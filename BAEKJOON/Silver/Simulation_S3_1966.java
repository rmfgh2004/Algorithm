import java.util.*;
import java.io.*;

class Docu {
    int sequence;
    int priority;

    Docu(int sequence, int priority) {
        this.sequence = sequence;
        this.priority = priority;
    }
}

public class Simulation_S3_1966 {

    static int N, M;
    static Docu[] docus;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            docus = new Docu[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int priority = Integer.parseInt(st.nextToken());
                docus[i] = new Docu(i, priority);
            }

            // N이 1이면 무조건 1
            if (N == 1) {
                bw.write("1\n");
                continue;
            }

            int cnt = 1; // 몇번 출력했는지 카운트
            for (int i = 0; i < N; i++) {
                Docu now = docus[i];
                boolean isBreak = false; // 우선순위가 더 높은게 있었는지 구분

                for (int j = i + 1; j < N; j++) {
                    Docu next = docus[j];
                    if (now.priority < next.priority) { // 우선순위가 더 높은게 있으면 정렬 후 다시 i--
                        sort(i);
                        isBreak = true;
                        i--;
                        break;
                    }
                }

                if (!isBreak) {
                    if (now.sequence == M) { // 찾으려는 문서면 출력
                        bw.write(cnt + "\n");
                        break;
                    }

                    cnt++;
                }
            }
        }

        bw.flush();
        bw.close();
    }

    private static void sort(int idx) { // 앞으로 한 칸씩 땡기기
        Docu docu = docus[idx];
        for (int i = idx + 1; i < N; i++) {
            docus[i - 1] = docus[i];
        }
        docus[N - 1] = docu;
    }
}
