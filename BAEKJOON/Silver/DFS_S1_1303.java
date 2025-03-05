import java.util.*;
import java.io.*;

public class DFS_S1_1303 {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M, wCnt = 0, bCnt = 0, max;
    static char[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N이 가로, M이 세로여서 반대로 받음
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = line[j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) { // 방문 안한 곳 부터 dfs
                    max = 1; // max로 몇개가 뭉쳐있는지 확인
                    visited[i][j] = true;
                    dfs(i, j);
                    // 다 돌고 제곱 연산 해줌
                    if (board[i][j] == 'W') wCnt += (max * max);
                    else bCnt += (max * max);
                }
            }
        }
        

        System.out.println(wCnt + " " + bCnt);
    }

    private static void dfs(int r, int c) {
        
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            // 같은 팀만 계속 돌 수 있게 함
            if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[r][c] != board[nr][nc] || visited[nr][nc]) continue;

            max++;
            visited[nr][nc] = true;
            dfs(nr, nc);
        }
    }
    
}
