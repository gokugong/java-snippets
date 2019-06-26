package misc;

public class Factorial
{
    public static int factorial(int num) {
        if (num < 1)
            return 1;
        return num * factorial(num - 1);
    }

    public static int factorial2(int num) {
        int result = 1;
        for (int i = num; i > 0; i--)
            result *= i;
        return result;
    }
    public static void main(String args[]) {
        System.out.println("Factorial of 8 = " + factorial(8));
        System.out.println("Factorial of 8 = " + factorial2(8));
    }
}
