import java.util.*;
import java.io.*;

class Cell {
    int r;
    int c;
    int n;
    int d;
    int time;

    Cell(int r, int c, int n, int d, int time) {
        this.r = r;
        this.c = c;
        this.n = n;
        this.d = d;
        this.time = time;
    }
}

public class J2382 {

    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, -1, 1};
    static int N, M, K;
    static ArrayList<Cell>[][] cells;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        int turn = 1;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cells = new ArrayList[N][N];
            for (int i = 0; i < N; i++) {
                cells[i] = new ArrayList[N];
                for (int j = 0; j < N; j++) {
                    cells[i][j] = new ArrayList<>();
                }
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                cells[r][c].add(new Cell(r, c, n, d, 0));
            }

            // printMap();
            for (int i = 0; i < M; i++) {
                move(i);
                validation(i);
                // printMap();
            }

            int sum = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!cells[r][c].isEmpty()) {
                        sum += cells[r][c].get(0).n;
                    }                
                }

            }
            bw.write("#" + turn + " " + sum + "\n");
            turn++;
        }

        bw.flush();
        bw.close();
    }

    // 단순 미생물 움직임
    private static void move(int time) {

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!cells[r][c].isEmpty()) { // 해당 위치에 미생물 존재 시 빼내면서 움직임
                    
                    Cell cell = cells[r][c].remove(0);
                    // 이미 움직인 애들은 그만
                    if (cell.time != time) {
                        cells[r][c].add(cell);
                        continue;
                    }

                    int nr = cell.r + dr[cell.d];
                    int nc = cell.c + dc[cell.d];
                    cells[nr][nc].add(new Cell(nr, nc, cell.n, cell.d, time + 1));
                        
                }
            }
        }

    }

    // 이동 후 약품, 군집 검사
    private static void validation(int time) {
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if ((r == 0 || r == N - 1) && !cells[r][c].isEmpty()) { // 상, 하 약품
                    Cell cell = cells[r][c].remove(0);
                    cell.d = changeDirection(cell.d);
                    cell.n /= 2;
                    if (cell.n != 0) cells[r][c].add(cell);
                } else if ((c == 0 || c == N - 1) && !cells[r][c].isEmpty()) { // 좌, 우 약품
                    Cell cell = cells[r][c].remove(0);
                    cell.d = changeDirection(cell.d);
                    cell.n /= 2;
                    if (cell.n != 0) cells[r][c].add(cell);
                } else if (cells[r][c].size() >= 2) { // 군집
                    int sum = 0;
                    int max = 0;
                    int d = 0;
                    int size = cells[r][c].size();
                    for (int k = 0; k < size; k++) {
                        Cell cell = cells[r][c].remove(0);
                        sum += cell.n;
                        if (max < cell.n) { // 가장 큰 미생물로 방향 바뀜
                            max = cell.n;
                            d = cell.d;
                        }
                    }
                    cells[r][c].add(new Cell(r, c, sum, d, time + 1));
                }
            }
        }

    }

    private static int changeDirection(int d) {
        switch (d) {
            case 1: return 2;
            case 2: return 1;
            case 3: return 4;
            case 4: return 3;
            default : return 0;
        }
    }

    private static void printMap() {
        System.out.println("=====================================");
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!cells[r][c].isEmpty()) {
                    System.out.print(cells[r][c].get(0).n + " ");
                } else {
                    System.out.print("0 ");
                }    
            }
            System.out.println();
        }
        System.out.println("=====================================");
    }
}
