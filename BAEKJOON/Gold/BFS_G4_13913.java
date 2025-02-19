import java.util.*;
import java.io.*;

public class BFS_G4_13913 {

  static int[] times = new int[100001];
  static int[] location = new int[100001];

  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int K = sc.nextInt();

    // N이 K보다 작을 경우 아래에 해당
    if (N >= K) {
      System.out.println(N - K);
      for (int i = N; i >= K; i--) {
        System.out.print(i + " ");
      }
      return;
    }

    bfs(N, K);
    int index = K;
    Stack<Integer> stack = new Stack<Integer>();
    stack.push(K);

    // Stack의 구조를 이용해 location 이전 위치를 찾아 push
    while (index != N) {
      index = location[index];
      stack.push(index);
    }

    System.out.println(times[K] - 1);
    while (!stack.isEmpty()) {
      System.out.print(stack.pop() + " ");
    }
  }

  private static void bfs(int N, int K) {
    Queue<Integer> q = new LinkedList<Integer>();
    q.offer(N);
    times[N] = 1;

    while (!q.isEmpty()) {
      int now = q.poll();

      if (now == K)
        return;

      for (int i = 0; i < 3; i++) {
        int next = 0;

        if (i == 0)
          next = now - 1;
        else if (i == 1)
          next = now + 1;
        else
          next = now * 2;

        if (next < 0 || next > 100000)
          continue;

        if (times[next] == 0) {
          q.offer(next);
          times[next] = times[now] + 1;
          location[next] = now;
        }
      }
    }
  }
}
