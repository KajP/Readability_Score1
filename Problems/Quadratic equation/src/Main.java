import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double discriminant = Math.sqrt(b * b - 4 * a * c);

        double x1 = (-b - discriminant) / 2 / a;
        double x2 = (-b + discriminant) / 2 / a;

        if (x1 < x2) {
            System.out.format("%f %f%n", x1, x2);
        } else {
            System.out.format("%f %f%n", x2, x1);
        }
    }
}