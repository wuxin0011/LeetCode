package leetcode.top_interview_150.monotonic_stacks;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/next-greater-element-i/
 * @title 下一个更大的元素
 */
public class next_greater_element_i {

    public static void main(String[] args) {
        
    }

    public static int[] map = new int[10001];
    public static int[] sk = new int[10001];
    int size = 0;


    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        size = 0;
        int n = nums1.length,m = nums2.length;
        for (int i = m - 1; i>=0; i--,size++) {
            while(size>0&&nums2[i]>=nums2[sk[size-1]]){
                size--;
            }
            map[nums2[i]] = size == 0 ? -1 : nums2[sk[size-1]];
            sk[size] = i;
        }
        int[] ans = new int[n];
        for(int i = 0;i<n;i++){
            ans[i] = map[nums1[i]];
        }
        return ans;
    }
}
