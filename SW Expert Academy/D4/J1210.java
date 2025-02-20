import java.util.*;
import java.io.*;

public class J1210 {
    
    static int[] dx = {-1, 1};
    static int N = 100, now;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = 10;

        while(T-- > 0) {
            int turn = Integer.parseInt(br.readLine());
            board = new int[N][N];
            
            int answer_x = 0;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 2) answer_x = j; // 도착 좌표
                }
            }

            int answer = 0;
            for (int x = 0; x < N; x++) {
                if (board[0][x] == 1) {
                    now = x;
                    dfs(1, x, 0);
                };
                // 사다리 탐색 후 정답에 도착 시 100으로 변경 후 확인
                if (board[N - 1][answer_x] == 100) {
                    answer = x;
                    break;
                }
            }

            bw.write("#" + turn + " " + answer + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void dfs(int y, int x, int dir) {
        // 바닥 도착 시
        if (y == N - 1) {
            if (board[y][x] == 2) board[y][x] = 100; // 도착 시 정답이면 100으로 변경
            return;
        }

        if (dir == 0) { // 하로 가는 중
            int d = 0;
            for (int i = 0; i < 2; i++) {
                int nx = x + dx[i];
                if (nx < 0 || nx >= N || board[y][nx] == 0) continue;
                d = i + 1;
            }
            if (d == 0) {
                dfs(y + 1, x, d);
            } else {
                dfs(y, x + dx[d - 1], d);
            }
        } else if (dir == 1) { // 좌로 가는 중
            int nx = x + dx[0];
            if (nx < 0 || board[y][nx] == 0) {
                dfs(y + 1, x, 0);
            } else {
                dfs(y, nx, 1);
            }
        } else if (dir == 2) { // 우로 가는 중
            int nx = x + dx[1];
            if (nx >= N || board[y][nx] == 0) {
                dfs(y + 1, x, 0);
            } else {
                dfs(y, nx, 2);
            }
        }
    }
}