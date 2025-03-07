import java.util.*;
import java.io.*;

class Pos {
    int x;
    int y;
    
    Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Simulation_G5_15686 {
    
    static int N, M, min;
    static ArrayList<Pos> chickens, homes;
    static boolean[] open;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        chickens = new ArrayList<>();
        homes = new ArrayList<>();
        min = Integer.MAX_VALUE;

        // 초기 데이터
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                
                // 집, 치킨 좌표
                if (n == 1) homes.add(new Pos(i, j));
                else if (n == 2) chickens.add(new Pos(i, j));
            }
        }

        open = new boolean[chickens.size()];
        dfs(0, 0);
        System.out.println(min);
    }

    private static void dfs(int idx, int depth) {
        if (depth == M) {
            int sum = 0;

            for (int i = 0; i < homes.size(); i++) {
                int temp = Integer.MAX_VALUE;

                // 집에서 오픈된 치킨집 거리 중 최소 거리를 구함
                for (int j = 0; j < chickens.size(); j++) {
                    if (open[j]) {
                        int distance = Math.abs(homes.get(i).x - chickens.get(j).x) + Math.abs(homes.get(i).y - chickens.get(j).y);
                        temp = Math.min(temp, distance);
                    }
                }
                sum += temp;
            }
            min = Math.min(min, sum);
            return;
        }

        // 이렇게 돌리면 중복되지 않고 모든 경우의 수를 돌릴 수 있음
        for (int i = idx; i < chickens.size(); i++) {
            open[i] = true;
            dfs(i + 1, depth + 1);
            open[i] = false;
        }
    }
}