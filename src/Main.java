import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println ("Введите число:");
        int firstNumber = new Scanner(System.in).nextInt();
        int secondNumber = new Scanner(System.in).nextInt();
        double quotient = (double) firstNumber/secondNumber;
        System.out.println (firstNumber-secondNumber);
        System.out.println (firstNumber+secondNumber);
        System.out.println (firstNumber*secondNumber);
        System.out.println (quotient);
    }
}