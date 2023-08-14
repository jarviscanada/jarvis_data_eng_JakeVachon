import org.slf4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp {

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }
        JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
        javaGrepLambdaImp.setRegex(args[0]);
        javaGrepLambdaImp.setRootPath(args[1]);
        javaGrepLambdaImp.setOutFile(args[2]);

        try {
            javaGrepLambdaImp.process();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<File> listFiles(String rootDir) {

        System.out.print("kittykat");
        File root = new File(rootDir);
        List<File> returnRes;

        Stream<File> files = Stream.of(Objects
                .requireNonNull(root.listFiles()));

        returnRes = files.filter(file -> file.isFile()).collect(Collectors.toList());

        return returnRes;

    }

    @Override
    public List<String> readLines(File inputFile) throws IOException {

        System.out.print("doggydog");
        List<String> lines = new ArrayList<String>();
        Stream<String> stream;

        stream = Files.lines(Paths.get(getRootPath() + '/' + inputFile.getName()));
        stream.forEach(lines::add);

        return lines;
    }
}