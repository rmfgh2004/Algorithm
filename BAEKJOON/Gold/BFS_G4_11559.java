import java.util.*;
import java.io.*;

class Puyo {
    int r;
    int c;
    char color;

    public Puyo(int r, int c, char color) {
        this.r = r;
        this.c = c;
        this.color = color;
    }
}

public class BFS_G4_11559 {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N = 12, M = 6;
    static char[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = line[j];
            }
        }

        int cnt = 0;
        boolean isFinish = false;
        while(!isFinish) { // 더이상 터지는게 없으면 종료
            visited = new boolean[N][M];
            int bumbCnt = 0;
            for (int i = N - 1; i >= 0; i--) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] != '.') {
                        if (!visited[i][j]) {
                            bumbCnt += bfs(new Puyo(i, j, board[i][j])) ? 1 : 0;
                        }
                    }
                }
            }
            // 터짐 여부를 확인 후 터졌으면 카운트 증가 아니면 게임 끝남.
            isFinish = bumbCnt == 0;
            if (!isFinish) {
                cnt++;
                movePuyo();
            };
        }

        bw.write(cnt + "\n");

        bw.flush();
        bw.close();
    }

    private static boolean bfs(Puyo p) {
        int sequence = 0;
        ArrayList<Puyo> history = new ArrayList<>();
        history.add(p);
        Queue<Puyo> q = new LinkedList<>();
        q.offer(p);
        visited[p.r][p.c] = true;
        
        while(!q.isEmpty()) {
            Puyo now = q.poll();
            sequence++;
            
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || board[nr][nc] != now.color) continue;

                q.add(new Puyo(nr, nc, now.color));
                history.add(new Puyo(nr, nc, now.color));
                visited[nr][nc] = true;
            }
        }
        
        if (sequence >= 4) {
            for (Puyo pu : history) {
                board[pu.r][pu.c] = '.';
            }
        }

        return sequence >= 4;
    }

    // 뿌요 밑으로 밀기
    private static void movePuyo() {
        // printBoard();
        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != '.' && board[i + 1][j] == '.') {
                    int index = i + 1;
                    for (int k = i + 2; k < N; k++) {
                        if (board[k][j] == '.') index = k;
                    }
                    board[index][j] = board[i][j];
                    board[i][j] = '.';
                }
            }
        }
        // System.out.println("------------- 정렬 후 -------------");
        // printBoard();
        // System.out.println("==========================");

    }

    // 보드판 실시간 현황
    private static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
