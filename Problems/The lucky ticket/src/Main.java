import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        char[] chars = line.toCharArray();

        char leftSum = 0;
        char rightSum = 0;

        for (int i = 0; i < chars.length / 2; i++) {
            leftSum += chars[i];
        }

        for (int i = chars.length / 2; i < chars.length; i++) {
            rightSum += chars[i];
        }

        if (leftSum == rightSum) {
            System.out.println("Lucky");
        } else {
            System.out.println("Regular");
        }
    }
}
