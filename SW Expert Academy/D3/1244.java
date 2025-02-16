import java.util.*;
import java.io.*;

public class Main {

  static int[] nums;
  static int chance, max;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    int turn = 1;
    while (T-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      // 숫자 배열
      char[] nums_char = st.nextToken().toCharArray();
      nums = new int[nums_char.length];
      for (int i = 0; i < nums.length; i++)
        nums[i] = nums_char[i] - '0';

      chance = Integer.parseInt(st.nextToken());
      if (chance >= nums.length)
        chance = nums.length;

      max = 0;
      DFS(0, 0);

      bw.write("#" + turn + " " + max + "\n");
      turn++;
    }

    bw.flush();
    bw.close();
  }

  private static void DFS(int n, int depth) {
    if (depth == chance) {
      int total = 0;
      for (int i = 0; i < nums.length; i++) {
        total += Math.pow(10, i) * nums[nums.length - (i + 1)];
      }
      max = Math.max(max, total);
      return;
    }

    for (int i = n; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        swap(i, j);
        DFS(i, depth + 1);
        swap(i, j);
      }
    }
  }

  private static void swap(int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}
