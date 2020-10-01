import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = Math.abs(i - j);
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i][0]);
            for (int j = 1; j < n; j++) {
                System.out.format(" %d", arr[i][j]);
            }
            System.out.println();
        }
    }
}