package week1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class APlusB2 {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new FileReader(new File("input.txt")));

            FileWriter writer = new FileWriter(new File("output.txt"));

            int a = scanner.nextInt();
            long b = scanner.nextLong();

            long sum = a + b*b;

            writer.write(String.valueOf(sum));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
