import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(br.readLine());
    int[][] dp = new int[11][4];
    dp[1][1] = 1;
    dp[2][1] = 1;
    dp[2][2] = 1;
    dp[3][1] = 2;
    dp[3][2] = 1;
    dp[3][3] = 1;

    for (int i = 4; i <= 10; i++) {
      // N-(1, 2, 3)을 구하는 1, 2, 3으로 끝나는 경우의 수를 다 더함
      dp[i][1] = dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3];
      dp[i][2] = dp[i - 2][1] + dp[i - 2][2] + dp[i - 2][3];
      dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
    }

    while (T-- > 0) {
      int N = Integer.parseInt(br.readLine());
      sb.append(dp[N][1] + dp[N][2] + dp[N][3]).append("\n");
    }
    System.out.println(sb);
  }
}
