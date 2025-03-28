import java.util.*;
import java.io.*;

public class BruteForce_B2_2798 {

    public static void main(String[] args) throws IOException {
  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        int max = -1;
        // for문을 통해 3개로만 구성된 경우의 수를 모두 탐색
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    int now = A[i] + A[j] + A[k];
                    if (now > max && now <= M) max = now;
                }
            }
        }
        
        System.out.println(max);
    }
  }