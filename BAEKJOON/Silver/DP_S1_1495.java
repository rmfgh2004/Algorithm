import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int[] A = new int[N + 1];
    for (int i = 1; i <= N; i++)
      A[i] = Integer.parseInt(st.nextToken());

    int[] dp = new int[M + 1];
    Arrays.fill(dp, -1);
    dp[S] = 0;
    int answer = -1;

    for (int n = 1; n <= N; n++) {
      List<Integer> list = new ArrayList<Integer>();

      // 현재 가능한 소리에서 변화 가능한 소리를 찾아서 list.add();
      for (int m = 0; m <= M; m++) {
        if (dp[m] == n - 1) {
          if (m + A[n] <= M)
            list.add(m + A[n]);
          if (m - A[n] >= 0)
            list.add(m - A[n]);
        }
      }

      // 변화된 소리 dp에 뮤직index 추가;
      for (int i = 0; i < list.size(); i++) {
        int s = list.get(i);
        dp[s] = n;
        if (n == N)
          answer = Math.max(answer, s);
      }
    }

    System.out.println(answer);
  }
}
