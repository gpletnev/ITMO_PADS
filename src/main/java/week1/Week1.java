package week1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Week1 {
    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            FileReader reader = new FileReader(file);
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();
            int[] input = new int[n];
            for (int i = 0; i < n; i++) {
                input[i] = scanner.nextInt();
            }
            reader.close();

            System.out.println(n);
            for (int i = 0; i < n; i++) {
                System.out.print(input[i] + " ");
            }
            System.out.println();
            System.out.println();

            int[] output = new int[n];
            output[0] = 1;

            for (int j = 1; j < n; j++) {
                int i = j;
                while (i > 0 && input[i] < input[i - 1]) {
                    // swap(input[i], input[i+1]);
                    int temp = input[i];
                    input[i] = input[i - 1];
                    input[i - 1] = temp;
                    --i;
                }
                output[j] = i + 1;
            }


            file = new File("output.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            for (int i = 0; i < n; i++) {
                writer.append(output[i] + " ");
                System.out.print(output[i] + " ");
            }
            System.out.println();
            writer.append("\n");
            for (int i = 0; i < n; i++) {
                writer.append(input[i] + " ");
                System.out.print(input[i] + " ");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
