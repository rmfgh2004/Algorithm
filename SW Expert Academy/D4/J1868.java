import java.util.*;
import java.io.*;

public class J1868 {

    static int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N;
    static char[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        int turn = 1;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];

            for (int i = 0; i < N; i++) {
                char[] line = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) map[i][j] = line[j];
            }

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.') {
                        int bomb = countBomb(i, j); 
                        if (bomb == 0) { // 0개 우선 탐색
                            dfs(i, j);
                            cnt++;
                        }
                    }
                }
            }

            // 나머지 카운트
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.') cnt++;
                }
            }
            
            bw.write("#" + turn + " " + cnt + " \n");

            turn++;
        }

        bw.flush();
        bw.close();
    }

    private static void dfs(int x, int y) {

        int bomb = countBomb(x, y);
        map[x][y] = (char)('0' + bomb);

        if (map[x][y] == '0') { // 0개 이면서 아직 클릭x 이면 dfs
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!isInside(nx, ny) || map[nx][ny] != '.') continue;
                
                dfs(nx, ny);
            }
        }
    }

    // 주변 폭탄 카운트
    private static int countBomb(int x, int y) {

        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!isInside(nx, ny)) continue;

            char data = map[nx][ny];
            if (data == '*') cnt++;
        }

        return cnt;
    }

    // 영역 in 여부
    private static boolean isInside(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N) return false;
        return true;
    }
}
