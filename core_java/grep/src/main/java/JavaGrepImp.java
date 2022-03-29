import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.BasicConfigurator;


import java.io.File;
import java.io.IOException;
import java.util.List;


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

    }

    @Override
    public List<File> listFiles(String rootDir) {
        return null;
    }

    @Override
    public List<File> readLines(File inputFile) {
        return null;
    }

    @Override
    public Boolean containsPattern(String line) {
        return null;
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {

    }

    @Override
    public String getRootPath() {
        return null;
    }

    @Override
    public void setRootPath(String rootPath) {

    }

    @Override
    public String getRegex() {
        return null;
    }

    @Override
    public void setRegex(String regex) {

    }

    @Override
    public String getOutFile() {
        return null;
    }

    @Override
    public void setOutFile(String outFile) {

    }
}
