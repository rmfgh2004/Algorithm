import java.util.*;
import java.io.*;

class Space {
  int x;
  int y;
  int cnt;

  public Space(int x, int y, int cnt) {
    this.x = x;
    this.y = y;
    this.cnt = cnt;
  }
}

public class Main {

  static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
  static int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };
  static int N, M;
  static int[][] map;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int max = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] == 0)
          max = Math.max(max, bfs(i, j));
      }
    }

    System.out.println(max);
  }

  private static int bfs(int x, int y) {
    Queue<Space> q = new LinkedList<Space>();
    boolean[][] visited = new boolean[N][M];
    q.add(new Space(x, y, 0));

    while (!q.isEmpty()) {
      Space now = q.poll();

      for (int i = 0; i < 8; i++) {
        int nx = now.x + dx[i];
        int ny = now.y + dy[i];

        if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny])
          continue;

        if (map[nx][ny] == 0) {
          q.add(new Space(nx, ny, now.cnt + 1));
          visited[nx][ny] = true;
        } else {
          return now.cnt + 1;
        }
      }
    }

    return -1;
  }
}
