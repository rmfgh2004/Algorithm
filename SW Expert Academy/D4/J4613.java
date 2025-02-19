import java.util.*;
import java.io.*;

public class J4613 {
    
    static char[] colors = {'W', 'B', 'R'};

    static int N, M, minCnt;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int turn = 1;
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new char[N][M];
            
            int cnt = 0; // 색칠 개수
            minCnt = Integer.MAX_VALUE;

            // 초기 셋팅
            for (int i = 0; i < N; i++) {
                char[] line = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    board[i][j] = line[j];

                    // 처음은 only White
                    if (i == 0 && board[i][j] != 'W') cnt++;

                    // 마지막은 only Red
                    if (i == N - 1 && board[i][j] != 'R') cnt++;
                }
            }

            dfs(1, cnt, 0);
            dfs(1, cnt, 1);

            bw.write("#" + turn + " " + minCnt + "\n");
            turn++;
        }

        bw.flush();
        bw.close();
    }

    private static void dfs(int row, int cnt, int idx) {

        if (row >= N - 1) {
            minCnt = Math.min(cnt, minCnt);
            return;
        };

        if (row == N - 2 && idx == 0) idx = 1;
            
        for (int c = 0; c < M; c++) {
            if (board[row][c] != colors[idx]) cnt++;
        }

        // 색칠 가능한 색으로 모든 경우 돌리기
        if (idx == 0) {
            dfs(row + 1, cnt, 0);
            dfs(row + 1, cnt, 1);
        } else if (idx == 1) {
            dfs(row + 1, cnt, 1);
            dfs(row + 1, cnt, 2);
        } else if (idx == 2) {
            dfs(row + 1, cnt, 2);
        }

    }

}
