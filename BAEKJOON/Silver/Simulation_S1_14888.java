import java.util.*;
import java.io.*;

public class Simulation_S1_14888 {

    static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] nums;
    static int[] operators = new int[4];

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        // 숫자 담기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        // 연산자 담기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) operators[i] = Integer.parseInt(st.nextToken());

        dfs(nums[0], 1);
        System.out.println(max + "\n" + min);     
    }

    private static void dfs(int n, int depth) {
        // 연산 다 끝나면 max, min 새로고침
        if (depth == N) {
            max = Math.max(max, n);
            min = Math.min(min, n);
            return;
        }

        // 모든 경우의 수 탐색
        for (int i = 0; i < 4; i++) {
            
            if (operators[i] > 0) {
                
                // 해당 연산자--
                operators[i]--;
                switch (i) {
                    case 0:
                        dfs(n + nums[depth], depth + 1);
                        break;
                    case 1:
                        dfs(n - nums[depth], depth + 1);
                        break;
                    case 2:
                        dfs(n * nums[depth], depth + 1);
                        break;
                    default:
                        dfs(n / nums[depth], depth + 1);
                        break;
                }
                // 해당 연산자++
                operators[i]++;
            }

        }
    }
}