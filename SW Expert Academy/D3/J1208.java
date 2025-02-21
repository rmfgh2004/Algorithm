import java.util.*;
import java.io.*;

public class J1208 {

    static int[] h;
    static int N = 100;
    static int dump;

    public static void main(String[] args) throws IOException {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      int T = 10;

      int turn = 1;
      while (T-- > 0) {
        int dump = Integer.parseInt(br.readLine());
        h = new int[100];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) h[i] = Integer.parseInt(st.nextToken());
        
        // 옮기는 작업
        while (dump-- > 0) {
            h[findMax()]--;
            h[findMin()]++;
        }

        // 차이 구하기
        int result = h[findMax()] - h[findMin()];
        bw.write("#" + turn + " " + result + "\n");
        turn++;
      }

      bw.flush();
      bw.close();
  }

  private static int findMax() {
    int idx = 0;
    for (int i = 1; i < N; i++) {
        if (h[idx] < h[i]) idx = i;
    }
    return idx;
  }

  private static int findMin() {
    int idx = 0;
    for (int i = 1; i < N; i++) {
        if (h[idx] > h[i]) idx = i;
    }
    
    return idx;
  }
  
}
