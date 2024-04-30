package leetcode.everyday.day_000;

import code_generation.contest.ParseCodeInfo;
import java.util.*;
import code_generation.utils.IoUtil;

/**
 *
 * 1146. 快照数组
 *
 * 实现支持下列接口的「快照数组」-SnapshotArray：
 * 	SnapshotArray(int length)- 初始化一个与指定长度相等的 类数组 的数据结构。
 * 初始时，每个元素都等于0。
 * 	void set(index, val)- 会将指定索引index处的元素设置为val。
 * 	int snap()- 获取该数组的快照，并返回快照的编号snap_id（快照号是调用snap()的总次数减去1）。
 * 	int get(index, snap_id)- 根据指定的snap_id选择快照，并返回该快照指定索引 index的值。
 *
 * 示例：
 * 输入：["SnapshotArray","set","snap","set","get"]
 *      [[3],[0,5],[],[0,6],[0,0]]
 * 输出：[null,null,0,null,5]
 * 解释：
 * SnapshotArray snapshotArr = new SnapshotArray(3); // 初始化一个长度为 3 的快照数组
 * snapshotArr.set(0,5);  // 令 array[0] = 5
 * snapshotArr.snap();  // 获取快照，返回 snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // 获取 snap_id = 0 的快照中 array[0] 的值，返回 5
 *
 * 提示：
 * 	1 <= length<= 50000
 * 	题目最多进行50000 次set，snap，和get的调用 。
 * 	0 <= index<length
 * 	0 <=snap_id <我们调用snap()的总次数
 * 	0 <=val <= 10^^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/snapshot-array
 * @title: 快照数组
 */
public class Code_0055_1146 {

    public static void main(String[] args) {
        IoUtil.testUtil(SnapshotArray.class, ParseCodeInfo.ConstructorClass, "txt_file\\Code_0055_1146.txt");
    }


    public  static class SnapshotArray {
        // 当前快照编号，初始值为 0
        private int curSnapId;

        // 每个 index 的历史修改记录
        private final Map<Integer, List<int[]>> history = new HashMap<>();

        public SnapshotArray(int length) {
        }

        public void set(int index, int val) {
            history.computeIfAbsent(index, k -> new ArrayList<>()).add(new int[]{curSnapId, val});
        }

        public int snap() {
            return curSnapId++;
        }

        public int get(int index, int snapId) {
            if (!history.containsKey(index)) {
                return 0;
            }
            List<int[]> h = history.get(index);
            int j = lowerBound(h, snapId);
            return j < 0 ? 0 : h.get(j)[1];
        }

        private int lowerBound(List<int[]> h, int x) {
            int r = h.size() - 1,l = 0;
            while(l<=r){
                int mid = l + ((r - l) >> 1);
                if(h.get(mid)[0]<=x){
                    l = mid + 1;
                }else {
                    r = mid - 1;
                }
            }
            return r;
        }
    }

}