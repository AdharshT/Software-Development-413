import java.util.Scanner;
import java.util.Stack;

public class ExpressionEvaluator {
    // Define infix and postfix expressions as constants
    public static final String infixExpression = "((a+b) * (c+d))";
    public static final String postfixExpression = "(ac - b^d+)";

    // Getter methods to access infix expression
    public static String getInfixExpression(){
        return infixExpression;
    }
    // Getter methods to access postfix expression
    public static String getPostfixExpression() {
        return postfixExpression;
    }

    // Main method to start the evaluation process
    public static void main(String[] args){ // initialize ExpressionDriver instance
        ExpressionDriver driver = new ExpressionDriver();
        //start the evaluation process
        driver.evaluateExpression();
    }
}

class ExpressionDriver{
    private Scanner scanner;

    // Constructor initializes Scanner
    public ExpressionDriver(){
        scanner = new Scanner(System.in);
    }
    // Method to evaluate expressions
    public void evaluateExpression(){
        String input;
        do {
            //Prompt the user to enter values for identifiers
            System.out.println("Enter your values for identifiers a, b, c, d:");
            int a = getValue("a");
            int b = getValue("b");
            int c = getValue("c");
            int d = getValue("d");

            //Evaluate infix and postfix expressions with the user values
            int infixResult = InfixEvaluator.evaluateInfix(ExpressionEvaluator.infixExpression, a, b, c, d);
            int postfixResult = PostfixEvaluator.evaluatePostfix(ExpressionEvaluator.postfixExpression, a, b, c, d);

            //Prints result for infix expression
            System.out.println("The value of infix string " + ExpressionEvaluator.infixExpression + " with a = "
                    + a + ", b = " + b + ", c = " + c + ", d = " + d + " is " + infixResult);
            //Prints results for postfix expression
            System.out.println("The value of postfix string " + ExpressionEvaluator.postfixExpression + " with a = "
                    + a + ", b = " + b + ", c = " + c + ", d = " + d + " is " + postfixResult);

            // ask user if they want to compute again
            System.out.println("Add different values? (Y/N):");
            input = scanner.next();
        }
        while (input.equalsIgnoreCase("Y"));

        scanner.close();
        }

        //Method to get value for a specific identifier
        private int getValue(String identifier){
            System.out.print("Enter value for " + identifier + ": ");
            return scanner.nextInt();
    }
}


class InfixEvaluator {
    //Method to evaluate infix expression
    public static int evaluateInfix(String str, int a, int b, int c, int d){
        //Stack to store operators
        Stack<Character> operatorStack = new Stack<>();
        //stack to store operand values
        Stack<Integer> valueStack = new Stack<>();

        // Iterate through each character in the infix expression
        for (char x : str.toCharArray()){

            //If the character is a letter
            if (Character.isLetter(x)){
                switch (x){
                    // Push the corresponding value of the operand onto the value stack
                    case 'a':
                        valueStack.push(a);
                        break;

                    case 'b':
                        valueStack.push(b);
                        break;

                    case 'c':
                        valueStack.push(c);
                        break;

                    case 'd':
                        valueStack.push(d);
                        break;

                    default:
                        break;
                }
            }
            // If the character is an operator
            else if (x == '+' || x == '-' || x == '*' || x == '/') {
                operatorStack.push(x); // Push the operator onto the operator stack
            }
            // If the character is a closing parenthesis
            else if (x == ')'){

                // Pop two values from the value stack
                int val1 = valueStack.pop();
                int val2 = valueStack.pop();

                //Pop an operator from the operator stack
                char operator = operatorStack.pop();

                // Evaluate the expression and push the result onto the value stack
                int result = evaluate(val1, val2, operator);
                valueStack.push(result);
            }
        }
        // Return the final result by popping from the value stack
        return valueStack.pop();
    }
    // Method to perform arithmetic operations
    private static int evaluate(int val1, int val2, char operator){
        switch (operator){
            case '+':
                return val1 + val2;
            case '-':
                return val1 - val2;
            case '*':
                return val1 * val2;
            case '/':
                return val1 / val2;
            default:
                return 0;
        }
    }
}

class PostfixEvaluator {
    // Method to evaluate a postfix expression
    public static int evaluatePostfix(String str, int a, int b, int c, int d) {
        Stack<Integer> stack = new Stack<>(); // Create a stack to hold values

        // Iterate over each character in the postfix expression
        for (char x : str.toCharArray()) {
            if (Character.isLetter(x)) {
                switch (x) {
                    case 'a':
                        stack.push(a); // Push value 'a' onto the stack
                        break;

                    case 'b':
                        stack.push(b); // Push value 'b' onto the stack
                        break;

                    case 'c':
                        stack.push(c); // Push value 'c' onto the stack
                        break;

                    case 'd':
                        stack.push(d); // Push value 'd' onto the stack
                        break;

                    default:
                        break;
                }
            } else if (x == '+' || x == '-' || x == '*' || x == '/') {
                // Pop the top two values from the stack
                int val1 = stack.pop();
                int val2 = stack.pop();

                //Evaluate the operation and push the result back onto the stack
                int answer = evaluate(val1, val2, x);
                stack.push(answer);
            }
        }
        // Final result is the top value remaining on the ctack
        return stack.pop();
    }

    // Method to perform arithmetic operations
    private static int evaluate(int val1, int val2, char operator) {
        switch (operator) {
            case '+':
                return val1 + val2;
            case '-':
                return val1 - val2;
            case '*':
                return val1 * val2;
            case '/':
                return val1 / val2;
            default:
                return 0; // Invalid operator
        }
    }
}

