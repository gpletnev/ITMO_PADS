package week1;

import java.io.*;

public class Sortland {

    Resident[] residents;

    public Sortland(int n) {
        residents = new Resident[n + 1];
    }

    static class Resident {
        int i;
        float m;

        Resident(int i, float m) {
            this.i = i;
            this.m = m;
        }
    }

    void swap(Resident[] A, int i, int j) {
        Resident t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    Resident[] sort() {
        for (int j = 2; j < residents.length; j++) {
            int i = j - 1;
            while (i > 0 && residents[i].m > residents[i + 1].m) {
                swap(residents, i, i + 1);
                i = i - 1;
            }
        }
        return residents;
    }

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(new File("input.txt"));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));

            FileWriter writer = new FileWriter(new File("output.txt"));

            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);

            Sortland sortland = new Sortland(n);
            line = bufferedReader.readLine();
            String[] s = line.split(" ");
            for (int i = 1; i < n + 1; i++) {
                sortland.residents[i] = new Resident(i, Float.parseFloat(s[i - 1]));
            }

            Resident[] A = sortland.sort();

            writer.append(String.valueOf(A[1].i)).append(" ");
            writer.append(String.valueOf(A[(n + 1) / 2].i)).append(" ");
            writer.append(String.valueOf(A[n].i)).append(" ");
            bufferedInputStream.close();
            fis.close();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
