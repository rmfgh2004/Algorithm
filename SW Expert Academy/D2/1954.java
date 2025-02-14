import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    int num = 1;
    while (T-- > 0) {
      int N = Integer.parseInt(br.readLine());
      int[][] snail = new int[N][N];

      // 가로
      int x = 0;
      // 세로
      int y = 0;
      // 2 : ↓, 4 : ←, 6 : →, 8 : ↑
      int direction = 6;
      snail[0][0] = 1;
      for (int i = 2; i <= N * N; i++) {

        switch (direction) {
          case 2:
            y++;
            break;
          case 4:
            x--;
            break;
          case 6:
            x++;
            break;
          case 8:
            y--;
            break;
        }

        // 범위 초과 시 방향 변경
        if (x >= N || x < 0 || y >= N || y < 0) {

          if (x >= N) {
            direction = 2;
            x--;
            y++;
          } else if (x < 0) {
            direction = 8;
            x++;
            y--;
          } else if (y >= N) {
            direction = 4;
            y--;
            x--;
          } else if (y < 0) {
            direction = 6;
            y++;
            x++;
          }
          ;
        }

        // 다음 칸이 빈칸이 아닐 시 방향 변경
        if (snail[y][x] != 0) {
          if (direction == 2) {
            direction = 4;
            y--;
            x--;
          } else if (direction == 4) {
            direction = 8;
            x++;
            y--;
          } else if (direction == 6) {
            direction = 2;
            x--;
            y++;
          } else if (direction == 8) {
            direction = 6;
            y++;
            x++;
          }
        }

        snail[y][x] = i;
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
