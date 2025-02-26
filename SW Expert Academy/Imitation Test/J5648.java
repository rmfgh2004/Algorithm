import java.util.*;
import java.io.*;

class Atom {
    int r;
    int c;
    int dir;
    int e;

    public Atom(int r, int c, int dir, int e) {
        this.r = r;
        this.c = c;
        this.dir = dir;
        this.e = e;
    }
}

public class J5648 {

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int sum;
    static int[][] map = new int[4001][4001];
    static Queue<Atom> q;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        int turn = 1;
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            q = new LinkedList<Atom>();
            sum = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                Atom atom = new Atom(r * 2 + 2000, c * 2 + 2000, dir, e);
                q.offer(atom);
            }
            bfs();
            
            bw.write("#" + turn + " " + sum + "\n");
            turn++;
        }

        bw.flush();
        bw.close();
    }

    private static void bfs() {
        
        while (!q.isEmpty()) {
            Atom atom = q.poll();
            
            // 지금 무언가가 합쳐진 상태
            if (map[atom.r][atom.c] > atom.e) {
                sum += map[atom.r][atom.c];
                map[atom.r][atom.c] = 0;
                continue;
            }

            map[atom.r][atom.c] = 0;
            int nr = atom.r + dr[atom.dir];
            int nc = atom.c + dc[atom.dir];
            if (nr < 0 || nr > 4000 || nc < 0 || nc > 4000) continue;

            if (map[nr][nc] == 0) q.add(new Atom(nr, nc, atom.dir, atom.e));
            
            map[nr][nc] += atom.e;
        }

    }
}
