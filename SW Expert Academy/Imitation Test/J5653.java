import java.util.*;
import java.io.*;

class Cell {
    int r;
    int c;
    int life;
    int wake;
    int die;
    boolean isWake = false;
    boolean isDie = false;
    boolean isBreed = false;

    public Cell(int r, int c, int life, int wake, int die) {
        this.r = r;
        this.c = c;
        this.life = life;
        this.wake = wake;
        this.die = die;
    }
}

public class J5653 {

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M, min_row, min_col, max_row, max_col;
    static int L = 2000; // 10000으로 제출하니깐 오류떠서 2000으로 바꾸니 됨..
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
            int mid = 700 - N;
            min_row = mid;
            min_col = mid;
            max_row = mid + N;
            max_col = mid + M;

            for (int r = min_row; r < max_row; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = min_col; c < max_col; c++) {
                    int life = Integer.parseInt(st.nextToken());
                    if (life != 0) map[r][c] = new Cell(r, c, life, 0, 0);
                }
            }

            for (int k = K; k > 0; k--) {
                bfs();
            }

            int cnt = 0;
            for (int r = min_row; r < max_row; r++) {
                for (int c = min_col; c < max_col; c++) {
                    Cell cell = map[r][c];
                    if (cell != null && !cell.isDie) cnt++;
                }
            }

            // bw.write("#" + turn + " " + cnt + " " + min_row + "/" + max_row + " " + min_col + "/" + max_col + "\n");
            bw.write("#" + turn + " " + cnt + "\n");

            turn++;
        }

        bw.flush();
        bw.close();

    }

    private static void bfs() {

        Queue<Cell> q = new LinkedList<>();
        
        // 초반 세포 생명력 변경과 번식 해야하는 세포 계산
        for (int r = min_row; r < max_row; r++) {
            for (int c = min_col; c < max_col; c++) {

                Cell cell = map[r][c];
                // 빈 칸 필터
                if (cell == null) continue;

                if (cell.isBreed && cell.isDie) { // 번식 완료한 세포 필터
                    continue;
                } else if (cell.isWake) { // 활성 세포 -> 죽이기
                    cell.die++;
                    cell.isDie = cell.life == cell.die;
                } else if (!cell.isWake) { // 비활성 세포 -> 깨우기
                    cell.wake++;
                    cell.isWake = cell.life == cell.wake;
                }

                if (cell.isWake && cell.die == 1) { // 깨어난진 1시간 뒤 번식을 위한 세포는 q에 저장
                    cell.isBreed = true;
                    map[r][c] = cell;
                    q.offer(cell);
                }
            }
        }

        while (!q.isEmpty()) {
            Cell cell = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cell.r + dr[i];
                int nc = cell.c + dc[i];
                Cell next = map[nr][nc];
    
                if (next == null) map[nr][nc] = new Cell(nr, nc, cell.life, 0, 0);
                // 동시에 번식한 세포 중 더 life가 큰 세포
                if (next != null && next.wake == 0 && next.life < cell.life) map[nr][nc] = new Cell(nr, nc, cell.life, 0, 0);
    
                if (nr < min_row) min_row = nr;
                if (nr + 1> max_row) max_row = nr + 1;
                if (nc < min_col) min_col = nc;
                if (nc + 1> max_col) max_col = nc + 1;
            }
        }

    }

    // 세포 출력
    private static void printMap() {
        System.out.println();
        for (int r = min_row; r < max_row; r++) {
            for (int c = min_col; c < max_col; c++) {
                Cell cell = map[r][c];
                if(cell == null) System.out.print("0 ");
                else System.out.print(cell.life + " ");
            }
            System.out.println();
        }
    }
}
