import java.util.ArrayList;

class TwoMcKenna {
    public static void main(String args[]) {

        if (args.length < 1) {
            System.out.println("Please enter n.");
            return;
        }

        int userPassedValue = Integer.parseInt(args[0]);

        ArrayList<Integer> fibNumbers = generateFibonacci(userPassedValue);
        // Gets length of last number in the array of fibonacci numbers to space output right
        int lastNum = fibNumbers.get(fibNumbers.size() - 1);
        int lengthNum = getLength(lastNum);

        // Generates cool looking output
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

    // Very simple loop to get fibonacci numbers, just adds last 2 numbers together and sets current i to that number
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