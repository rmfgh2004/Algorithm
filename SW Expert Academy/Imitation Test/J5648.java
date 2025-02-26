import java.util.*;
import java.io.*;

class Atom {
    int r;
    int c;
    int dir;
    int e;
    int time;

    public Atom(int c, int r, int dir, int e, int time) {
        this.r = r;
        this.c = c;
        this.dir = dir;
        this.e = e;
        this.time = time;
    }
}

public class J5648 {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int max = 2001, sum;
    static Atom[][] map;
    static Queue<Atom> q;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        int turn = 1;
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            map = new Atom[max][max];
            q = new LinkedList<Atom>();
            sum = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                Atom atom = new Atom(1000 + Integer.parseInt(st.nextToken()), 1000 + Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
                q.offer(atom);
                map[atom.r][atom.c] = atom;
            }
            bfs();
            
            bw.write("#" + turn + " " + sum + "\n");
            turn++;
        }

        bw.flush();
        bw.close();
    }

    private static void bfs() {
        
        int cnt = 0;
        while (!q.isEmpty()) {
            Atom atom = q.poll();
            atom.time++;
            int nr = atom.r + dr[atom.dir];
            int nc = atom.c + dc[atom.dir];
            if (nr < 0 || nr >= max || nc < 0 || nc >= max) continue;

            // 동시 충돌
            if (map[nr][nc] != null && (map[nr][nc].time == atom.time || map[nr][nc].time == atom.time - 1)) {
                System.out.println(map[nr][nc].time + " / " + atom.time);
                sum += map[nr][nc].e + atom.e;
                map[nr][nc] = null;
                map[atom.r][atom.c] = null;
                continue;
            }
            
            // 이동
            map[atom.r][atom.c] = null;
            map[nr][nc] = atom;
            q.add(new Atom(nc, nr, atom.dir, atom.e, atom.time));
            
            cnt++;

            
        }

    }
}
