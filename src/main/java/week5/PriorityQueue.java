package week5;

import java.io.*;

public class PriorityQueue {

    static class Node {
        int key;
        int line;

        public Node(int key, int line) {
            this.key = key;
            this.line = line;
        }
    }

    int[] lineNumberToIndex;
    Node[] A;

    int heapSize = 0;
    FileWriter writer;

    public PriorityQueue(int n, FileWriter writer) {
        lineNumberToIndex = new int[n + 1];
        A = new Node[n];
        this.writer = writer;
    }

    void swap(int i, int j) {
        Node t = A[i];
        A[i] = A[j];
        A[j] = t;
        lineNumberToIndex[t.line] = j;
        lineNumberToIndex[A[i].line] = i;
    }

    int parent(int i) {
        return (i - 1) / 2;
    }

    int leftChild(int i) {
        return 2 * i + 1;
    }

    int rightChild(int i) {
        return 2 * i + 2;
    }

    void minHeapify(int i) {
        int lowest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < heapSize && A[left].key < A[lowest].key)
            lowest = left;
        if (right < heapSize && A[right].key < A[lowest].key)
            lowest = right;

        if (lowest != i) {
            swap(i, lowest);
            minHeapify(lowest);
        }
    }

    void processLine(int i, String line) throws IOException {

        byte command = (byte) line.charAt(0);
        if (command == 'X') {
            writer.append(extractMin().concat("\n"));
        } else if (command == 'A') {
            int x = Integer.parseInt(line.substring(2));
            insert(x, i);
        } else if (command == 'D') {
            String[] s = line.split(" ");

            int lineNumber = Integer.parseInt(s[1]);
            int j = lineNumberToIndex[lineNumber];
            decreaseKey(j, Integer.parseInt(s[2]));
        }
    }

    String extractMin() {
        if (heapSize < 1)
            return "*";

        String min = String.valueOf(A[0].key);
        swap(0, heapSize - 1);
        heapSize--;
        //A.remove(A.size() - 1);
        minHeapify(0);

        return min;
    }

    void insert(int key, int line) {
        heapSize++;
        Node node = new Node(Integer.MAX_VALUE, line);
        A[heapSize - 1] = node;
        lineNumberToIndex[line] = heapSize - 1;
        decreaseKey(heapSize - 1, key);
    }

    void decreaseKey(int i, int key) {
        A[i].key = key;
        while (i > 0 && A[i].key < A[parent(i)].key) {
            swap(i, parent(i));
            i = parent(i);
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

            PriorityQueue priorityQueue = new PriorityQueue(n, writer);

            for (int i = 1; i < n + 1; i++) {
                line = bufferedReader.readLine();
                priorityQueue.processLine(i, line);
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
