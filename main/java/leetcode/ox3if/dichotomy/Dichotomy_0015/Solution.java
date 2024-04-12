package leetcode.ox3if.dichotomy.Dichotomy_0015;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 *
 * 1011. 在 D 天内送达包裹的能力
 *
 * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。传送带上的第 i个包裹的重量为weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 * 示例 1：输入：weights = [1,2,3,4,5,6,7,8,9,10], days = 5输出：15解释：船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：第 1 天：1, 2, 3, 4, 5第 2 天：6, 7第 3 天：8第 4 天：9第 5 天：10请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 *
 * 示例 2：输入：weights = [3,2,2,4,1,4], days = 3输出：6解释：船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：第 1 天：3, 2第 2 天：2, 4第 3 天：1, 4
 *
 * 示例 3：输入：weights = [1,2,3,1,1], days = 4输出：3解释：第 1 天：1第 2 天：2第 3 天：3第 4 天：1, 1
 *
 * 提示：
 *
 *
 * 	1 <= days <= weights.length <= 5 * 104
 * 	1 <= weights[i] <= 500
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days
 * @title: 犍天内送达包裹的能力
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"shipWithinDays","in.txt");
    }
     

    public int shipWithinDays(int[] weights, int days) {

        int r = 0,l = 0;
        for (int weight : weights) {
            r += weight;
            l = Math.max(l,weight);
        }
        if(days==1){
            return r;
        }
        while(l<=r){
            int mid = l + ((r - l)>>1);
            if(check(weights,mid,days)){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }


    public static boolean check(int[] weights,int w,int d){
        int day = 1;
        int tot = 0;
        for(int v : weights){
            tot += v;
            if(tot>w){
                day++;
                tot = v;
            }
            if(day>d){
                break;
            }
        }
        return day <= d;
    }

  

}