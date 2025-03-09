import java.util.*;
import java.io.*;

public class Simulation_G3_14890 {

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int N, L, cnt;
    static int[][] map;
    static boolean[][] visited; // 경사로 설치를 확인
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];
        search_row();
        visited = new boolean[N][N];
        search_column();
        
        System.out.println(cnt);
    }

    // 탐색 가로
    private static void search_row() {
        
        for (int r = 0; r < N; r++) {

            for (int c = 0; c < N - 1; c++) {
                int nc = c + dc[0];
                int now = map[r][c];

                // 경사가 2이상 차이나면 컷
                if (Math.abs(map[r][nc] - now) >= 2) break;

                // 나보다 다음이 낮을 경우 경사로 설치가 가능한지 확인
                if (map[r][nc] == now - 1) {
                    if (visited[r][nc]) break;
                    
                    // 경사로 설치 기록
                    if (!buildWay(r, nc, 0)) break;

                    // 경사로 설치한 만큼 스킵
                    c += L - 1;
                } else if (map[r][nc] == now + 1) { // 높을 경우 지나온길에 L만큼 경사로 설치가 가능한지 확인
                    if(visited[r][c]) break;

                    // 경사로 설치 가능 여부
                    if (!buildWay(r, c, 1)) break;
                }

                if (c == N - 2) { // 끝까지 도착
                    cnt++;
                    break;
                };
            }
        }
    }

    // 탐색 세로 
    private static void search_column() {
        
        for (int c = 0; c < N; c++) {

            for (int r = 0; r < N - 1; r++) {

                int nr = r + dr[2];
                int now = map[r][c];

                // 경사가 2이상 차이나면 컷
                if (Math.abs(map[nr][c] - now) >= 2) break;

                // 나보다 다음이 낮을 경우 경사로 설치가 가능한지 확인
                if (map[nr][c] == now - 1) {
                    if (visited[nr][c]) break;
                    
                    // 경사로 설치 가능 여부
                    if (!buildWay(nr, c, 2)) break;

                    // 경사로 설치한 만큼 스킵
                    r += L - 1;
                } else if (map[nr][c] == now + 1) { // 높을 경우 지나온길에 L만큼 경사로 설치가 가능한지 확인
                    if(visited[r][c]) break;

                    // 경사로 설치 기록
                    if (buildWay(r, c, 3)) {
                        for (int i = r; i > r - L; i--) {
                            visited[i][c] = true;
                        }
                    } else break; // 불가능하면 스킵
                }

                // 끝까지 도착하면
                if (r == N - 2) {
                    cnt++;
                };
            }
        }
    }

    // 경사로 설치가 가능한지 확인
    private static boolean buildWay(int r, int c, int dir) {
        int h = map[r][c];
        visited[r][c] = true;

        for (int i = 0; i < L - 1; i++) {
            r += dr[dir];
            c += dc[dir];
            
            if (!isRange(r, c) || map[r][c] != h || visited[r][c]) return false;
            visited[r][c] = true;
        }

        return true;
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
    
}