import java.util.*;
import java.io.*;

public class Main {
    
	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[][] dp = new int[N][2];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        int max = A[0];
        dp[0][0] = A[0];
        dp[0][1] = A[0];
        
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(A[i], dp[i - 1][0] + A[i]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + A[i]);
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }
        
        
        System.out.println(max);
    }
    
}
