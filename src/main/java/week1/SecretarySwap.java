package week1;

import java.io.*;

public class SecretarySwap {

    static int n = 0;
    static int[] A;

    static Integer indexOfMin(int start) {
        int minIndex = start;
        int minValue = A[start];
        for (int i = start; i < n + 1; i++) {
            if (A[i] < minValue) {
                minIndex = i;
                minValue = A[i];
            }
        }
        return minIndex;
    }

    static void swap(Integer start, Integer end) {
        int tempV = A[end];
        A[end] = A[start];
        A[start] = tempV;
    }

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(new File("input.txt"));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));

            FileWriter writer = new FileWriter(new File("output.txt"));

            String line = bufferedReader.readLine();
            n = Integer.parseInt(line);
            A = new int[n + 1];
            line = bufferedReader.readLine();
            String[] s = line.split(" ");
            for (int i = 1; i < n + 1; i++) {
                A[i] = Integer.parseInt(s[i - 1]);
            }

            for (int i = 1; i < n + 1; i++) {
                int minIndex = indexOfMin(i);
                if (i != minIndex) {
                    swap(i, minIndex);
                    writer.append("Swap elements at indices ")
                            .append(String.valueOf(i))
                            .append(" and ")
                            .append(String.valueOf(minIndex))
                            .append(".\n");
                }
            }
            writer.append("No more swaps needed.\n");
            for (int i = 1; i < n + 1; i++) {
                writer.append(String.valueOf(A[i])).append(" ");
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
