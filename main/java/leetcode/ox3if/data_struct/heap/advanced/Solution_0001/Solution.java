package leetcode.ox3if.data_struct.heap.advanced.Solution_0001;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

import java.util.*;

/**
 *
 *
 * 355. 设计推特
 *
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近 10 条推文。
 * 实现 Twitter 类：
 * 	Twitter() 初始化简易版推特对象
 * 	void postTweet(int userId, int tweetId) 根据给定的 tweetId 和 userId 创建一条新推文。每次调用此函数都会使用一个不同的 tweetId 。
 * 	List<Integer> getNewsFeed(int userId) 检索当前用户新闻推送中最近 10 条推文的 ID 。新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文。推文必须 按照时间顺序由最近到最远排序 。
 * 	void follow(int followerId, int followeeId) ID 为 followerId 的用户开始关注 ID 为 followeeId 的用户。
 * 	void unfollow(int followerId, int followeeId) ID 为 followerId 的用户不再关注 ID 为 followeeId 的用户。
 *
 * 示例：
 * 输入
 * ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
 * [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
 * 输出
 * [null, null, [5], null, null, [6, 5], null, [5]]
 * 解释
 * Twitter twitter = new Twitter();
 * twitter.postTweet(1, 5); // 用户 1 发送了一条新推文 (用户 id = 1, 推文 id = 5)
 * twitter.getNewsFeed(1);  // 用户 1 的获取推文应当返回一个列表，其中包含一个 id 为 5 的推文
 * twitter.follow(1, 2);    // 用户 1 关注了用户 2
 * twitter.postTweet(2, 6); // 用户 2 发送了一个新推文 (推文 id = 6)
 * twitter.getNewsFeed(1);  // 用户 1 的获取推文应当返回一个列表，其中包含两个推文，id 分别为 -> [6, 5] 。推文 id 6 应当在推文 id 5 之前，因为它是在 5 之后发送的
 * twitter.unfollow(1, 2);  // 用户 1 取消关注了用户 2
 * twitter.getNewsFeed(1);  // 用户 1 获取推文应当返回一个列表，其中包含一个 id 为 5 的推文。因为用户 1 已经不再关注用户 2
 *
 * 提示：
 * 	1 <= userId, followerId, followeeId <= 500
 * 	0 <= tweetId <= 10^4
 * 	所有推特的 ID 都互不相同
 * 	postTweet、getNewsFeed、follow 和 unfollow 方法最多调用 3 * 10^4 次
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/design-twitter
 * @title: 设计推特
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Twitter.class, ParseCodeInfo.ConstructorClass, "in.txt");
    }

    public static class News implements Comparable<News> {
        int postId;
        int timeId;

        public News(int postId,int timeId) {
            this.postId = postId;
            this.timeId = timeId;
        }

        @Override
        public int compareTo(News o) {
            return o.timeId - this.timeId;
        }
    }

    public static class Twitter {

        int timeId = 0;

        Map<Integer, TreeSet<Integer>> flowers = new HashMap<>();
        Map<Integer, TreeSet<News>> postTweet = new HashMap<>();

        public Twitter() {


        }

        public void postTweet(int userId, int tweetId) {
            this.timeId++;
            TreeSet<News> ids = postTweet.getOrDefault(userId, new TreeSet<News>());
            ids.add(new News(tweetId,this.timeId));
            postTweet.put(userId, ids);
        }


        public List<Integer> getNewsFeed(int userId) {
            List<Integer> news = new ArrayList<>();

            // 关注的用户
            TreeSet<Integer> ids = flowers.get(userId);
            PriorityQueue<News> q = new PriorityQueue<>();


            // 自己的文章
            TreeSet<News> postIds = postTweet.get(userId);

            if (postIds != null && postIds.size() > 0) {
                q.addAll(postIds);
            }
            if (ids != null && ids.size() > 0) {
                for (Integer id : ids) {
                    // 通过自己关注的Id拿到全部文章
                    postIds = postTweet.get(id);
                    if (postIds != null && postIds.size() > 0) {
                        q.addAll(postIds);
                    }

                }
            }

            int cnt = 10;
            while (cnt > 0 && !q.isEmpty()) {
                news.add(q.poll().postId);
                cnt--;
            }

            return news;
        }

        public void follow(int followerId, int followeeId) {
            TreeSet<Integer> ids = flowers.getOrDefault(followerId, new TreeSet<>((a, b) -> a - b));
            ids.add(followeeId);
            flowers.put(followerId, ids);
        }

        public void unfollow(int followerId, int followeeId) {
            TreeSet<Integer> ids = flowers.getOrDefault(followerId, new TreeSet<>((a, b) -> a - b));
            ids.remove(followeeId);
            flowers.put(followerId, ids);
        }


    }

}