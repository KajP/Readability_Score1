import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] arr = new int[n][n];

        init(arr);

        printArray(arr);
    }

    private static void printArray(int[][] arr) {
        final int n = arr.length;
        for (int[] ints : arr) {
            System.out.print(ints[0]);
            for (int j = 1; j < n; j++) {
                System.out.format(" %d", ints[j]);
            }
            System.out.println();
        }
    }

    private static void init(int[][] arr) {
        final int n = arr.length;
        int value = 1;
        int x = +1;
        int y = 0;

        int k = 0;
        int l;

        for (l = 0; l < n; l++) {
            arr[k][l] = value++;
        }

        l--;
        k++;

        for (int i = n - 1; i > 0; i--) {
            for (int a = 0; a < 2; a++) {
                for (int j = i; j > 0; j--) {
                    arr[k][l] = value++;
                    k += x;
                    l += y;
                }
                k -= x;
                l -= y;
                if (x == 0 && y == +1) {
                    x = +1;
                    y = 0;
                } else if (x == +1) {
                    x = 0;
                    y = -1;
                } else if (x == 0) {
                    x = -1;
                    y = 0;
                } else {
                    x = 0;
                    y = +1;
                }
                k += x;
                l += y;
            }
        }
    }
}