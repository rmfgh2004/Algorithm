import java.util.*;
import java.io.*;

public class Simulation_G4_14502 {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M, max;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0);
        System.out.println(max);
    }

    // 벽 세우기
    private static void dfs(int wallCnt) {

        // 벽 3개 세우면 바이러스 전염 및 안전구역 카운트
        if (wallCnt == 3) {
            bfs();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(wallCnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    // 바이러스 전염
    private static void bfs() {
        int[][] virusMap = new int[N][M];
        Queue<int[]> q = new LinkedList<>();

        // 바이러스 전염를 위한 새 배열 생성
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                virusMap[i][j] = map[i][j];
                // 바이러스 전염을 위한 배열 저장
                if (virusMap[i][j] == 2) q.offer(new int[] {i, j});
            }
        }
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];

                if (isNotInMap(nr, nc) || virusMap[nr][nc] != 0) continue;
            
                // 전염
                q.add(new int[] {nr, nc});
                virusMap[nr][nc] = 2;
            }
        }

        countSpace(virusMap);
    }

    // 맵 in 여부
    private static boolean isNotInMap(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    // 안전공간 카운트
    private static void countSpace(int[][] virusMap) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (virusMap[i][j] == 0) cnt++;
            }
        }
        // 많은 안전공간 새로고침
        max = Math.max(max, cnt);
    }
}
