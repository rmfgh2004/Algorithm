import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] A;
    static boolean[] visited;
    static ArrayList<Integer> answer;
    
	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        visited = new boolean[N + 1];
        answer = new ArrayList<>();
        
        for (int i = 1; i <=N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        
        for (int i = 1; i <=N; i++) {
            visited[i] = true;
            DFS(i, i);
            visited[i] = false;
        }
        
        Collections.sort(answer);
        System.out.println(answer.size());
        for (int i : answer) {
            System.out.println(i);
        }
        
    }
    
    private static void DFS(int start, int target) {
        if (!visited[A[start]]) {
            visited[A[start]] = true;
            DFS(A[start], target);
            visited[A[start]] = false;
        }
        
        if (A[start] == target) {
            answer.add(target);
        }
    }
    
    
}
