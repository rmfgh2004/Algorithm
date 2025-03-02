import java.util.*;
import java.io.*;

class Core {
    int r;
    int c;
    ArrayList<ArrayList<int[]>> root;

    Core(int r, int c) {
        this.r = r;
        this.c = c;
        root = new ArrayList<>();
    }
}

public class J1767 {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N;
    static int[][] board;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        int turn = 1;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            int coreCnt = 0;
            Core[] cores = new Core[12];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1) {
                        cores[coreCnt] = new Core(i, j);
                        coreCnt++;
                    }
                }
            }


            int sum = 0;
            for (int i = 0; i < coreCnt; i++) {
                bfs(cores[i]);

                if (cores[i].root.size() == 1) {
                    for (int[] pos : cores[i].root.get(0)) {
                        int r = pos[0];
                        int c = pos[1];
                        
                        if (board[r][c] != 0) continue;
                    }
                }
            }

            turn++;
        }

        bw.flush();
        bw.close();
    }

    private static void bfs(Core core) {
        
    }
}
