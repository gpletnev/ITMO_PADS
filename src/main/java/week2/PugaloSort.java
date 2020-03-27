package week2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PugaloSort {

    static int split(int[] a, int l, int r, int k) {
        int x = a[l];

        while (l <= r) {
            while (a[l] < x)
                l += k;
            while (a[r] > x)
                r -= k;

            if (l <= r) {
                int temp = a[l];
                a[l] = a[r];
                a[r] = temp;
                l += k;
                r -= k;
            }
        }

        return l;
    }

    static void qsort(int[] a, int l, int r, int k) {
        if (l == r)
            return;

        int m = split(a, l, r, k);

        qsort(a, l, m - k, k);
        qsort(a, m, r, k);
    }

    static void pugaloSort(int[] a, int n, int k) {
        int r = ((n - 1) / k) * k;

        for (int i = 0; i < k; i++) {
            qsort(a, i, r, k);
            r++;

            if (r > n - 1)
                r -= k;
        }
    }

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            // 1<=n<=1000000
            int n = scanner.nextInt();
            System.out.print(n + " ");
            int k = scanner.nextInt();
            System.out.println(k);

            int[] input = new int[n];
            for (int i = 0; i < n; i++) {
                input[i] = scanner.nextInt();
                //System.out.print(input[i] + " ");
            }
            System.out.println();

            file = new File("output.txt");
            FileWriter writer = new FileWriter(file);

            String output = "YES";
            if (k != 1) {

                pugaloSort(input, n, k);

                System.out.println();
/*                for (int i = 0; i < n; i++) {
                    System.out.print(input[i] + " ");
                }*/
                System.out.println();
                for (int i = 1; i < n; i++) {
                    if (input[i] < input[i - 1]) {
                        output = "NO";
                        break;
                    }
                }
            }
            writer.append(output);
            System.out.println(output);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
