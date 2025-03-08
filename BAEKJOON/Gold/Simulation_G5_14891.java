import java.util.*;
import java.io.*;

public class Simulation_G5_14891 {
    
    static int[][] wheels = new int[4][8];
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < 4; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = line[j] - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            visited = new boolean[4];
            visited[idx] = true;
            dfs(idx, dir);
            
        }
        
        int sum = 0;
        for (int i = 0; i < 4; i++) sum += calculate(i);
        System.out.println(sum);
    }

    private static void dfs(int idx, int dir) {
        
        for (int i = 0; i < 2; i++) {
            int next = idx + (i == 0 ? -1 : 1); // 왼쪽과 오른쪽 순으로 확인함
            if (next >= 0 && next < 4 && !visited[next]) {
                // 현재와 다음 톱니바퀴가 마주보고있는 index를 확인
                if (wheels[next][i == 0 ? 2 : 6] != wheels[idx][i == 0 ? 6 : 2]) {
                    visited[next] = true;
                    // 반대 방향으로 돈다.
                    dfs(next, dir == -1 ? 1 : -1);
                }
            }
        }

        turn(idx, dir);
    }

    private static void turn(int idx, int dir) {

        int[] copyWheels = wheels[idx].clone();
        if (dir == 1) { // 시계 방향
            for (int i = 0; i < 8; i++) {
                wheels[idx][i == 7 ? 0 : i + 1] = copyWheels[i];
            }
        } else { // 반시계 방향
            for (int i = 7; i >= 0; i--) {
                wheels[idx][i == 0 ? 7 : i - 1] = copyWheels[i];
            }
        }
    }

    private static int calculate(int idx) {
        return wheels[idx][0] == 1 ? (int) Math.pow(2, idx) : 0;
    }
}