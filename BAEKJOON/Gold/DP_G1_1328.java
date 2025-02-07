import java.util.*;
import java.io.*;

public class Main {
    
	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        long[][][] NLR = new long[N + 1][L + 1][R + 1];
        
        NLR[1][1][1] = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= L; j++) {
                for (int k = 1; k <= R; k++) {
                    NLR[i][j][k] = (NLR[i - 1][j - 1][k] + NLR[i - 1][j][k - 1] + (NLR[i - 1][j][k] * (i - 2))) % 1000000007;
                }
            }
        }
        
        System.out.println(NLR[N][L][R]);
    }
}
