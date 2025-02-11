import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[][] map = new int[N][N];

    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    long[][] dp = new long[N][N];
    dp[0][0] = 1;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        int now = map[i][j];

        // 0이라는 뜻은 현재 좌표로 오는 경우의 수가 없다는 뜻
        if (now == 0)
          break;

        // 오른쪽으로 가는 경우의 수
        if (now + j < N)
          dp[i][j + now] += dp[i][j];

        // 아래쪽으로 가는 경우의 수
        if (now + i < N)
          dp[i + now][j] += dp[i][j];
      }
    }

    System.out.println(dp[N  - 1][N - 1]);
  }
}
