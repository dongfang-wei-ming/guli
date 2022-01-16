import org.junit.jupiter.api.Test;

class Solution {
    public long mostPoints(int[][] questions) {
        int n =questions.length;
        long[] dp = new long[n];
        dp[0] = questions[0][0];
        long ans = dp[0];
        int begin = 0;
        for(int i = 1 ; i < n ;i++){
            long max = 0;
            for(int j = begin;  j < i; j++){
                if( j + questions[j][1] < i){
                    if(dp[j] >= max){
                        max = dp[j];
                        begin = j;
                    }
                }
            }
            dp[i] = questions[i][0] + max;
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }
    @Test
    public void t(){
        mostPoints(new int[][]{{3,2},{4,3},{4,4},{2,5}});
    }
}