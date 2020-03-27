package week4;

import java.io.*;
import java.util.LinkedList;

public class Postfix {
    public static void main(String[] args) {

        try {
            File iFile = new File("input.txt");
            FileInputStream fis = new FileInputStream(iFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));

            LinkedList<Integer> stack = new LinkedList<>();

            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);

            line = bufferedReader.readLine();
            String[] exp = line.split(" ");
            for (int i = 0; i < n; i++) {
                String element = exp[i];
                switch (element) {
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case "-":
                        int o = stack.pop();
                        stack.push(stack.pop() - o);
                        break;
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;
                    default:
                        stack.push(Integer.parseInt(element));
                        break;
                }
            }

            File oFile = new File("output.txt");
            FileWriter writer = new FileWriter(oFile);
            writer.append(stack.pop().toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
