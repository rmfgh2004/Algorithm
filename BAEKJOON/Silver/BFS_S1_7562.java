import java.util.*;
import java.io.*;

class Point {
  int x;
  int y;
  int cnt;

  public Point(int x, int y, int cnt) {
    this.x = x;
    this.y = y;
    this.cnt = cnt;
  }
}

public class Main {

  // 나이트 이동 가능한 경로
  static int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
  static int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };
  static int N;
  static int[][] map;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());
    while (T-- > 0) {
      N = Integer.parseInt(br.readLine());
      map = new int[N][N];
      StringTokenizer st = new StringTokenizer(br.readLine());
      int sx = Integer.parseInt(st.nextToken());
      int sy = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(br.readLine());
      int ex = Integer.parseInt(st.nextToken());
      int ey = Integer.parseInt(st.nextToken());

      sb.append(BFS(new Point(sx, sy, 0), new Point(ex, ey, 0)) + " \n");
    }

    System.out.println(sb);
  }

  public static int BFS(Point sp, Point ep) {
    Queue<Point> q = new LinkedList<Point>();
    q.offer(sp);
    map[sp.x][sp.y] = 1;

    while (!q.isEmpty()) {
      Point p = q.poll();

      // 목적지 도착
      if (p.x == ep.x && p.y == ep.y) {
        return p.cnt;
      }

      for (int i = 0; i < 8; i++) {
        Point np = new Point(p.x + dx[i], p.y + dy[i], p.cnt + 1);
        if (np.x < 0 || np.x >= N || np.y < 0 || np.y >= N || map[np.x][np.y] == 1)
          continue;

        // 방문 확인을 위한 1로 변경
        map[np.x][np.y] = 1;
        q.offer(np);
      }
    }

    return -1;
  }
}
