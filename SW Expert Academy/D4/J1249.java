import java.util.*;
import java.io.*;

class Road {
  int x;
  int y;
  int time;

  public Road(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class J1249 {

  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, -1, 0, 1}; 
  static int N, min;
  static int[][] map, minMap;
  static boolean[][] visited;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    int turn = 1;
    while (T-- > 0) {
      N = Integer.parseInt(br.readLine());
      map = new int[N][N];
      visited = new boolean[N][N];
      minMap = new int[N][N];
      min = Integer.MAX_VALUE;

      for (int i = 0; i < N; i++) {
        String line = br.readLine();
        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(line.substring(j, j + 1));
          minMap[i][j] = Integer.MAX_VALUE;
        }
      }

      bfs();
      bw.write("#" + turn + " " + min + "\n");
      turn++;
    }

    bw.flush();
    bw.close();
  }

  private static void bfs() {
    Queue<Road> q = new LinkedList<>();
    q.offer(new Road(0, 0));
    visited[0][0] = true;
    minMap[0][0] = 0;
    
    while (!q.isEmpty()) {
      Road now = q.poll();
      int x = now.x;
      int y = now.y;

      // min 수정
      if (x == N - 1 && y == N - 1) min = Math.min(minMap[N - 1][N - 1], min);
      // 현재 탐색중인 도로가 이미 min보다 크다면 탐색X
      if (min <= minMap[x][y]) continue;
      
      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
          if (!visited[nx][ny] || minMap[nx][ny] > minMap[x][y] + map[nx][ny]) {
            visited[nx][ny] = true;
            minMap[nx][ny] = minMap[x][y] + map[nx][ny];
            q.offer(new Road(nx, ny));
          }
        }
      }
    }
  }
}
