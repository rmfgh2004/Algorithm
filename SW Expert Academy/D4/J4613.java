import java.util.*;
import java.io.*;

public class J4613 {

    static int N, M;
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

            // 초기 셋팅
            for (int i = 0; i < N; i++) {
                char[] line = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    board[i][j] = line[j];
                }
            }

            int cnt = Integer.MAX_VALUE;
            int wCnt = 0;
            int bCnt = 0;
            int rCnt = 0;
            // W 채우기
            for (int i = 0; i < N - 2; i++) {
                wCnt += countColor(i, 'W');
                bCnt = 0;
                // B 채우기
                for (int j = i + 1; j < N - 1; j++) {
                    bCnt += countColor(j, 'B');
                    rCnt = 0;
                    // R 채우기
                    for (int k = j + 1; k < N; k++) {
                        rCnt += countColor(k, 'R');
                    }
                    

                    cnt = Math.min(cnt, wCnt + bCnt + rCnt);
                }
            }

            bw.write("#" + turn + " " + cnt + "\n");
            turn++;
        }

        bw.flush();
        bw.close();
    }

    private static int countColor(int r, char color) {
        int cnt = 0;
        for (int c = 0; c < M; c++) {
            if (board[r][c] != color) cnt++;
        }
        return cnt;
    }

}
