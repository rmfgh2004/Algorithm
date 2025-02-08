import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    // N이 K보다 큰 경우는 뒤로만 이동 가능해서 아래에 해당
    if (N >= K) {
      System.out.println(N - K);
      return;
    }

    System.out.println(bfs(N, K));
    ;
  }

  private static int bfs(int N, int K) {
    Queue<int[]> q = new LinkedList<int[]>();
    int[] times = new int[100001];
    q.offer(new int[] { N, 0 });

    while (!q.isEmpty()) {
      int[] now = q.poll();

      for (int i = 0; i < 3; i++) {
        int next;
        int sec;

        // 순간이동은 시간증가가 없기에 먼저 돌아야 최소 시간을 구할 수 있음
        if (i == 0) {
          next = now[0] * 2;
          sec = now[1];
        } else if (i == 1) {
          next = now[0] - 1;
          sec = now[1] + 1;
        } else {
          next = now[0] + 1;
          sec = now[1] + 1;
        }

        if (next < 0 || next > 100000)
          continue;

        if (next == K) {
          return sec;
        }

        if (times[next] == 0 || times[next] >= times[now[0]] + 1) {
          q.add(new int[] { next, sec });
          times[next] = sec;
        }
      }
    }

    return -1;
  }
}
