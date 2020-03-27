package week4;

import java.io.*;
import java.util.LinkedList;

public class Queue {

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
            LinkedList<Integer> list = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                line = bufferedReader.readLine();
                byte command = line.getBytes()[0];
                if (command == 43) {
                    int x = Integer.parseInt(line.substring(2));
                    list.add(x);
                } else if (command == 45) {
                    int x = list.poll();
                    writer.append(x + "\n");
                }
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
