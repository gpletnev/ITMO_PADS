package week6;

import java.io.*;

public class BinarySubtreeRemove {

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

    static int count(Node root) {
        int leftCount, rightCount;
        if (root == null)
            return 0;

        leftCount = count(A[root.left]);
        rightCount = count(A[root.right]);

        return 1 + leftCount + rightCount;
    }

    static int findParent(int i, int key) {
        Node root = A[i];
        if (root == null || (root.left != 0 && A[root.left].key == key) || (root.right != 0 &&A[root.right].key == key))
            return i;
        if (key < root.key) {
            return findParent(root.left, key);
        } else {
            return findParent(root.right, key);
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

            line = bufferedReader.readLine();
            int m = Integer.parseInt(line);
            line = bufferedReader.readLine();
            String[] s = line.split(" ");
            for (int i = 0; i < m; i++) {
                int k = Integer.parseInt(s[i]);

                int parentIndex = findParent(1, k);
                if (parentIndex != 0) {
                    Node parent = A[parentIndex];
                    if (parent.left != 0 && A[parent.left].key == k) {
                        n -= count(A[parent.left]);
                    } else {
                        n -= count(A[parent.right]);
                    }

                    if (parent.left != 0 && A[parent.left].key == k) {
                        parent.left = 0;
                    } else {
                        parent.right = 0;
                    }
                }
                writer.append(n + "\n");
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
