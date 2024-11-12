import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FiveMcKenna {
    public static void main(String[] args) {
        int m = (int)Math.pow(2, 7);
        char[] cipher = new char[m];
        /* This for loop finds the cipher that we can pass
         * cipher.txt through to get the original plain.txt file
         * by taking the congruency y ≡ 7x+3 (mod128), adding 125 to
         * both sides to get y+125 ≡ 7x mod(128), and multiplying both
         * sides by 7^-1 (which = 55 mod(128)), giving the formula
         * formula of 55(y+125) ≡ x mod(128).
         */
        for (int i = 0; i < m; i++)
            cipher[i] =(char)mod(55 * i + 91, m);
        String cipherFile = read("cipher.txt");
        for (int i = 0; i < cipherFile.length(); i++)
            System.out.print(cipher[cipherFile.charAt(i)]);
    }
    public static String read(String path) {
        String ret = null;
        try {
            ret = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static int mod(int x, int n) { return ((x % n) + n) % n; }
}