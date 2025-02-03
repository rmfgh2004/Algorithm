import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Integer>[] A;
    static boolean[] visited;
    
	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        A = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        
        for (int i = 1; i < N + 1; i++) A[i] = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            A[s].add(e);
            A[e].add(s);
        }
        
        System.out.println(DFS(1));
    }
    
    private static int DFS(int v) {
        Queue<Integer> q = new LinkedList<Integer>();
        int cnt = 0;
        q.add(v);
        visited[v] = true;
        
        while (!q.isEmpty()) {
            int n = q.poll();
            for (int i : A[n]) {
                if (!visited[i]) {
                    cnt++;
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        
        return cnt;
    }
    
}
