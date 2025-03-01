import java.util.*;
import java.io.*;

public class BFS_G3_2638 {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M;
    static int[][] map;
    static ArrayList<int[]> meltCheeses;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
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

        // 맨 가장자리는 치즈가 놓이지 않음
        map[0][0] = 2;
        airAroundSearch(0, 0);

        int time = 0;
        while (true) {
            meltCheeses = new ArrayList<>();
            for (int i = 0; i < N; i++) { // map 전체에서 치즈 부분만 뽑아서 탐색
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) cheeseAroundSearch(i, j);
                }
            }

            // size가 0이면 녹일 치즈가 없어서 종료
            if (meltCheeses.size() == 0) break;
            else {
                time++;
                melt();
            };
        }

        bw.write(time + "\n");

        bw.flush();
        bw.close();
    }

    // 외부 공기 탐색
    private static void airAroundSearch(int r, int c) {
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c});

        // 치즈로 둘러싸인 공간은 q에 add가 되지 않아 자연스럽게 외부 공기만 탐색 가능
        while (!q.isEmpty()) {

            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                
                // 방문한 곳은 제외하고 외부 공기로 변경
                if (map[nr][nc] == 0) {
                    map[nr][nc] = 2;
                    q.add(new int[] {nr, nc});
                }
            }
        }

    }

    // 치즈 주변 탐색
    private static void cheeseAroundSearch(int r, int c) {

        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] != 2) continue;
            cnt++;
        }

        // 외부공기에 2회 이상 노출 됐다면 녹일치즈에 추가
        if (cnt >= 2) meltCheeses.add(new int[] {r, c});
    }

    private static void melt() {
        for (int[] pos : meltCheeses) {
            map[pos[0]][pos[1]] = 2;
            airAroundSearch(pos[0], pos[1]); // 치즈 녹임과 동시에 외부공기 새로고침
        }
    }

    // 맵 실시간 출력용
    private static void printMap() {
        System.out.println("==============================");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
