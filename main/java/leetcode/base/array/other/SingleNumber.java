package leetcode.base.array.other;

/**
 * @author: wuxin0011
 * @Description:
 */
public class SingleNumber {
    public static void main(String[] args) {

    }

    public int singleNumber(int[] nums) {
        int result = 0;
        int i = 0;
        int cur = nums[0];
        for (int num : nums) {
            result = result ^ num;
        }
        return result;
    }
}
