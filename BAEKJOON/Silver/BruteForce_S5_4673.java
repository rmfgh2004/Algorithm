import java.util.*;
import java.io.*;

public class BruteForce_S5_4673 {

    public static void main(String[] args) throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[] selfNumbers = new boolean[10001];
        
        // 셀프 넘버 구하기
        for (int i = 1; i < 10001; i++) {
            char[] chars = String.valueOf(i).toCharArray();
            int[] nums = new int[chars.length];

            // 자리별 수를 배열에 저장
            for (int j = 0; j < nums.length; j++) {
                nums[j] = chars[j] - '0';
            }
            
            // 값 더함
            int sum = i;
            for (int j = 0; j < nums.length; j++) {
                sum += nums[j];
            }
            
            // 생성자로 나오는 수 저장
            if (sum < 10001) selfNumbers[sum] = true;
        }
        
        // 출력
        for (int i = 1; i < 10001; i++) {
            if (!selfNumbers[i]) bw.write(i + "\n");
        }
        

        bw.flush();
        bw.close();
    }
    
}
