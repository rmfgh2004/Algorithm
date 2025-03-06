import java.util.*;
import java.io.*;

public class Simulation_S1_14888 {

    static int N, max, min;
    static int[] nums;
    static boolean[] visited;
    static char[] operators;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        operators = new char[N - 1];
        visited = new boolean[N - 1];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        // 숫자 담기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        // 연산자 담기
        st = new StringTokenizer(br.readLine());
        int a = 0;
        for (int i = 0; i < 4; i++) {
            int M = Integer.parseInt(st.nextToken());
            for (int j = 0; j < M; j++) {
                operators[a] = new char[] {'+', '-', '*', '/'}[i];
                a++;
            }
        }

        // 모든 경우의 수 연산
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

        // visited를 통해 모든 경우의 수 탐색
        for (int i = 0; i < N - 1; i++) {
            if (!visited[i]) {
                int next = calculate(n, nums[depth], operators[i]);
                visited[i] = true;
                dfs(next, depth + 1);
                visited[i] = false;
            }
        }
    }

    // 계산하기
    private static int calculate(int n1, int n2, char o) {
        switch (o) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
            default:
                return n1 / n2;
        }
    }
}