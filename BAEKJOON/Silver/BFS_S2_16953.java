import java.util.*;
import java.io.*;

public class BFS_S2_16953 {

    static long A, B;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        bw.write(bfs() + "\n");

        bw.flush();
        bw.close();
    }

    private static long bfs() {
        Queue<long[]> q = new LinkedList<>();
        q.offer(new long[] {A, 1});

        while(!q.isEmpty()) {
            long[] now = q.poll();
            long a = now[0];
            if (a == B) return now[1];

            // x2 연산 저장
            long b1 = now[0] * 2;
            if (b1 <= B) q.add(new long[] {b1, now[1] + 1});

            // 뒤에 1붙이는 연산 저장
            long b2 = now[0] * 10 + 1;
            if (b2 <= B) q.add(new long[] {b2, now[1] + 1});
        }

        return -1;
    }
}
