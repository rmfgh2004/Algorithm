import java.util.*;
import java.io.*;

public class J2105 {

    static int[] dr = {-1, -1, 1, 1};
    static int[] dc = {1, -1, -1, 1};
    static int N;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        int turn = 1;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[101];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            turn++;
        }

        bw.flush();
        bw.close();
    }
}
