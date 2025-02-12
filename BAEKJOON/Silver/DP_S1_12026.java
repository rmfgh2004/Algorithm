import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    char[] map = br.readLine().toCharArray();

    int[] dp = new int[N];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int i = 0; i < N; i++) {
      if (dp[i] == Integer.MAX_VALUE)
        continue;
      char now = map[i];
      char next;

      if (now == 'B')
        next = 'O';
      else if (now == 'O')
        next = 'J';
      else
        next = 'B';

      for (int j = i + 1; j < N; j++) {
        if (map[j] == next) {
          dp[j] = Math.min(dp[j], dp[i] + (j - i) * (j - i));
        }
      }
    }

    System.out.println(dp[N - 1] == Integer.MAX_VALUE ? -1 : dp[N - 1]);
  }
}
