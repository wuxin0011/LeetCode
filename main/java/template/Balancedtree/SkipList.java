package template.Balancedtree;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description: 调表的实现
 */
public class SkipList {
    // 跳表最大层的限制
    public static int MAXL = 20;

    public static int MAXN = 100001;

    // 空间使用计数
    public static int cnt;

    // 节点的key
    public static int[] key = new int[MAXN];

    // 节点key的计数
    public static int[] count = new int[MAXN];

    // 节点拥有多少层指针
    public static int[] level = new int[MAXN];

    // 节点每一层指针指向哪个节点
    public static int[][] next = new int[MAXN][MAXL + 1];

    // 节点每一层指针的长度(底层跨过多少数，左开右闭)
    public static int[][] len = new int[MAXN][MAXL + 1];

    // 建立跳表
    public static void build() {
        cnt = 1;
        key[cnt] = Integer.MIN_VALUE;
        level[cnt] = MAXL;
    }

    // 使用多少空间一律清空
    public static void clear() {
        Arrays.fill(key, 1, cnt + 1, 0);
        Arrays.fill(count, 1, cnt + 1, 0);
        Arrays.fill(level, 1, cnt + 1, 0);
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(next[i], 0);
            Arrays.fill(len[i], 0);
        }
        cnt = 0;
    }

    // 扔骰子决定节点的层数
    public static int random() {
        int ans = 1;
        while (Math.random() < 0.5) {
            ans++;
        }
        return Math.min(ans, MAXL);
    }

    // 当前在i号节点的h层，返回key为num的节点，空间编号是多少
    public static int find(int i, int h, int num) {
        while (next[i][h] != 0 && key[next[i][h]] < num) {
            i = next[i][h];
        }
        if (h == 1) {
            if (next[i][h] != 0 && key[next[i][h]] == num) {
                return next[i][h];
            } else {
                return 0;
            }
        }
        return find(i, h - 1, num);
    }

    // 增加num，重复加入算多个词频
    public static void add(int num) {
        if (find(1, MAXL, num) != 0) {
            addCount(1, MAXL, num);
        } else {
            key[++cnt] = num;
            count[cnt] = 1;
            level[cnt] = random();
            addNode(1, MAXL, cnt);
        }
    }

    // 当前在i号节点的h层，num增加一个词频
    public static void addCount(int i, int h, int num) {
        while (next[i][h] != 0 && key[next[i][h]] < num) {
            i = next[i][h];
        }
        if (h == 1) {
            count[next[i][h]]++;
        } else {
            addCount(i, h - 1, num);
        }
        len[i][h]++;
    }

    // 当前在i号节点的h层，插入空间编号为j的节点
    // 返回值：从i号节点出发，直到把空间编号为j的节点插入，底层总共有多少数字比key[j]小
    // 返回值很重要，因为上游需要这个信息来改动指针的长度信息
    public static int addNode(int i, int h, int j) {
        int rightCnt = 0;
        while (next[i][h] != 0 && key[next[i][h]] < key[j]) {
            rightCnt += len[i][h];
            i = next[i][h];
        }
        if (h == 1) {
            next[j][h] = next[i][h];
            next[i][h] = j;
            len[j][h] = count[next[j][h]];
            len[i][h] = count[next[i][h]];
            return rightCnt;
        } else {
            int downCnt = addNode(i, h - 1, j);
            if (h > level[j]) {
                len[i][h]++;
            } else {
                next[j][h] = next[i][h];
                next[i][h] = j;
                len[j][h] = len[i][h] + 1 - downCnt - count[j];
                len[i][h] = downCnt + count[j];
            }
            return rightCnt + downCnt;
        }
    }

    // 删除x，如果有多个，只删掉一个
    public static void remove(int num) {
        int j = find(1, MAXL, num);
        if (j != 0) {
            if (count[j] > 1) {
                removeCount(1, MAXL, num);
            } else {
                removeNode(1, MAXL, j);
            }
        }
    }

    // 当前在i号节点的h层，num减少一个词频
    public static void removeCount(int i, int h, int num) {
        while (next[i][h] != 0 && key[next[i][h]] < num) {
            i = next[i][h];
        }
        if (h == 1) {
            count[next[i][h]]--;
        } else {
            removeCount(i, h - 1, num);
        }
        len[i][h]--;
    }

    // 当前在i号节点的h层，删除空间编号为j的节点
    public static void removeNode(int i, int h, int j) {
        if (h < 1) {
            return;
        }
        while (next[i][h] != 0 && key[next[i][h]] < key[j]) {
            i = next[i][h];
        }
        if (h > level[j]) {
            len[i][h]--;
        } else {
            next[i][h] = next[j][h];
            len[i][h] += len[j][h] - 1;
        }
        removeNode(i, h - 1, j);
    }

    // 查询num的排名
    public static int rank(int num) {
        return small(1, MAXL, num) + 1;
    }

    // 当前在i号节点的h层，查询有多少个数字比num小
    public static int small(int i, int h, int num) {
        int rightCnt = 0;
        while (next[i][h] != 0 && key[next[i][h]] < num) {
            rightCnt += len[i][h];
            i = next[i][h];
        }
        if (h == 1) {
            return rightCnt;
        } else {
            return rightCnt + small(i, h - 1, num);
        }
    }

    // 查询排名第x的key是什么
    public static int index(int x) {
        return index(1, MAXL, x);
    }

    // 当前在i号节点的h层，查询排名第x的key是什么
    public static int index(int i, int h, int x) {
        int c = 0;
        while (next[i][h] != 0 && c + len[i][h] < x) {
            c += len[i][h];
            i = next[i][h];
        }
        if (h == 1) {
            return key[next[i][h]];
        } else {
            return index(i, h - 1, x - c);
        }
    }

    // 查询num的前驱
    public static int pre(int num) {
        return pre(1, MAXL, num);
    }

    public static int pre(int i, int h, int num) {
        while (next[i][h] != 0 && key[next[i][h]] < num) {
            i = next[i][h];
        }
        if (h == 1) {
            return i == 1 ? Integer.MIN_VALUE : key[i];
        } else {
            return pre(i, h - 1, num);
        }
    }

    // 查询num的后继
    public static int post(int num) {
        return post(1, MAXL, num);
    }

    public static int post(int i, int h, int num) {
        while (next[i][h] != 0 && key[next[i][h]] < num) {
            i = next[i][h];
        }
        if (h == 1) {
            if (next[i][h] == 0) {
                return Integer.MAX_VALUE;
            }
            if (key[next[i][h]] > num) {
                return key[next[i][h]];
            }
            i = next[i][h];
            if (next[i][h] == 0) {
                return Integer.MAX_VALUE;
            } else {
                return key[next[i][h]];
            }
        } else {
            return post(i, h - 1, num);
        }
    }
}
