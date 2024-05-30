package leetcode.top_interview_150.lru_cache;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存
 * <p>
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * <p>
 * 示例：
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 * <p>
 * 提示：
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 10^5
 * 最多调用 2 * 10^5 次 get 和 put
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/lru-cache
 * @title: 缓存
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(LRUCache.class, ParseCodeInfo.ConstructorClass, "in.txt");
    }


    public static class LRUCache {

        public static class Node {
            int key;
            int val;

            Node front;
            Node next;

            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }


        public static class NodeList {
            Node head;
            Node tail;

            NodeList() {
                head = null;
                tail = null;
            }


            void addNode(Node node) {
                if (node == null) {
                    return;
                }
                // 首次创建节点
                if (head == null) {
                    head = node;
                    tail = node;
                } else {
                    // 最新节点
                    tail.next = node;
                    node.front = tail;
                    tail = node;
                }
            }

            void moveEnd(Node node) {
                if (node == null || node == tail) {
                    return;
                }
                if (head == node) {
                    head = node.next;
                    if (head != null && head.front != null) {
                        head.front = null;
                    }
                } else {
                    // 断开
                    node.front.next = node.next;
                    node.next.front = node.front;

                }
                // 链接到尾部
                node.front = tail;
                node.next = null;
                tail.next = node;
                tail = node;

            }

            int remove() {
                if (head == null) {
                    return -1;
                }
                Node cur = head;
                int key = head.key;
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head = cur.next;
                    cur.next = null;
                    head.front = null;
                }
                return key;
            }
        }

        public Map<Integer, Node> map;

        int capacity;

        NodeList nodeList;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.nodeList = new NodeList();
            this.map = new HashMap<>();
        }

        public int get(int key) {
            Node cur = map.get(key);
            if (cur == null) {
                return -1;
            }
            int val = cur.val;
            nodeList.moveEnd(cur);
            return val;
        }

        public void put(int key, int val) {
            Node cur = map.get(key);
            if (cur != null) {
                cur.val = val;
                // remove end
                nodeList.moveEnd(cur);
            } else {
                if (map.size() >= capacity) {
                    map.remove(nodeList.remove());
                }
                Node node = new Node(key, val);
                nodeList.addNode(node);
                map.put(key, node);
            }

        }


    }

}