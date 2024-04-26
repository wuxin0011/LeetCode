package leetcode.everyday.day_000;

import code_generation.contest.ParseCodeInfo;
import java.util.*;
import code_generation.utils.IoUtil;

/**
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