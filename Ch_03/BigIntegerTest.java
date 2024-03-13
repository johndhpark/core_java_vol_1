import java.math.*;
import java.util.*;

public class BigIntegerTest {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("How many numbers do you need to draw? ");
            int k = in.nextInt();

            System.out.print("What is the highest number you can draw? ");
            BigInteger n = in.nextBigInteger();

            // compute binomial coefficient

            BigInteger lotteryOdds = BigInteger.ONE;

            for (int i = 1; i <= k; ++i) {
                lotteryOdds = lotteryOdds.multiply(BigInteger.valueOf(i - 1))
                        .divide(BigInteger.valueOf(i));
            }

            System.out.printf("Your odss are 1 in %s. Good luck!%n", lotteryOdds);
        }

    }
}