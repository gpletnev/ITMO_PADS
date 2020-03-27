package week3;

import java.io.*;
import java.util.Scanner;

public class DigitalSort {

    private static final byte CODE_A = 97;
    private static final byte LENGTH = 123 - 97;

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            // 2<=n<=40000000
            int n = scanner.nextInt();
            System.out.print(n + " ");
            int m = scanner.nextInt();
            System.out.print(m + " ");
            int K = scanner.nextInt();
            System.out.println(K);


            int[] output = new int[n];
            for (int i = 0; i < n; i++) {
                output[i] = i + 1;
            }
            scanner.close();

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            int end;
            do {
                end = fis.read();
            } while (end != 0x0a);

            byte[][] s = new byte[m + 1][n];

            byte[] line = new byte[n];
            for (int j = 0; j < m; j++) {
                if (bufferedInputStream.read(line, 0, n) > 0) {
                    for (int i = 0; i < n; i++) {
                        s[j][i] = (byte) (line[i] - CODE_A);
                    }
                    do {
                        end = bufferedInputStream.read();
                    } while (end != 0x0a && end != -1);
                }
            }

            bufferedInputStream.close();
            fis.close();

            int[] b = new int[n];
            int[] counts = new int[LENGTH];
            byte[] temp = new byte[n];
            for (int k = 1; k <= K; k++) {
                int f = m - k;

                for (int i = 0; i < n; i++) {
                    counts[s[f][i]]++;
                }

                for (int j = 1; j < LENGTH; j++) {
                    counts[j] += counts[j - 1];
                }

                for (int i = n - 1; i >= 0; i--) {
                    b[--counts[s[f][i]]] = output[i];
                }

                System.arraycopy(b, 0, output, 0, n);
                if (f > 0) {
                    for (int i = 0; i < n; i++) {
                        temp[i] = s[f - 1][output[i] - 1];
                    }
                    System.arraycopy(temp, 0, s[f - 1], 0, n);
                }

                for (int j = 0; j < LENGTH; j++) {
                    counts[j] = 0;
                }
            }

            file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < n; i++) {
                writer.append(String.valueOf(output[i]).concat(" "));
                //writer.append(s[0][i] + " ");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
