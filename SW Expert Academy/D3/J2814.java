import java.util.*;
import java.io.*;

public class J2814 {

    static int N, M, cnt;
    static boolean[] visited;
    static ArrayList<Integer>[] A;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        int turn = 1;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            A = new ArrayList[N + 1];
            cnt = 0;

            for (int i = 1; i <= N; i++) A[i] = new ArrayList<Integer>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                A[s].add(e);
                A[e].add(s);
            }

            
            for (int i = 1; i <= N; i++) {
                visited = new boolean[N + 1];
                visited[i] = true;
                dfs(i, 1);
                
            }

            bw.write("#" + turn + " " + cnt + "\n");
            turn++;
        }

        bw.flush();
        bw.close();
    }

    private static void dfs(int i, int depth) {
        cnt = Math.max(cnt, depth);

        for (int a : A[i]) {
            if (!visited[a]) {
                visited[a] = true;
                dfs(a, depth + 1);
                visited[a] = false;
            }
        }
    }
}
