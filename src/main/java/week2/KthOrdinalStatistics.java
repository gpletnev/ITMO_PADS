package week2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class KthOrdinalStatistics {

    static int k1;
    static int k2;
    static void qsort(int[] a, int l, int r) {
        int key = a[(r + l) / 2];

        int i = l;
        int j = r;
        do {
            while (a[i] < key) {
                i++;
            }

            while (key < a[j]) {
                j--;
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

        if (l < j && !(k1 > j && k2 > j))
            qsort(a, l, j);
        if (i < r && !(k1 < i && k2 < i))
            qsort(a, i, r);
    }

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            // 2<=n<=40000000
            int n = scanner.nextInt();
            System.out.print(n + " ");
            k1 = scanner.nextInt();
            System.out.print(k1 + " ");
            k2 = scanner.nextInt();
            System.out.println(k2);

            int A = scanner.nextInt();
            System.out.print(A + " ");
            int B = scanner.nextInt();
            System.out.print(B + " ");
            int C = scanner.nextInt();
            System.out.print(C + " ");
            int a1 = scanner.nextInt();
            System.out.print(a1 + " ");
            int a2 = scanner.nextInt();
            System.out.println(a2);

            file = new File("output.txt");
            FileWriter writer = new FileWriter(file);

            // a[i] = A*a[i-2] + B*a[i-1] + C
            int[] a = new int[n + 1];
            a[1] = a1;
            a[2] = a2;
            for (int i = 3; i <= n; i++) {
                a[i] = A * a[i - 2] + B * a[i - 1] + C;
            }

            qsort(a, 1, n);

            for (int i = k1; i <= k2; i++) {
                writer.append(a[i] + " ");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
