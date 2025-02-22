import java.util.*;
import java.io.*;

public class J2806 {

    static int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N, cnt;
    static int[][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        
        int turn = 1;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            visited = new int[N][N];
            cnt = 0;
            
            dfs(0);

            bw.write("#" + turn + " " + cnt + "\n");
            turn++;
        }

        bw.flush();
        bw.close();
    }

    private static void dfs(int depth) {
        if (depth == N) {
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[depth][i] != 0) continue;

            visit(depth, i, 1);
            dfs(depth + 1);
            visit(depth, i, -1);
        }
        
    }

    // add(1, -1)를 통해 백트래킹 구현
    private static void visit(int x, int y, int add) {
        
        for (int i = 0; i < 8; i++) {
            int ix = x;
            int iy = y;

            // 한 방향으로 계속 탐색
            while (true) {
                int nx = ix + dx[i];
                int ny = iy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
            
                ix = nx;
                iy = ny;
                visited[ix][iy] += add;
            }
        }
    }
}