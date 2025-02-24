import java.util.*;
import java.io.*;

class Num {
    int n1;
    int n2;
    int depth;
    boolean isMultiply;

    public Num(int n1, int n2, int depth, boolean isMultiply) {
        this.n1 = n1;
        this.n2 = n2;
        this.depth = depth;
        this.isMultiply = isMultiply;
    }
}

public class J1808 {

    static int answer;
    static boolean[] nums;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        int turn = 1;
        while (T-- > 0) {
            nums = new boolean[10];
            visited = new boolean[1000001][1000001][2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 10; i++) nums[i] = Integer.parseInt(st.nextToken()) == 1;

            answer = Integer.parseInt(br.readLine());

            bw.write("#" + turn + " " + bfs() + "\n");
            turn++;
        }

        bw.flush();
        bw.close();
    }

    private static int bfs() {
        Queue<Num> q = new LinkedList<>();

        for (int i = 1; i < 10; i++) {
            if (nums[i]) {
                q.offer(new Num(i, 0, 1, false));
                visited[i][0][0] = true;
            };
        }

        while(!q.isEmpty()) {
            Num num = q.poll();

            if (num.n1 == answer) return num.depth;
            if (num.n1 >= answer || num.n2 >= answer || num.n1 * num.n2 >= answer) continue;

            for (int i = 0; i < 12; i++) {
                Num next = new Num(num.n1, num.n2, num.depth + 1, num.isMultiply);
                if (i < 10) {
                    if (num.isMultiply) {
                        next.n2 = Integer.parseInt(num.n2 + "" + i);
                    } else {
                        next.n1 = Integer.parseInt(num.n1 + "" + i);
                    }
                } else if (i == 10 && !num.isMultiply) {
                    next.isMultiply = true;
                } else if (i == 11) {
                    if (next.isMultiply) {
                        next.n1 = next.n1 * next.n2;
                        next.n2 = 0;
                    }
                }

                if (!visited[next.n1][next.n2][next.isMultiply ? 1 : 0]) {
                    q.add(next);
                }
            }

        }

        return -1;
    }
}
