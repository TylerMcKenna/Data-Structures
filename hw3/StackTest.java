import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(3);
        test.push(21);
        test.push(4);
        test.pop();
        
        System.out.println(test.toString());
    }
}