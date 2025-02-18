import java.util.*;
import java.io.*;

public class Main {

  static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
  static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
  static int N;
  static char[][] board;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int T = Integer.parseInt(br.readLine());

    int turn = 1;
    while (T-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      board = new char[N][N];

      // board 기본셋팅
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          board[i][j] = '-';
        }
      }
      int mid = N == 4 ? 1 : N == 6 ? 2 : 3;
      board[mid][mid] = 'W';
      board[mid + 1][mid + 1] = 'W';
      board[mid + 1][mid] = 'B';
      board[mid][mid + 1] = 'B';

      for (int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        play(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) == 1 ? 'B' : 'W');
      }

      int[] cnts = new int[2];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (board[i][j] == 'B') cnts[0]++;
          else if (board[i][j] == 'W') cnts[1]++;
        }
      }

      bw.write("#" + turn + " " + cnts[0] + " " + cnts[1] + "\n");
      turn++;
    }

    bw.flush();
    bw.close();
  }

  // 돌 두고 8방향 탐색
  private static void play(int x, int y, char color) {
    board[y][x] = color;
    for (int i = 0; i < 8; i++) changeColor(x, y, color, i, true);
  }

  // -1 : 공백 / 0 : 다른 색 / 1 : 같은 색
  private static int changeColor(int x, int y, char color, int dir, boolean isStart) {

    if (x < 0 || x >= N || y < 0 || y >= N) return -1;
    
    int result = -1;      
    char c = board[y][x];

    if (c == '-') return -1; // 공백 필터
    else if (color != c || isStart) result = changeColor(x + dx[dir], y + dy[dir], color, dir, false); // 뒤집을 돌 발견
    else if (color == c && !isStart) return 1; // 양 쪽 끝이 같은지 판별

    // 같다면 지나온 모든 돌 뒤집기
    if (result == 1) {
      board[y][x] = color;
    };

    return result;
  }

  // 뒤집기 실시간 print
  private static void printBoard() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
  }
}
