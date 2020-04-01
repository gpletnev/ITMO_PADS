package week7;

import java.io.*;

public class BinaryTreeBalance {

    private static Node[] A;

    static class Node {
        int key;
        int left;
        int right;
        int balance;

        public Node(int key, int left, int right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    static int height(Node root) {
        int leftHeight, rightHeight;
        if (root == null)
            return 0;

        leftHeight = height(A[root.left]);
        rightHeight = height(A[root.right]);
        root.balance = rightHeight - leftHeight;
        return (Math.max(leftHeight, rightHeight)) + 1;
    }

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(new File("input.txt"));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));

            FileWriter writer = new FileWriter(new File("output.txt"));

            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);
            A = new Node[n + 1];
            for (int i = 1; i < n + 1; i++) {
                line = bufferedReader.readLine();
                String[] s = line.split(" ");
                A[i] = new Node(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            }

            int height = n == 0 ? 0 : height(A[1]);
            for (int i = 1; i < n + 1; i++) {
                writer.append(String.valueOf(A[i].balance)).append("\n");
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
