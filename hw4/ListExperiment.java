import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListExperiment {
    public static void main(String[] args) {
        List<Integer> ls = null;
        StringBuilder sb = new StringBuilder();
        appendTimes((int)Math.pow(2, 20), ls, sb);
        //searchTimes((int)Math.pow(2, 17), ls, sb);
    }

    /**
     * FOR ls IN [ArrayList, LinkedList]:
     *   i = 1
     *   WHILE i is less than n:
     *     record time for appending on ls
     *     i++
     *
     * At return, there should be a CSV file with two rows. The first
     * for the ArrayList and the second for the LinkedList. The ith
     * column of each row represents the nanoseconds taken to append
     * the ith element.
     *
     * @param n   The row length.
     * @param ls  The list that is either an array or linked list.
     * @param sb  The string builder to join the nanoseconds.
     */
    public static void appendTimes(int n, List<Integer> ls, StringBuilder sb) {
        long start = -1;
        for(int i = 0; i < 2; i++) {
            ls = (i == 0 ? new ArrayList<Integer>() : new LinkedList<Integer>());
            sb.setLength(0);
            for (int j = 0; j < n; j++) {
                start = System.nanoTime();
                ls.add(42);
                sb.append(System.nanoTime() - start + ",");
            }
            sb.setLength(sb.length() - 1);
            write("./append.csv", sb.toString(), i == 1);
        }
    }     

    /**
     * FOR ls IN [ArrayList, LinkedList]:
     *   i = 1
     *   WHILE 2^i is less than n:
     *     assure ls is empty
     *     fill ls with 2^i random integers
     *     let x be a random integer
     *     record time for binary searching x in ls
     *     i++
     *
     * At return, there should be a CSV file with two rows. The first
     * for the ArrayList and the second for the LinkedList. The ith
     * column of each row represents the nanoseconds taken to perform
     * the binary search on a list of size 2^i.
     *
     * @param n   The row length.
     * @param ls  The list that is either an array or linked list.
     * @param sb  The string builder to join the nanoseconds.
     */
    public static void searchTimes(int n, List<Integer> ls, StringBuilder sb) {
        long start = -1;
        int randInt = -1;
        for(int i = 0; i < 2; i++) {
            ls = (i == 0 ? new ArrayList<Integer>() : new LinkedList<Integer>());
            sb.setLength(0);
            for (int j = 0; Math.pow(2, j) < n; j++) {
                ls.clear();
                fillList(0,(int)Math.pow(2, j), ls);
                randInt = randomInteger(0, (int)Math.pow(2, j));

                start = System.nanoTime();
                binarySearch(ls, 4, 0, ls.size() - 1);
                sb.append(System.nanoTime() - start + ",");
            }
            sb.setLength(sb.length() - 1);
            write("./append.csv", sb.toString(), i == 1);
        }
    }
    
    /**
     * @param ls The list to binary search.
     * @param n  The integer to look for.
     * @param s  The starting bound.
     * @param r  The ending bound.
     * @return   Whether n was found in ls.
     */
    public static boolean binarySearch(List<Integer> ls, int n, int s, int r) {
        if (r >= s) {
            int mid = s + (r - s) / 2;
            if (ls.get(mid) == n) {
                return true;
            }
            if (ls.get(mid) > n) {
                return binarySearch(ls, n, s, mid - 1);
            }
            return binarySearch(ls, n, mid + 1, r);
        }
        return false;
    }

    /**
     * Write, appending or overwriting, a string to a file.
     *
     * @param path   The path of the file to write to.
     * @param line   The line to write to the file at that path.
     * @param append Whether to append or overwrite the file.
     */
    public static void write(String path, String line, boolean append) {
        try (FileWriter fw = new FileWriter(path, append)) {
            fw.write(line + "\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Fill the list ls with random integers and sort it.
     *
     * @param min  The lower bound (inclusive) for the random n.
     * @param max  The upper bound (exclusive) for the random n.
     * @param ls   The list to fill with random integers n.
     */
    public static void fillList(int min, int max, List<Integer> ls) {
        for (int i = 0; i < max; i++)
            ls.add(randomInteger(min, max));
        Collections.sort(ls);
    }

    /**
     * @param min The lower bound (inclusive) for the random n.
     * @param max The upper bound (exclusive) for the random n.
     * @return    A random integer n between min and max.
     */
    public static int randomInteger(int min, int max) {
        return min + (int)(Math.random() * (max - min));
    }
}