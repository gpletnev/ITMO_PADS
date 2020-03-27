package week4;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Stack {

    public static void main(String[] args) {
        try {
            File iFile = new File("input.txt");
/*            Scanner scanner = new Scanner(file);
            int n = scanner.nextInt();
            System.out.println(n);
            scanner.close();*/

            File oFile = new File("output.txt");
            FileWriter writer = new FileWriter(oFile);
            FileOutputStream fileOutputStream = new FileOutputStream(oFile);

            FileInputStream fis = new FileInputStream(iFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));

            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);
            LinkedList<Integer> list = new LinkedList<>();

                for (int i = 0; i < n; i++) {
                    line = bufferedReader.readLine();
                    byte command = line.getBytes()[0];
                    if (command == 43) {
                        int x = Integer.parseInt(line.substring(2));
                        list.push(x);
                    } else if (command == 45) {
                        int x = list.pop();
//                        fileOutputStream.write(x);
//                        fileOutputStream.write(51);
                        writer.append(x + "\n");
                    }
                }

            //fileOutputStream.close();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
