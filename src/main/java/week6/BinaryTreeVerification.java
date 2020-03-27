package week6;

import java.io.*;

public class BinaryTreeVerification {

    private static Node[] A;

    static class Node {
        int key;
        int left;
        int right;

        public Node(int key, int left, int right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        try {
            File iFile = new File("input.txt");
            FileInputStream fis = new FileInputStream(iFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));

            File oFile = new File("output.txt");
            FileWriter writer = new FileWriter(oFile);

            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);
            A = new Node[n + 1];
            for (int i = 1; i < n + 1; i++) {
                line = bufferedReader.readLine();
                String[] s = line.split(" ");
                A[i] = new Node(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            }

            boolean correct = true;

            if (n > 0)
                correct = check(1, Integer.MIN_VALUE, Integer.MAX_VALUE);
            writer.append(correct ? "YES" : "NO");

            bufferedInputStream.close();
            fis.close();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean check(int i, int min, int max) {
        if (i == 0)
            return true;

        if (min > A[i].key || max < A[i].key)
            return false;

        return check(A[i].left, min, A[i].key - 1) && check(A[i].right, A[i].key + 1, max);
    }
}
