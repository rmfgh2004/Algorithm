import java.util.*;
import java.io.*;

public class J1961 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    int turn = 1;
    while (T-- > 0) {
      int N = Integer.parseInt(br.readLine());
      int[][][] map = new int[4][N][N]; // 0 90 180 270 배열

      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          map[0][i][j] = Integer.parseInt(st.nextToken());
        }
      }

      for (int i = 1; i < 4; i++) {
        for (int j = 1; j <= N; j++) {
          for (int k = 0; k < N; k++) {
            // 90도는 0도에서 돌리고 180도는 90도에서 90도만 돌림
            map[i][k][N - j] = map[i - 1][j - 1][k];
          }
        }
      }

      // 출력
      bw.write("#" + turn + "\n");
      for (int i = 0; i < N; i++) {
        for (int j = 1; j < 4; j++) {
          for (int k = 0; k < N; k++) {
            bw.write(map[j][i][k] + "");
          }
          bw.write(" ");
        }
        bw.write("\n");
      }

      turn++;
    }

    bw.flush();
    bw.close();
  }
}
