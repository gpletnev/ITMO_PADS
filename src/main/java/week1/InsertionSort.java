package week1;

import java.io.*;

public class InsertionSort {
    static void swap(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(new File("input.txt"));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));

            FileWriter writer = new FileWriter(new File("output.txt"));

            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);
            int[] A = new int[n + 1];
            line = bufferedReader.readLine();
            String[] s = line.split(" ");
            for (int i = 1; i < n + 1; i++) {
                A[i] = Integer.parseInt(s[i - 1]);
            }

            int[] steps = new int[n + 1];
            steps[1] = 1;
            for (int j = 2; j < n + 1; j++) {
                int i = j - 1;
                while (i > 0 && A[i] > A[i + 1]) {
                    swap(A, i, i + 1);
                    i = i - 1;
                }
                steps[j] = i + 1;
            }

            for (int i = 1; i < n + 1; i++) {
                writer.append(String.valueOf(steps[i])).append(" ");
            }
            writer.append("\n");
            for (int i = 1; i < n + 1; i++) {
                writer.append(String.valueOf(A[i])).append(" ");
            }
            bufferedInputStream.close();
            fis.close();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
