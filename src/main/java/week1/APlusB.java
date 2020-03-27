package week1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class APlusB {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new FileReader(new File("input.txt")));

            FileWriter writer = new FileWriter(new File("output.txt"));

            int a = scanner.nextInt();
            int b = scanner.nextInt();

            int sum = a + b;

            writer.write(String.valueOf(sum));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
