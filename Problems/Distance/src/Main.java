import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double distance = scanner.nextDouble(); // miles
        double time = scanner.nextDouble(); // hours

        System.out.println(distance / time);
    }
}