import java.util.*;
import java.io.*;

public class J2805 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        int turn = 1;
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N + 1];
            int mid = (N + 1) / 2;

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 1; j <= N; j++) {
                    map[i][j] = Integer.parseInt(line.substring(j - 1, j));
                }
            }

            boolean isMid = false;
            int r = 0; // 행
            int i = 0; // mid 기준 -,+ 값
            int sum = 0;
            while (r < N) {
                for (int c = mid - i; c <= mid + i; c++) {
                    sum += map[r][c];
                }

                if (r + 1 == mid) isMid = true;
                
                if (isMid) i--;
                else i++;

                r++;
            }

            bw.write("#" + turn + " " + sum + "\n");
            turn++;
        }

        bw.flush();
        bw.close();
    }
}
