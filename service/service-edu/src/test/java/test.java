import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author 叶刚诚
 * @create 2022-01-09-10:20
 */
public class test {

    public int minSwaps(int[] nums) {
        int count = 0;
        int n = nums.length;
        for(int i = 0; i < n ; i++) if(nums[i] == 1) count++;
        int ans = count;
        int[] win = new int[n*2];
        System.arraycopy(nums,0,win,0,n);
        System.arraycopy(nums,0,win,n,n);
        for(int i = 1 ; i < 2*n ;i++) win[i] +=win[i-1];
        for(int i = 0 ; i < 2*n-count +1 ; i++){
            int t = win[i + count - 1];
            if(i > 0) t -=win[i-1];
            ans = Math.min(ans,count - t);
        }
        return ans;
    }

}
