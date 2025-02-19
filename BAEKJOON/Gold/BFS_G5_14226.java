import java.util.*;
import java.io.*;

class Emoji {
  int now;
  int copy;
  int sec;

  public Emoji(int now, int copy, int sec) {
    this.now = now;
    this.copy = copy;
    this.sec = sec;
  }
}

public class BFS_G5_14226 {

  public static void main(String[] args) throws IOException {

    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();

    System.out.println(bfs(N));
  }

  private static int bfs(int N) {
    Queue<Emoji> q = new LinkedList<Emoji>();
    boolean[][] visited = new boolean[1001][1001];
    q.offer(new Emoji(1, 0, 0));
    visited[1][0] = true;

    while (!q.isEmpty()) {
      Emoji now = q.poll();

      // 종료 조건
      if (now.now == N) return now.sec;

      // 복사
      if (now.now * 2 <= 1000 && !visited[now.now][now.now]) {
        q.add(new Emoji(now.now, now.now, now.sec + 1));
        visited[now.now][now.now] = true;
      }

      // 붙여넣기
      if (now.now + now.copy <= 1000 && !visited[now.now + now.copy][now.copy]) {
        q.add(new Emoji(now.now + now.copy, now.copy, now.sec + 1));
        visited[now.now + now.copy][now.copy] = true;
      }

      // 지우기
      if (now.now > 0 && !visited[now.now - 1][now.copy]) {
        q.add(new Emoji(now.now - 1, now.copy, now.sec + 1));
        visited[now.now - 1][now.copy] = true;
      }
    }

    return -1;
  }
}
