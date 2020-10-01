import java.util.Arrays;
import java.util.IntSummaryStatistics;

/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        // write your code here
        long result;
        IntSummaryStatistics statistics = Arrays.stream(args, 1, args.length)
                .mapToInt(Integer::parseInt).summaryStatistics();
        switch (operator) {
            case "MAX":
                result = statistics.getMax();
                break;
            case "MIN":
                result = statistics.getMin();
                break;
            case "SUM":
                result = statistics.getSum();
                break;
            default:
                result = 0;
                break;
        }
        System.out.println(result);
    }
}