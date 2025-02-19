import java.util.*;
import java.io.*;

public class J1206 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = 10;

    int num = 1;
    while (T-- > 0) {
      int N = Integer.parseInt(br.readLine());
      int[] h = new int[N];

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
        h[i] = Integer.parseInt(st.nextToken());
      }

      int cnt = 0;
      for (int i = 2; i < N - 2; i++) {
        // 양 옆 두 건물 중 가장 높은 높이를 구함
        int max = Math.max(Math.max(h[i - 1], h[i - 2]), Math.max(h[i + 1], h[i + 2]));
        if (h[i] - max > 0)
          cnt += h[i] - max;
      }

      bw.write("#" + num + " " + cnt + "\n");
      num++;
    }

    bw.flush();
    bw.close();
  }
}
