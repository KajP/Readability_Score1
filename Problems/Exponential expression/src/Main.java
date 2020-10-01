import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double x = scanner.nextDouble();
        double result = ((x + 1) * x + 1) * x + 1;
        System.out.println(result);
    }
}