import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;

public class HashBrown {
  // Change to Long or Byte to test
  public static final long MAX = Integer.MAX_VALUE;

  public static void main(String[] args) {
    String[] words = read("./words.txt").split("\\s+");
    HashMap<Long, LinkedList<String>> map = new HashMap<>();
    
    for (String word : words) {
      if (!map.containsKey(hash(word))) {
        map.put(hash(word), new LinkedList<String>());
      }
      map.get(hash(word)).add(word);
    }

    int collisions = 0;
    LinkedList<String> cur = null;
    for (long hash : map.keySet()) {
      cur = map.get(hash);
      if (cur.size() > 1) {
        collisions++;
        String ls = Arrays.toString(cur.toArray());
        System.out.printf("%10d has %s%n", hash, ls);
      }
    }
    System.out.printf("Collision = %d/%d%n", collisions, words.length);

    /*
    for (Long key : map.keySet()) {
      if (map.get(key).size() > 1) {
        for (String word : map.get(key)) {
          System.out.printf("h(%s) = ", word);
        }
        System.out.println(key);
      }
    }
    */
  }

  private static long base27(Object toHash) {
    String s = String.format("%s", toHash);
    long base = 27; // 26 alphabet and a blank-space
    long offset = 'a' - 1;
    long x = 0, p = 1;
    for (int i = s.length() - 1; i > -1; i--) {
      long bit = s.charAt(i);
      bit = bit == ' ' ? 0 : (bit - offset);
      x += bit * p;
      p *= base;
    } // Long.MAX_VALUE = 2^(63) - 1, 64 bits but one sign bit
    return Math.floorMod(x, Long.MAX_VALUE);
  }

  public static long hash(Object toHash) {
    return Math.floorMod(base27(toHash), MAX);
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