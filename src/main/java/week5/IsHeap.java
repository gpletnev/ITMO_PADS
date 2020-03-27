package week5;

import java.io.*;
import java.util.LinkedList;

public class IsHeap {

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
            boolean fool = n % 2 > 0;
            boolean isHeap = true;
            int[] heap = new int[n + 1];
            heap[1] = Integer.parseInt(exp[0]);
            for (int i = 2; i < n + 1; i++) {
                heap[i] = Integer.parseInt(exp[i - 1]);
                    if (heap[i / 2] > heap[i]) {
                        isHeap = false;
                        break;
                    }
            }

            File oFile = new File("output.txt");
            FileWriter writer = new FileWriter(oFile);
            writer.append(isHeap ? "YES" : "NO");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
