import java.util.*;
import java.io.*;

public class Main {
    
	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N][M];
        long max = 0;
        
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                dp[i][j] = Integer.parseInt(line.substring(j, j + 1));
                
                if (dp[i][j] == 1 && i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + dp[i][j];
                }
                if (max < dp[i][j]) max = dp[i][j];
            }
        }
        
        System.out.println(max * max);
    }
}
