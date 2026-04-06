package march19.util;

import march19.exceptions.checked.InvalidFileFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {

    public void readFile(String fileName) throws InvalidFileFormatException, IOException {
        if (!fileName.endsWith(".txt")) {
            throw new InvalidFileFormatException("Only .txt files are allowed");
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
