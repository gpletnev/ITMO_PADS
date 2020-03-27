package week4;

import java.io.*;
import java.util.LinkedList;

public class QueueWithMin {
    class Node {
        int value;
        int min;

        public Node(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }

    LinkedList<Node> stack = new LinkedList<>();

    void push(int value) {
        Node node;
        if (stack.isEmpty())
            node = new Node(value, value);
        else {
            int min = stack.peek().min;
            node = new Node(value, Math.min(min, value));
        }
        stack.push(node);
    }

    int pop() {
        return stack.pop().value;
    }

    int getMin() {
        return stack.peek().min;
    }

    boolean isEmpty() {
        return stack.isEmpty();
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

            QueueWithMin s1 = new QueueWithMin();
            QueueWithMin s2 = new QueueWithMin();

            for (int i = 0; i < n; i++) {
                line = bufferedReader.readLine();
                byte command = line.getBytes()[0];
                if (command == '+') {
                    int x = Integer.parseInt(line.substring(2));
                    s1.push(x);
                } else if (command == '-') {
                    if (s2.isEmpty()) {
                        while (!s1.isEmpty()) {
                            s2.push(s1.pop());
                        }
                    }
                    s2.pop();
                } else if (command == '?') {
                    int min;
                    if (s1.isEmpty() || s2.isEmpty())
                        min = s1.isEmpty() ? s2.getMin() : s1.getMin();
                    else
                        min = Math.min(s1.getMin(), s2.getMin());
                    writer.append(min + "\n");
                }
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
