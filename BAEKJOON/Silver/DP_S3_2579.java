import java.util.*;
import java.io.*;

public class Main {

  static int[] A;
  static long[] dp;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    A = new int[N + 1];
    dp = new long[N + 1];

    for (int i = 1; i <= N; i++) {
      A[i] = Integer.parseInt(br.readLine());
    }

    dp[1] = A[1];

    if (N >= 2) {
      dp[2] = A[1] + A[2];
    }

    for (int i = 3; i <= N; i++) {
      dp[i] = Math.max(dp[i - 2], dp[i - 3] + A[i - 1]) + A[i];
    }

    System.out.println(dp[N]);
  }
}
