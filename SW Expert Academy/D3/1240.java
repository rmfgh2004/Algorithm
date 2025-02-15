import java.util.*;
import java.io.*;

public class Main {

  static String[] secret_num = {
      "0001101",
      "0011001",
      "0010011",
      "0111101",
      "0100011",
      "0110001",
      "0101111",
      "0111011",
      "0110111",
      "0001011"
  };

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    int turn = 1;
    while (T-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());

      int[] secret = new int[8];
      for (int i = 0; i < N; i++) {
        String line = br.readLine();
        for (int j = M - 1; j >= 0; j--) {

          // 암호는 모두 1로 끝남
          if (line.charAt(j) == '1') {

            int idx = 0;
            for (int k = j - 55; k <= j; k += 7) {
              secret[idx++] = secretToNumber(line.substring(k, k + 7));
            }
            break;
          }
        }
      }

      bw.write("#" + turn + " ");
      if (((secret[0] + secret[2] + secret[4] + secret[6]) * 3 + secret[1] + secret[3] + secret[5] + secret[7])
          % 10 == 0) {
        int sum = 0;
        for (int i = 0; i < 8; i++)
          sum += secret[i];
        bw.write(sum + "\n");
      } else {
        bw.write("0\n");
      }

      turn++;
    }

    bw.flush();
    bw.close();
  }

  // 암호를 숫자로 변경
  private static int secretToNumber(String secret) {
    for (int i = 0; i < secret_num.length; i++) {
      if (secret.equals(secret_num[i]))
        return i;
    }

    return -1;
  }
}
