package week6;

import java.io.*;

public class BinaryTreeHeight {

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

    static int height(Node root) {
        int leftHeight, rightHeight;
        if (root == null)
            return 0;

        leftHeight = height(A[root.left]);
        rightHeight = height(A[root.right]);

        return (Math.max(leftHeight, rightHeight)) + 1;
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

            int height = n == 0 ? 0 : height(A[1]);
            writer.append(String.valueOf(height));

            bufferedInputStream.close();
            fis.close();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
