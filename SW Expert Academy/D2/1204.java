import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      int N = Integer.parseInt(br.readLine());
      int[] score = new int[101];

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < 1000; i++) {
        score[Integer.parseInt(st.nextToken())]++;
      }

      int max_index = 100;
      for (int i = 99; i >= 0; i--) {
        if (score[max_index] < score[i])
          max_index = i;
      }

      bw.write(("#" + N + " " + max_index) + "\n");
      
    }

    bw.flush();
    bw.close();
  }
}
