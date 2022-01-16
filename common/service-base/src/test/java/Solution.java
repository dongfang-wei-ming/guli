import java.util.HashMap;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        Solution s=new Solution();
        s.maximumInvitations(new int[]{1,0,0,2,1,4,7,8,9,6,7,10,8});
    }
    public int maximumInvitations(int[] favorite) {
        Map<Integer,Integer> map = new HashMap();
        int ans = 2;
        for(int i = 0; i < favorite.length; i++){
            int tmp = i;
            int count = 1;
            if( map.containsKey(favorite[i])) continue;
            int begin = i;
            int end = i;
            while( !map.containsKey(favorite[i]) ){
                end = i;
                map.put(i,favorite[i]);
                i = favorite[i];
                count++;
            }
            if(favorite[i] != begin && favorite[i] !=end){
                map.clear();
            }else if(favorite[i] == begin){
                ans = Math.max(ans,count);
            }else{
                ans = Math.max(ans,count);
                map.clear();
            }
            i = tmp;
        }
        return ans;
    }

}