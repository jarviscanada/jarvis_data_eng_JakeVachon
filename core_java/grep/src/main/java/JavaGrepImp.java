import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.BasicConfigurator;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JavaGrepImp implements JavaGrep {

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }


    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
        javaGrepImp.process();
    }   catch (Exception ex) {
        javaGrepImp.logger.error("Error: Unable to process", ex);
    }
    }

    @Override
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<>();

        for (File file : listFiles(getRootPath())) {
            for (String line : readLines(file)) {
                if (containsPattern(line)) {
                    matchedLines.add(line);
                }
            }
        } writeToFile(matchedLines);

    }

    @Override
    public List<File> listFiles(String rootDir) {

        List<File> returnRes = new ArrayList<>();
        File root = new File(rootDir);
        List<File> files = Arrays.asList(root.listFiles());
        List<File> newList = null;


        for (File file : files) {
            if (file.isDirectory()) {
                newList = listFiles(file.getAbsolutePath());

            }
            else if (file.isFile()){
                returnRes.add(file);

            }
            if (newList != null) {
                returnRes.addAll(newList);
            }
        }

        return returnRes;
    }

    @Override
    public List<String> readLines(File inputFile) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));

            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

        @Override
    public Boolean containsPattern(String line) {

        Pattern pattern = Pattern.compile(getRegex());
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {

            return true;
        }

        return false;
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(getOutFile()), "utf-8"))) {
            for (String line : lines) {
                writer.write(line);

            }

        }
    }

    public Logger getLogger() {
        return logger;
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}
