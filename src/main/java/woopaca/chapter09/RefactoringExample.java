package woopaca.chapter09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RefactoringExample {

    public static void main(String[] args) throws IOException {
        String oneLine =
                processFile(BufferedReader::readLine);
        String twoLine =
                processFile(bufferedReader -> bufferedReader.readLine() + bufferedReader.readLine());
    }

    public static String processFile(BufferedReaderProcessor brp) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("ModernJavaInAction/chap9/data.txt"))) {
            return brp.process(br);
        }
    }

    interface BufferedReaderProcessor {

        String process(BufferedReader bufferedReader) throws IOException;
    }
}
