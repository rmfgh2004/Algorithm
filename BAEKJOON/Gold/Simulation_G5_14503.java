import java.util.*;
import java.io.*;

class Robot {
    int r;
    int c;
    int d;

    public Robot(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}

public class Simulation_G5_14503 {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M, clear;
    static int[][] map;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clear = 0;
        play(new Robot(r, c, d));
        System.out.println(clear);
    }

    private static void play(Robot robot) {
        Queue<Robot> q = new LinkedList<>();
        q.offer(robot);

        while(!q.isEmpty()) {
            Robot now = q.poll();
            int d = now.d;

            // 현재 칸 청소
            if (map[now.r][now.c] == 0) {
                map[now.r][now.c] = 2;
                clear++;
            }

            for (int i = 0; i < 4; i++) {
                d = turnDirection(d); // 현재 바라보고 있는 방향 기준으로 4방향 돌리기
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                
                // 맵 범위 벗어나거나 청소x 빈 칸이 아니라면 스킵
                if (isNotInMap(nr, nc) || map[nr][nc] != 0) continue;

                q.add(new Robot(nr, nc, d));
                break;
            }
            
            // 4방향 청소할 곳이 없다면
            if (q.isEmpty()) {
                int backD = turnDirection(turnDirection(now.d));
                int nr = now.r + dr[backD];
                int nc = now.c + dc[backD];
                if (!isNotInMap(nr, nc) && map[nr][nc] != 1) { // 후진
                    q.add(new Robot(nr, nc, now.d));
                }
                // 뒤가 벽이면 알아서 종료
            }
        }
    }

    // 반시계 방향 돌리기
    private static int turnDirection(int d) {
        d--;
        if (d == -1) return 3;
        return d;
    }

    // 맵 in 여부
    private static boolean isNotInMap(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}