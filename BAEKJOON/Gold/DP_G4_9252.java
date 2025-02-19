import java.util.*;
import java.io.*;

public class DP_G4_9252 {
    
    static long[][] dp;
    static char[] A, B;
    static ArrayList<Character> path;
    
	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();
        dp = new long[A.length + 1][B.length + 1];
        path = new ArrayList<>();
        
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        System.out.println(dp[A.length][B.length]);
        getText(A.length, B.length);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i));
        }
    }
    
    private static void getText(int i, int j) {
        if (i == 0 || j == 0) return;
        if (A[i - 1] == B[j - 1]) {
            path.add(A[i - 1]);
            getText(i - 1, j - 1);
        } else {
            if (dp[i - 1][j] > dp[i][j - 1]) {
                getText(i - 1, j);
            } else {
                getText(i, j - 1);
            }
        }
    }
    
}
