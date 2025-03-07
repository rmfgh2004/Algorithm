import java.util.*;
import java.io.*;

public class Simulation_G5_15686 {
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M, min;
    static ArrayList<int[]> chickens, homes;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        int[][] map = new int[N][N];
        chickens = new ArrayList<>();
        homes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = Integer.MAX_VALUE;
                if (n == 1) homes.add(new int[] {i, j});
                else if (n == 2) chickens.add(new int[] {i, j});
            }
        }

        visited = new boolean[chickens.size()];
        dfs(0, 0, map);
        
        System.out.println(min);
    }

    private static void dfs(int idx, int depth, int[][] map) {

        int[] now = chickens.get(idx);
        int[][] copyMap = copy(map);
        if(depth != 0) bfs(now[0], now[1], copyMap);

        if (depth == M) {
            int sum = 0;
            for (int[] pos : homes) {
                sum += copyMap[pos[0]][pos[1]];
            }
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < chickens.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, depth + 1, copyMap);
                visited[i] = false;
            }
        }     
    }

    private static void bfs(int r, int c, int[][] map) {
        Queue<int[]> q = new LinkedList<>();
        map[r][c] = 0;
        q.offer(new int[] {r, c});
        
        while(!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                
                if (isInMap(nr, nc) && (map[nr][nc] == Integer.MAX_VALUE || map[nr][nc] > map[now[0]][now[1]] + 1)) {
                    map[nr][nc] = Math.min(map[nr][nc], map[now[0]][now[1]] + 1);
                    q.add(new int[] {nr, nc});
                }
            }
        }

    }

    private static int[][] copy(int[][] map) {
        int[][] copyMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }
        return copyMap;
    }

    private static boolean isInMap(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static void printMap(int[][] map) {
        System.out.println("==============================");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        
    }
}