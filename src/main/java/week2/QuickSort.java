package week2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuickSort {

    static int count = 0;

    static void qsort(int[] a, int l, int r) {
        int key = a[(r + l) / 2];

        System.out.println("key = " + key);

        int i = l;
        int j = r;
        do {
            while (a[i] < key) {
                i++;
                count++;
            }

            while (key < a[j]) {
                j--;
                count++;
            }

            if (i <= j) {
                // swap
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;

                i++;
                j--;
            }
        } while (i <= j);

        if (l < j)
            qsort(a, l, j + 1);
        if (i < r)
            qsort(a, i, r);
    }

    static int[] anti_qsort(int n) {
        int[] output = new int[n];
        for (int i = 0; i < n; i++) {
            output[i] = i + 1;
        }

        for (int i = 2; i < n; i++) {
            int m = i / 2;
            int temp = output[i];
            output[i] = output[m];
            output[m] = temp;
        }
        return output;
    }

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            // 1<=n<=1000000
            int n = scanner.nextInt();
            System.out.println(n);

//            int[] input = new int[n];
//            for (int i = 1; i <= n; i++) {
//                input[i] = scanner.nextInt();
//                System.out.print(input[i] + " ");
//            }
//            System.out.println();
//
//            qsort(input, 1, n);

            int[] input = anti_qsort(n);

            file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < n; i++) {
                writer.append(input[i] + " ");
                //System.out.print(input[i] + " ");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
