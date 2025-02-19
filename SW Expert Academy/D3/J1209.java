import java.util.*;
import java.io.*;

public class J1209 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = 10;
    int N = 100;

    while (T-- > 0) {
      int turn = Integer.parseInt(br.readLine());
      int[][] map = new int[N][N];

      // 입력
      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      int max_sum = -1;
      int rdsum = 0;
      int ldsum = 0;
      for (int i = 0; i < N; i++) {
        int rsum = 0;
        int csum = 0;
        // 가로세로
        for (int j = 0; j < N; j++) {
          rsum += map[i][j];
          csum += map[j][i];
        }
        max_sum = Math.max(max_sum, Math.max(rsum, csum));

        // 대각선
        rdsum += map[i][i];
        ldsum += map[i][99 - i];
      }
      max_sum = Math.max(max_sum, Math.max(rdsum, ldsum));

      bw.write("#" + turn + " " + max_sum + "\n");
    }

    bw.flush();
    bw.close();
  }
}
