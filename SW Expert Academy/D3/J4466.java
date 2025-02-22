import java.util.*;
import java.io.*;

public class J4466 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    int turn = 1;
    while (T-- > 0) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Integer[] A = new Integer[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(A, Collections.reverseOrder());
        
        int sum = 0;
        for (int i = 0; i < K; i++) sum += A[i];
        
        bw.write("#" + turn + " " + sum + "\n");
        turn++;
    }

    bw.flush();
    bw.close();
  }
}
