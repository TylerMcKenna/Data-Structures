import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Frequency {
    public static void main(String[] args) {
        String contents = soClean(read("test.txt"));
        String[] words = contents.split("\\s+");

        HashMap<String, Integer> frequency = new HashMap<String, Integer>();
        
        for (String word : words) 
            if (word.length() > 5 && frequency.containsKey(word))
                frequency.put(word, frequency.get(word) + 1);
            else if (word.length() > 5)
                frequency.put(word, 1);
        
        String[] keys = frequency.keySet().toArray(new String[0]);
        int[] values = new int[keys.length];
        for (int i = 0; i < keys.length; i++)
            values[i] = frequency.get(keys[i]);
        sort(keys, values, 0, values.length - 1);
        for (int i = 0; i < 10; i++) 
            System.out.printf("|%10s | %d|%n", keys[i], values[i]);
    }

    public static void sort(String[] k, int[] v, int lo, int hi) {
        if (lo >= hi)
            return;
        int j = lo, i = lo - 1;
        do {
            if (v[j] >= v[hi]) {
                int escrow1 = v[++i];
                String escrow2 = k[i];
                v[i] = v[j];
                v[j] = escrow1;
                k[i] = k[j];
                k[j] = escrow2;
            }
        } while (j++ < hi);
        sort(k, v, lo, i - 1);
        sort(k, v, i + 1, hi);
    }

    public static String soClean(String contents) {
        contents = contents.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int ඞ = 0; ඞ < contents.length(); ඞ++) {
            char cur = contents.charAt(ඞ);
            if (cur == ' ' || cur == '\n')
                sb.append(' ');
            if ('a' <= cur && cur <= 'z')
                sb.append(cur);
        }
        return sb.toString();
    }

    public static String read(String path) {
        byte[] ret = null;
        try {
            ret = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(ret);
    }
}