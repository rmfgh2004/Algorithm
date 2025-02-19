import java.util.*;
import java.io.*;

public class DP_S1_11058 {

  public static void main(String[] args) throws IOException {

    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    long[] dp = new long[N + 1];

    for (int i = 1; i <= N; i++) {
      dp[i] = dp[i - 1] + 1;

      // 6이전은 그냥 AAA ACV 라고 가정하고 시작
      if (i > 6) {
        // ACV -> dp[i - 3] * 2, ACVV -> dp[i - 4] * 3, ACVVV -> dp[i - 5] * 4
        for (int j = 3; j <= 5; j++) {
          dp[i] = Math.max(dp[i], dp[i - j] * (j - 1));
        }
      }
    }

    System.out.print(dp[N]);
  }
}
