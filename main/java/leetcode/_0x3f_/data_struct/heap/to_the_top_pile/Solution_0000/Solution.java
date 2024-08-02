package leetcode._0x3f_.data_struct.heap.to_the_top_pile.Solution_0000;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

/**
 *
 * 210^2. 序列顺序查询一个观光景点由它的名字name 和景点评分score组成，其中name是所有观光景点中唯一的字符串，score是一个整数。
 * 景点按照最好到最坏排序。景点评分 越高，这个景点越好。如果有两个景点的评分一样，那么 字典序较小的景点更好。
 * 你需要搭建一个系统，查询景点的排名。初始时系统里没有任何景点。这个系统支持：
 * 	添加 景点，每次添加 一个 景点。
 * 	查询 已经添加景点中第i好的景点，其中i是系统目前位置查询的次数（包括当前这一次）。
 *
 * 		比方说，如果系统正在进行第 4次查询，那么需要返回所有已经添加景点中第 4好的。
 *
 *
 * 注意，测试数据保证任意查询时刻，查询次数都 不超过系统中景点的数目。
 * 请你实现SORTracker类：
 * 	SORTracker()初始化系统。
 * 	void add(string name, int score)向系统中添加一个名为name 评分为score的景点。
 * 	string get()查询第 i好的景点，其中 i是目前系统查询的次数（包括当前这次查询）。
 *
 * 示例：
 * 输入：
 * ["SORTracker", "add", "add", "get", "add", "get", "add", "get", "add", "get", "add", "get", "get"]
 * [[], ["bradford", 2], ["branford", 3], [], ["alps", 2], [], ["orland", 2], [], ["orlando", 3], [], ["alpine", 2], [], []]
 * 输出：
 * [null, null, null, "branford", null, "alps", null, "bradford", null, "bradford", null, "bradford", "orland"]
 * 解释：
 * SORTracker tracker = new SORTracker(); // 初始化系统
 * tracker.add("bradford", 2); // 添加 name="bradford" 且 score=2 的景点。
 * tracker.add("branford", 3); // 添加 name="branford" 且 score=3 的景点。
 * tracker.get();              // 从好带坏的景点为：branford ，bradford 。
 *                             // 注意到 branford 比 bradford 好，因为它的 评分更高 (3 > 2) 。
 *                             // 这是第 1 次调用 get() ，所以返回最好的景点："branford" 。
 * tracker.add("alps", 2);     // 添加 name="alps" 且 score=2 的景点。
 * tracker.get();              // 从好到坏的景点为：branford, alps, bradford 。
 *                             // 注意 alps 比 bradford 好，虽然它们评分相同，都为 2 。
 *                             // 这是因为 "alps" 字典序比 "bradford" 小。
 *                             // 返回第 2 好的地点 "alps" ，因为当前为第 2 次调用 get() 。
 * tracker.add("orland", 2);   // 添加 name="orland" 且 score=2 的景点。
 * tracker.get();              // 从好到坏的景点为：branford, alps, bradford, orland 。
 *                             // 返回 "bradford" ，因为当前为第 3 次调用 get() 。
 * tracker.add("orlando", 3);  // 添加 name="orlando" 且 score=3 的景点。
 * tracker.get();              // 从好到坏的景点为：branford, orlando, alps, bradford, orland 。
 *                             // 返回 "bradford".
 * tracker.add("alpine", 2);   // 添加 name="alpine" 且 score=2 的景点。
 * tracker.get();              // 从好到坏的景点为：branford, orlando, alpine, alps, bradford, orland 。
 *                             // 返回 "bradford" 。
 * tracker.get();              // 从好到坏的景点为：branford, orlando, alpine, alps, bradford, orland 。
 *                             // 返回 "orland" 。
 *
 * 提示：
 * 	name只包含小写英文字母，且每个景点名字互不相同。
 * 	1 <= name.length <= 10
 * 	1 <= score <= 10^5
 * 	任意时刻，调用get的次数都不超过调用add的次数。
 * 	总共调用add 和get不超过4 * 10^4
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/sequentially-ordinal-rank-tracker
 * @title: 序列顺序查询
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(SORTracker.class, ParseCodeInfo.ConstructorClass, "in.txt");
    }


    public static class SORTracker {
        public SORTracker() {


        }

        public void add(String name, int score) {


        }

        public String get() {

            return null;
        }


    }

}