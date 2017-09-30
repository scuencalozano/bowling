package bowling;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if ( args.length < 1 ){
            System.out.println("Error, file path missed");
            return;
        }
        String filePath = args[0];
        new Main().loadFile(filePath);
    }

    private void loadFile(String filePath){
        List<String> fileLines = readFile(filePath);
        Game bowling = new Bowling(fileLines);
        if (bowling.isValid()) {
            bowling.calculateScore();
            bowling.showResults();
        }
    }

    private List<String> readFile(String filePath) {
        try {
            File file = new File(filePath);
            // Using commons-io FileUtils
            // https://commons.apache.org/proper/commons-io/description.html
            return FileUtils.readLines(file, "UTF-8");
        } catch (Exception e) {
            System.out.println("Error reading the file:" + filePath);
        }
        return Collections.emptyList();
    }

}
