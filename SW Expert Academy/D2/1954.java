import java.util.*;
import java.io.*;

public class Main {

  // 우하좌상
  static int[] dr = { 0, 1, 0, -1 };
  static int[] dc = { 1, 0, -1, 0 };

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    int num = 1;
    while (T-- > 0) {
      int N = Integer.parseInt(br.readLine());
      int[][] snail = new int[N][N];

      int r = 0; // row
      int c = 0; // column
      int dir = 0; // 방향
      for (int i = 1; i <= N * N; i++) {
        snail[r][c] = i;

        // 방향전환
        if (r + dr[dir] < 0 || r + dr[dir] >= N || c + dc[dir] < 0 || c + dc[dir] >= N
            || snail[r + dr[dir]][c + dc[dir]] != 0) {
          dir = (dir + 1) % 4;
        }

        r = r + dr[dir];
        c = c + dc[dir];
      }

      // 출력
      bw.write("#" + num + "\n");
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          bw.write(snail[i][j] + " ");
        }
        bw.write("\n");
      }

      num++;
    }

    bw.flush();
    bw.close();
  }
}
