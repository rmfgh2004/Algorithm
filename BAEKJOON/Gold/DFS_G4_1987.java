import java.util.*;
import java.io.*;

public class DFS_G4_1987 {
    
    static int N, M, max;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static boolean[] visited;
    
	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[100];
        
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            char[] line = str.toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = (int) line[j];
            }
        }
        
        max = 0;
        DFS(0, 0, 1);
        System.out.println(max);
    }
    
    private static void DFS(int x, int y, int len) {
        max = Math.max(len, max);
        visited[map[x][y]] = true;
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                int al = map[nx][ny];
                if (!visited[al]) {
                    DFS(nx, ny, len + 1);        
                    visited[al] = false;
                }
            }
        }
    }
    
}
