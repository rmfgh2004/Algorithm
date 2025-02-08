import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    // N이 K보다 크다면 -1만 가능하므로 아래에 해당
    if (N >= K) {
      System.out.println(N - K + "\n1");
      return;
    }

    int[] result = bfs(N, K);
    System.out.println(result[0] + "\n" + result[1]);
  }

  private static int[] bfs(int N, int K) {
    Queue<Integer> q = new LinkedList<Integer>();
    int min_sec = Integer.MAX_VALUE;
    int cnt = 0;
    int[] times = new int[100001];

    q.offer(N);
    times[N] = 1;

    while (!q.isEmpty()) {
      int now = q.poll();

      // 최소 시간보다 시간이 크다면 더 이상 탐색X
      if (min_sec < times[now])
        return new int[] { min_sec, cnt };

      for (int i = 0; i < 3; i++) {
        int next;

        if (i == 0)
          next = now + 1;
        else if (i == 1)
          next = now - 1;
        else
          next = now * 2;

        if (next < 0 || next > 100000)
          continue;

        // 목적지 도착
        // 어차피 첫 발견한 시점이 가장 짧은 시간대라 예외 처리 필요X
        if (next == K) {
          min_sec = times[now];
          cnt++;
        }

        // next가 첫 방문 or 시간이 똑같다면 탐색
        if (times[next] == 0 || times[next] == times[now] + 1) {
          q.add(next);
          times[next] = times[now] + 1;
        }
      }
    }

    return new int[] { min_sec, cnt };
  }
}
