package week7;

import java.io.*;
import java.util.LinkedList;

public class BinaryTreeLeftTurn {

    private Node[] A;

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

    int height(Node root) {
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
            BinaryTreeLeftTurn tree = new BinaryTreeLeftTurn();
            tree.A = new Node[n + 1];
            for (int i = 1; i < n + 1; i++) {
                line = bufferedReader.readLine();
                String[] s = line.split(" ");
                tree.A[i] = new Node(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            }

            int height = n == 0 ? 0 : tree.height(tree.A[1]);
            Node root;
            if (tree.A[tree.A[1].right].balance == -1)
                root = tree.rotateRightLeft();
            else
                root = tree.rotateLeft();

            writer.append(String.valueOf(n)).append("\n");
            tree.reorder(writer, root);


            bufferedInputStream.close();
            fis.close();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void reorder(FileWriter writer, Node root) throws IOException {
        int counter = 1;
        LinkedList<Node> stack = new LinkedList<>();
        stack.add(root);

        while (stack.size() > 0) {
            Node node = stack.pollLast();

            writer.append(String.valueOf(node.key)).append(" ");
            if (node.left != 0 && node.right != 0) {
                writer.append(String.valueOf(++counter)).append(" ").append(String.valueOf(++counter)).append("\n");

            } else if (node.left == 0 && node.right != 0) {
                writer.append("0").append(" ").append(String.valueOf(++counter)).append("\n");

            } else if (node.left != 0) {
                writer.append(String.valueOf(++counter)).append(" 0\n");
            } else {
                writer.append("0 0 \n");
            }

            if (node.left != 0)
                stack.push(A[node.left]);
            if (node.right != 0)
                stack.push(A[node.right]);
        }

    }

    private Node rotateLeft() {
        int b = A[1].right;

        A[1].right = A[b].left;
        A[b].left = 1;

        return A[b];
    }

    private Node rotateRightLeft() {
        int b = A[1].right;
        int x = A[A[b].left].left;
        int y = A[A[b].left].right;
        int c = A[b].left;

        A[1].right = x;
        A[c].left = 1;
        A[c].right = b;
        A[b].left = y;

        return A[c];
    }
}
