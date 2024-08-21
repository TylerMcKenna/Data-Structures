import java.util.ArrayList;

class Fibonacci {
    public static void main(String args[]) {
        ArrayList<Integer> fibNumbers = generateFibonacci(42);
        int lastNum = fibNumbers.get(fibNumbers.size() - 1);
        int lengthNum = getLength(lastNum);

        for(int i = 0; i < fibNumbers.size(); i++) {
            int spaceCount = (lengthNum + 1) - getLength(fibNumbers.get(i));
            for(int n = 0; n < spaceCount; n++) {
                System.out.print("\s");
            }
            System.out.print(fibNumbers.get(i));
        }
    }

    public static int getLength(int number) {
        if (number != 0) {
            return (int)(Math.log10(number) + 1);
        } else return 1;
    }

    public static ArrayList<Integer> generateFibonacci(int n) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(0);
        numbers.add(1);
    
        for (int i = 2; i < n; i++) {
            numbers.add(numbers.get(i-1) + numbers.get(i-2));
        }

        return numbers;
    }
}