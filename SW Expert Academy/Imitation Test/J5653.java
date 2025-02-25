import java.util.*;
import java.io.*;

class Cell {
    int life;
    int wake;
    int die;
    boolean isWake = false;
    boolean isDie = false;
    boolean isBreed = false;

    public Cell(int life, int wake, int die) {
        this.life = life;
        this.wake = wake;
        this.die = die;
    }
}

public class J5653 {

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M, min_row, min_col, max_row, max_col;
    static int L = 10000;
    static Cell[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        int turn = 1;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            map = new Cell[L][L];
            int mid = 5000 - N;
            min_row = mid;
            min_col = mid;
            max_row = mid + N;
            max_col = mid + M;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    if (life != 0) map[i][j] = new Cell(life, 0, 0);
                }
            }

            for (int k = K; k > 0; k--) {
                play();
            }

            int cnt = 0;
            for (int r = min_row; r < max_row; r++) {
                for (int c = min_col; c < max_col; c++) {
                    Cell cell = map[r][c];
                    if (!cell.isDie && cell.life != 0) cnt++;
                }
            }

            bw.write("#" + turn + " " + cnt + "\n");

            turn++;
        }

        bw.flush();
        bw.close();
    }

    private static void play() {
        
        for (int r = min_row; r < max_row; r++) {
            for (int c = min_col; c < max_col; c++) {

                Cell cell = map[r][c];
                // 빈 칸 필터
                if (cell == null) continue;

                if (cell.isBreed) { // 죽은 세포 필터
                    continue;
                } else if (cell.isWake) { // 활성 세포 -> 죽이기
                    cell.die++;
                    cell.isDie = cell.life == cell.die;
                } else if (!cell.isWake) { // 비활성 세포 -> 깨우기
                    cell.wake++;
                    cell.isWake = cell.life == cell.wake;
                }

                if (cell.isWake && cell.die == 1) { // 깨어난진 1시간 뒤
                    cell.isBreed = true;
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        Cell next = map[nr][nc];

                        if (nr < min_row) min_row = nr;
                        if (nr > max_row) max_row = nr;
                        if (nc < min_col) min_col = nc;
                        if (nc > max_col) max_col = nc;
                    }
                }
            }
        }

    }
}
