package week3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IntegerSort {
    static final int interval = 1000;
    private static final int k = 40000 * 40000 + 1;
    static final int NUMBER_OF_BUCKETS = k / interval;

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            // 2<=n<=40000000
            int n = scanner.nextInt();
            System.out.print(n + " ");
            int m = scanner.nextInt();
            System.out.println(m);

            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
                //System.out.print(input[i] + " ");
            }

            int[] b = new int[m];
            for (int i = 0; i < m; i++) {
                b[i] = scanner.nextInt();
                //System.out.print(input[i] + " ");
            }

            int[] ab = new int[m * n];
            int ci = 0;
            for (int j = 0; j < m; j++) {
                for (int i = 0; i < n; i++) {
                    int product = a[i] * b[j];
                    ab[ci] = product;
                    ci++;
                }
            }

            radixSort(ab);

            long sum = 0;
            for (int i = 0; i < m * n; i += 10) {
                sum += ab[i];
            }
            System.out.println(sum);

            file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            writer.append(String.valueOf(sum));
            writer.flush();
            writer.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static void radixSort(int[] array) {
        int k = Integer.SIZE / 8;
        int n = array.length;

        // count sort
        for (int i = 0; i < k; i++) {
            int[] counts = new int[256];

            for (int value : array) {
                counts[dig256(value, i)]++;
            }

            for (int j = 1; j < 256; j++) {
                counts[j] += counts[j - 1];
            }

            int[] b = new int[n];
            for (int j = n - 1; j >= 0; j--) {
                b[--counts[dig256(array[j], i)]] = array[j];
            }

            System.arraycopy(b, 0, array, 0, b.length);
        }
    }

    //эта функция возвращает p-ый байт числа n
    public static int dig256(int n, int p) {
        return (n >> (8 * p)) & 255;
    }
}
