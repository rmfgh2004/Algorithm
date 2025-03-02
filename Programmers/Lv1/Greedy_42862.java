class Greedy_42862 {
    
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] clothes = new int[31]; // -1 없음 0 본인만 1 여분있음
        
        // 옷 여분이 있는지 판단을 위해 int[]에 저장
        for (int i : lost) {
            clothes[i]--;
        }
        
        for (int i : reserve) {
            clothes[i]++;
        }
        
        for (int i = 1; i <= n; i++) {
            
            // 앞 번호를 먼저 빌려줌
            if (i - 1 >= 1 && clothes[i] == 1 && clothes[i - 1] == -1) {
                clothes[i]--;
                clothes[i - 1]++;
                continue;
            }
            
            // 그 후 뒤 번호를 빌려준다.
            if (i + 1 <= n && clothes[i] == 1 && clothes[i + 1] == -1) {
                clothes[i]--;
                clothes[i + 1]++;
            }
        }
        
        for (int i = 1; i <= n; i++) {
            if (clothes[i] >= 0) answer++;
        }
        
        
        return answer;
    }
}
