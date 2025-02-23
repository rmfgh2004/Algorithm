import java.util.*;
import java.io.*;

public class J1219 {

    static int N = 100, M;
    static ArrayList<Integer>[] A;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = 10;

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int turn = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            A = new ArrayList[N];
            visited = new boolean[N];

            for (int i = 0; i < N; i++) A[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                A[s].add(e);
            }

            bw.write("#" + turn + " " + bfs() + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        visited[0] = true;
        
        while (!q.isEmpty()) {
            int n = q.poll();

            if (n == 99) return 1;

            for (int a : A[n]) {
                if (!visited[a]) {
                    q.add(a);
                    visited[a] = true;
                }
            }
        }

        return 0;
    }
}
