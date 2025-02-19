import java.util.*;
import java.io.*;

public class J1974 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    int num = 1;
    while (T-- > 0) {
      boolean isError = false;
      int[][] map = new int[9][9];
      for (int i = 0; i < 9; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < 9; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      // 가로 세로 중복 확인
      for (int i = 0; i < 9; i++) {
        // 가로줄 중복 확인
        int[] nums_row = new int[10];
        // 세로줄 중복 확인
        int[] nums_col = new int[10];

        for (int j = 0; j < 9; j++) {

          nums_row[map[i][j]]++;
          nums_col[map[j][i]]++;

          if (nums_row[map[i][j]] > 1 || nums_col[map[j][i]] > 1) {
            isError = true;
            break;
          }
        }

        if (isError)
          break;
      }

      // 3x3확인
      for (int i = 0; i < 3; i++) {
        int[][] nums = new int[3][10];

        for (int x = 0; x < 3; x++) {
          for (int y = 0; y < 3; y++) {
            // 위에 1, 2, 3
            nums[0][map[x][i * 3 + y]]++;
            // 가운데 1, 2, 3
            nums[1][map[3 + x][i * 3 + y]]++;
            // 아래 1, 2, 3
            // 블럭 확인
            nums[2][map[6 + x][i * 3 + y]]++;
            if (nums[0][map[x][i * 3 + y]] > 1 || nums[1][map[3 + x][i * 3 + y]] > 1
                || nums[2][map[6 + x][i * 3 + y]] > 1) {
              isError = true;
              break;
            }
          }
          if (isError)
            break;
        }
        if (isError)
          break;
      }

      bw.write("#" + num + " " + (isError ? 0 : 1) + "\n");
      num++;
    }

    bw.flush();
    bw.close();
  }
}
