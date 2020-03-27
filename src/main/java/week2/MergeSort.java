package week2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MergeSort {
    static FileWriter writer;

    static void sort(int[] a, int l, int r) throws IOException {
        if (l == r - 1)
            return;

        int m = l + (r - l) / 2;

        sort(a, l, m);
        sort(a, m, r);

        int i = l;
        int j = m;
        List<Integer> temp = new LinkedList<Integer>();
        while (i < m || j < r) {
            if (j==r || (i < m && a[i] <= a[j])) {
                temp.add(a[i]);
                i++;
            } else {
                temp.add(a[j]);
                j++;
            }
        }
        Iterator<Integer> iterator = temp.iterator();
        i = l;
        while (iterator.hasNext()) {
            a[i] = iterator.next();
            i++;
        }
        writer.append(l + 1 + " " + r + " " + a[l] + " " + a[r-1] + "\n");
    }

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            int n = scanner.nextInt();
            int[] input = new int[n];
            System.out.println(n);
            for (int i = 0; i < n; i++) {
                input[i] = scanner.nextInt();
                System.out.print(input[i] + " ");
            }
            System.out.println();
            System.out.println();

            file = new File("output.txt");
            writer = new FileWriter(file);

            sort(input, 0, n);

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
