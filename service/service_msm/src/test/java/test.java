import java.util.HashSet;
import java.util.Set;

/**
 * @author 叶刚诚
 * @create 2022-01-11-11:24
 */
public class test {
    Set<Long> visited = new HashSet();
    boolean isPossible;
    long border = 1000000;
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        for(int i = 0 ; i < blocked.length ; i++){
            visited.add(blocked[i][0]*border +blocked[i][1]);
        }
        dfs(source[0] , source[1] , target[0],target[1],100);
        return isPossible;
    }
    boolean dfs(int x ,int y ,int tx ,int ty , int time){
        long tmp =x*border+y;
        if(isPossible || x < 0 || y < 0 || x >=border || y >= border || visited.contains(tmp) ) return false;
        if( time < 0) return true;
        if(x == tx && ty == y){
            isPossible = true;
            return true;
        }
        visited.add(tmp) ;
        boolean b1 = dfs(x - 1, y, tx, ty, time - 1);
        if(isPossible) return true;
        boolean b2 = dfs(x,y-1,tx,ty,time -1);
        if(isPossible) return true;
        boolean b3 = dfs(x+1,y,tx,ty,time -1);
        if(isPossible) return true;
        boolean b4 = dfs(x,y+1,tx,ty,time -1);
        if(isPossible) return true;
        visited.remove(tmp);
        return b1 || b2 || b3 || b4;
    }

    public static void main(String[] args) {
        test t =new test();
        boolean b = t.isEscapePossible(new int[][]{{0, 1}, {1, 0}}, new int[]{0, 0}, new int[]{0, 2});
        System.out.println(b);
    }
}
