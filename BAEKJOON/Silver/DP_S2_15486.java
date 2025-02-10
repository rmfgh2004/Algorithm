import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] T = new int[N + 1];
    int[] P = new int[N + 1];
    int[] D = new int[N + 2];

    for (int i = 1; i <= N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      T[i] = Integer.parseInt(st.nextToken());
      P[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = N; i > 0; i--) {
      // 상담이 퇴사 이후에 끝난다면 그 다음 상담의 수익을 저장
      if (T[i] + i > N + 1) {
        D[i] = D[i + 1];
      } else {
        // 이 다음 상담의 수익과 현재 상담 + 그 후 상담의 수익을 비교
        D[i] = Math.max(D[i + 1], P[i] + D[i + T[i]]);
      }
    }

    System.out.println(D[1]);
  }
}
