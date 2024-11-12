import java.util.Stack;

public class Sorter implements GodricsHat {
    public void insertion(int[] array) {
        for(int i = 1; i < array.length; i++) {
            int j = i;
            int current = array[i];
            while(j != 0 && array[j-1] > current) {
                array[j] = array[j-1];
                j--;
            }
            array[j] = current;
        }
    }

    public void merge(int[] array) {
        if (array.length <= 1) {
            return;
        }
        int[] left = new int[array.length / 2];
        int[] right = new int[array.length - left.length];
        System.arraycopy(array, 0, left, 0, left.length);
        System.arraycopy(array, left.length, right, 0, right.length);
        merge(left);
        merge(right);
        int i = 0, li = 0, ri = 0;
        for (i = 0; i < array.length; i++) {
            if (li > left.length - 1) {
                array[i] = right[ri++];
            }
            else if (ri > right.length - 1) {
                array[i] = left[li++];
            }
            else if(left[li] <= right[ri]) {
                array[i] = left[li++];
            }
            else {
                array[i] = right[ri++];
            }
        }
    }

    public void quick(int[] array, int p, int r) {
        if (p < r) {
            // Partition begins
            int x = array[r];
            int i = p - 1;
            for (int j = p; j < r; j++) {
                if (array[j] <= x) {
                    i++;  
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            int temp = array[i + 1];
            array[i + 1] = array[r];
            array[r] = temp;
            int q = i + 1;
            // Partition ends
            quick(array, p, q-1);
            quick(array, q+1, r);
        }
    }

    public void quickLoopy(int[] array) {
        int r = array.length - 1;
        int p = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(p);
        stack.push(r);

        while (!stack.isEmpty()) {
            r = stack.pop(); 
            p = stack.pop();

            int x = array[r];

            int i = p - 1;
            for (int j = p; j < r; j++) {
                if (array[j] < x) {
                    i++;
                    int temp = array[i]; 
                    array[i] = array[j]; 
                    array[j] = temp; 
                }
            }

            int q = i + 1; 
            int temp = array[q];
            array[q] = array[r];
            array[r] = temp;

            if (q - 1 > p) { 
                stack.push(p);
                stack.push(q - 1);
            }
            if (q + 1 < r) {
                stack.push(q + 1);
                stack.push(r);
            }
        }
    }

    public void counting(int[] array) {
        int i = 0, j = 0, k = 0, max = Integer.MIN_VALUE;
        for (i = 0; i < array.length; i++) {
            max = array[i] > max ? array[i] : max;
        }
        int[] counts = new int[max + 1];
        for (i = 0; i < array.length; i++) {
            counts[array[i]]++;
        }
        for (i = 0; i < counts.length; i++) {
            for (j = 0; j < counts[i]; j++) {
                array[k++] = i;
            }
        }
    }
}