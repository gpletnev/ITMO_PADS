package week4;

import java.io.*;
import java.util.LinkedList;

public class BracketSequence {

    static boolean isCorrect(byte[] sequence) {

        LinkedList<Byte> list = new LinkedList<>();
        for (byte b : sequence) {
            switch (b) {
                case '(':
                case '[':
                    list.push(b);
                    break;
                case ')':
                case ']':
                    if (list.isEmpty())
                        return false;

                    if ((list.pop() + b) % 132 <= 1)
                        return false;
                    break;
            }
        }

        return list.isEmpty();
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


            for (int i = 0; i < n; i++) {
                line = bufferedReader.readLine();
                byte[] bytes = line.getBytes();

                if (isCorrect(bytes)) {
                    writer.append("YES\n");
                } else {
                    writer.append("NO\n");
                }
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
