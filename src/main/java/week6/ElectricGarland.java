package week6;

import java.io.*;

public class ElectricGarland {
    public static void main(String[] args) {
        try {
            File iFile = new File("input.txt");
            FileInputStream fis = new FileInputStream(iFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));

            File oFile = new File("output.txt");
            FileWriter writer = new FileWriter(oFile);

            String line = bufferedReader.readLine();
            String[] s = line.split(" ");
            int n = Integer.parseInt(s[0]);
            double[] h = new double[n];
            double A = Double.parseDouble(s[1]);
            h[0] = A;

            double l = 0, r = h[0];
            while (r - l > 0.0000000001) {
                h[1] = (l + r) / 2;
                boolean up = true;
                for (int i = 2; i < n; i++) {
                    h[i] = 2 * h[i - 1] - h[i - 2] + 2;
                    if (h[i] < 0) {
                        up = false;
                        break;
                    }
                }
                if (up) {
                    r = h[1];
                } else {
                    l = h[1];
                }
            }

            double B = h[n - 1];
            bufferedInputStream.close();
            fis.close();

            writer.write(String.valueOf(B));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
