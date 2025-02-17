import java.util.*;
import java.io.*;

public class Main {

  static int[] dx = { 0, 1, 0, -1 };
  static int[] dy = { 1, 0, -1, 0 };
  static int N, M;
  static String[][] map;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    int turn = 1;
    while (T-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      map = new String[N][M];

      for (int i = 0; i < N; i++) {
        String line = br.readLine();
        for (int j = 0; j < M; j++) {
          map[i][j] = line.substring(j, j + 1);
        }
      }

      boolean isYes = bfs();

      bw.write("#" + turn + " " + (isYes ? "YES" : "NO") + "\n");
      turn++;
    }

    bw.flush();
    bw.close();
  }

  private static boolean bfs() {
    Queue<Item> q = new LinkedList<Item>();
    q.offer(new Item(0, 0, 0, 0));
    boolean[][][][] visited = new boolean[16][4][N][M]; // 같은 숫자로 두 번째 방문이라면 가능성 아웃

    while (!q.isEmpty()) {
      Item item = q.poll();
      String data = map[item.x][item.y];

      // 중복 방문이라도 다음 방문 확인을 위해 return 대신 continue;
      if (visited[item.n][item.dir][item.x][item.y])
        continue;

      visited[item.n][item.dir][item.x][item.y] = true;

      // 조건문
      if (data.equals("<")) {
        item.dir = 2;
      } else if (data.equals(">")) {
        item.dir = 0;
      } else if (data.equals("^")) {
        item.dir = 3;
      } else if (data.equals("v")) {
        item.dir = 1;
      } else if (data.equals("_")) {
        item.dir = (item.n == 0 ? 0 : 2);
      } else if (data.equals("|")) {
        item.dir = (item.n == 0 ? 1 : 3);
      } else if (data.equals("?")) {
        for (int i = 0; i < 4; i++) {
          int nx = item.x + dx[i];
          int ny = item.y + dy[i];

          // 범위 계산
          if (nx < 0)
            nx = N - 1;
          else if (nx >= N)
            nx = 0;

          if (ny < 0)
            ny = M - 1;
          else if (ny >= M)
            ny = 0;

          q.offer(new Item(nx, ny, item.n, i));
        }
        continue;
      } else if (data.equals(".")) {

      } else if (data.equals("@")) {
        return true;
      } else if (data.equals("+")) {
        if (item.n == 15)
          item.n = 0;
        else
          item.n++;
      } else if (data.equals("-")) {
        if (item.n == 0)
          item.n = 15;
        else
          item.n--;
      } else {
        item.n = Integer.parseInt(map[item.x][item.y]);
      }

      int nx = item.x + dx[item.dir];
      int ny = item.y + dy[item.dir];

      // 범위 계산
      if (nx < 0)
        item.x = N - 1;
      else if (nx >= N)
        item.x = 0;
      else
        item.x = nx;

      if (ny < 0)
        item.y = M - 1;
      else if (ny >= M)
        item.y = 0;
      else
        item.y = ny;

      q.add(new Item(item.x, item.y, item.n, item.dir));

    }

    return false;
  }
}

class Item {
  int x;
  int y;
  int n;
  int dir; // 방향 우하좌상

  public Item(int x, int y, int n, int dir) {
    this.x = x;
    this.y = y;
    this.n = n;
    this.dir = dir;
  }
}
