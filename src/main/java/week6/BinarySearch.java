package week6;

import java.io.*;

public class BinarySearch {
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
            int[] A = new int[n];
            line = bufferedReader.readLine();
            String[] s = line.split(" ");
            for (int i = 0; i < n; i++) {
                A[i] = Integer.parseInt(s[i]);
            }

            line = bufferedReader.readLine();
            int M = Integer.parseInt(line);
            line = bufferedReader.readLine();
            s = line.split(" ");
            for (int i = 0; i < M; i++) {
                int x = Integer.parseInt(s[i]);

                int l = -1;
                int r = n;
                while (r > l + 1) {
                    int m = (l + r) / 2;
                    if (A[m] < x)
                        l = m;
                    else
                        r = m;
                }

                if (r < n && A[r] == x) {
                    int r1 = r;
                    int r2 = r;

                    if (r < n - 1 && A[r + 1] == x) {
                        r = n;

                        while (l < r - 1) {
                            int m = (l + r) / 2;

                            if (A[m] > x)
                                r = m;
                            else
                                l = m;
                        }
                        r2 = l;
                    }
                    writer.append(String.format("%d %d\n", r1 + 1, r2 + 1));
                } else
                    writer.append("-1 -1\n");
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
