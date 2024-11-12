import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays; // for tostring

class FourMcKenna {
    public static void main(String args[]) {
        double startTime = System.nanoTime();

        // document given by Professor Tashfeen
        byte[] bytes = getFileBytes("half_gaps.bin");
        long[] gaps = new long[bytes.length];
        for(int i = 0; i < bytes.length; i++) 
            gaps[i] = Byte.toUnsignedInt(bytes[i]);
        gaps = cumSum(gaps);
        System.out.println(Arrays.toString(Arrays.copyOfRange(gaps, 0, 15)));
        System.out.println(Arrays.toString(Arrays.copyOfRange(gaps, gaps.length - 5, gaps.length)));

        double duration = System.nanoTime() - startTime;
        duration = duration / Math.pow(10, 9);
        System.out.println("It took " + duration + " seconds to compute these numbers");
    }

    public static byte[] getFileBytes(String path) {
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

/* 
 * 
 *  1  2  3  4  5  6  7  8  9  10
 *  1  3  6 10 15 21 28 36 45  55
 * 
 * 
*/
// storing the gaps is far more efficient
// adds up all gaps between prime numbers until youre trying to get to one (ALL GAPS ARE EVEN SO THEY ARE DIVIDED BY TWO HENCE HALF GAPS) cumsum is the inverse of converting 
    public static long[] cumSum(long[] originalArray) {
        for (int i = 1; i < originalArray.length; i++) {
            originalArray[i] = originalArray[i] + originalArray[i-1];
        }
        for (int i = 0; i < originalArray.length; i++) {
            originalArray[i] = 2 * originalArray[i] + 3;
        }
        return originalArray;

        //System.out.println(Arrays.toString(Arrays.copyOfRange(gaps, 0, 15)));
        //System.out.println(Arrays.toString(Arrays.copyofRange(gaps, gaps.length - 5, gaps.length)));
    }
}