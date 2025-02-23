import java.util.*;
import java.io.*;

class Pos {
    int x;
    int y;
    
    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class J1226 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N = 16;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = 10;

        while (T-- > 0) {
            int turn = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(line.substring(j, j + 1));
                }
            }
            
            bw.write("#" + turn + " " + bfs() + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static int bfs() {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(1, 1));
        visited[1][1] = true;

        while (!q.isEmpty()) {
            Pos now = q.poll();
            int x = now.x;
            int y = now.y;

            // 도착 조건
            if (map[now.x][now.y] == 3) return 1;

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new Pos(nx, ny));
            }
        }

        // 도착 실패
        return 0;
    }
}
