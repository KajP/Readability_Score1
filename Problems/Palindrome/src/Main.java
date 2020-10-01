import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        boolean palindrome = new StringBuilder(line).reverse().toString().equals(line);

        if (palindrome) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}