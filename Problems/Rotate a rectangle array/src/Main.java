import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] inputArr = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                inputArr[i][j] = scanner.nextInt();
            }
        }

        int[][] outputArr = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                outputArr[i][j] = inputArr[n - 1 - j][i];
            }
        }

        printArray(outputArr);
    }

    private static void printArray(int[][] arr) {
        for (int[] ints : arr) {
            System.out.print(ints[0]);
            for (int j = 1; j < ints.length; j++) {
                System.out.format(" %d", ints[j]);
            }
            System.out.println();
        }
    }
}