package template.bit;

import code_generation.utils.RandomArrayUtils;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Test {

    public static int findMinimumXOR(int[] nums) {

        int ans = Integer.MAX_VALUE;
        int[] b = Arrays.copyOf(nums,nums.length);
        Arrays.sort(b);
        for(int i = 0;i<nums.length-1;i++){
            ans=Math.min(ans,b[i]^b[i + 1]);
        }
        return ans;
    }

    public static int forcesfindMinimumXOR(int[] nums) {
       int ans = Integer.MAX_VALUE;
       for(int i = 0;i<nums.length;i++){
           for(int j = i + 1;j < nums.length;j++){
               ans = Math.min(ans,nums[i]^nums[j]);
           }
       }
       return ans;
    }


    public static void main(String[] args) {
        int t = 100000;
        boolean ok = true;
        for(;t > 0;t--){
            int[] a = RandomArrayUtils.randomIntArray(10,20,0,1000);
//            int[] a = {5,7};
            int r = findMinimumXOR(a);
            int e = forcesfindMinimumXOR(a);
            if(r != e){
                System.out.println("result = " + r);
                System.out.println("except = " + e);
                System.out.println(Arrays.toString(a));
                ok = false;
                break;
            }
        }
        System.out.println(ok ? "sucees" :"error");
    }
}
