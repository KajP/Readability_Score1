import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        String[] words = line.split(" ");

        String max = Arrays.stream(words)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println(max);
    }
}