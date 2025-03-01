import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
    int vertex;
    int value;

    Edge(int next, int value) {
        this.vertex = next;
        this.value = value;
    }

    @Override
    public int compareTo(Edge e) {
        return value - e.value;
    }
}

public class Dijkstra_G3_1238 {

    static int N, M, X;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // 반대로 가는 루트를 구함으로 인해 모두 출발점을 X로 두고 풀이 가능
        ArrayList<Edge>[] list = new ArrayList[N + 1];
        ArrayList<Edge>[] reverse_list = new ArrayList[N + 1];
        
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
            reverse_list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            list[a].add(new Edge(b, t));
            reverse_list[b].add(new Edge(a, t));
        }
        
        // 모든 정점에서 X로 가는 거리를 구함
        int[] distance = dijkstra(list);
        int[] reverse_distance = dijkstra(reverse_list);
        int max = -1;
        for (int i = 1; i <= N; i++) max = Math.max(max, distance[i] + reverse_distance[i]);

        bw.write(max + "\n");

        bw.flush();
        bw.close();
    }

    private static int[] dijkstra(ArrayList<Edge>[] list) {
        int[] distance = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) distance[i] = Integer.MAX_VALUE;
        distance[X] = 0;

        PriorityQueue<Edge> q = new PriorityQueue<>();
        q.offer(new Edge(X, 0));

        while(!q.isEmpty()) {
            Edge cur = q.poll();
            int c_v = cur.vertex;

            if (visited[c_v]) continue;
            visited[c_v] = true;
            
            for (Edge tmp : list[c_v]) {
                int next = tmp.vertex;
                int value = tmp.value;

                // 최단거리 구함
                if (distance[next] > distance[c_v] + value) {
                    distance[next] = distance[c_v] + value;
                    q.add(new Edge(next, distance[next]));
                }
            }
        }
        
        return distance;
    }
}
