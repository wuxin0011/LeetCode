package template.Balancedtree;

/**
 * https://leetcode.cn/problems/jBjn9C/submissions/667910277/
 * https://www.luogu.com.cn/problem/P3369
 *
 *
 * 您需要动态地维护一个可重集合 M，并且提供以下操作：
 *
    *     1 向 M 中插入一个数 x。
    *     2 从 M 中删除一个数 x（若有多个相同的数，应只删除一个）。
    *     3 查询 M 中有多少个数比 x 小，并且将得到的答案加一。
    *     4 查询如果将 M 从小到大排列后，排名位于第 x 位的数。
    *     5 查询 M 中 x 的前驱（前驱定义为小于 x，且最大的数）。
    *     6 查询 M 中 x 的后继（后继定义为大于 x，且最小的数）。
 * @author: wuxin0011
 * @Description: AVL 平衡树实现
 */
public class AVL {

    public int cnt, head;
    public static int[] key, height, left, right, count, size;

    public AVL(int n) {
        key = new int[n];
        height = new int[n];
        left = new int[n];
        right = new int[n];
        count = new int[n];
        size = new int[n];
        cnt = head = 0;
    }

    public void up(int i) {
        size[i] = size[left[i]] + size[right[i]] + count[i];
        height[i] = Math.max(height[left[i]], height[right[i]]) + 1;
    }

    // i节点为头的树左旋，返回左旋后头节点的空间编号
    private int leftRotate(int i) {
        int r = right[i];
        right[i] = left[r];
        left[r] = i;
        up(i);
        up(r);
        return r;
    }

    private int rightRotate(int i) {
        int l = left[i];
        left[i] = right[l];
        right[l] = i;
        up(i);
        up(l);
        return l;
    }

    // 检查i节点为头的树是否违规
    // 如果命中了某种违规情况，就进行相应调整
    // 返回树的头节点的空间编号
    public int maintain(int i) {
        int lh = height[left[i]];
        int rh = height[right[i]];
        if (lh - rh > 1) {
            if (height[left[left[i]]] >= height[right[left[i]]]) {
                i = rightRotate(i);
            } else {
                left[i] = leftRotate(left[i]);
                i = rightRotate(i);
            }
        } else if (rh - lh > 1) {
            if (height[right[right[i]]] >= height[left[right[i]]]) {
                i = leftRotate(i);
            } else {
                right[i] = rightRotate(right[i]);
                i = leftRotate(i);
            }
        }
        return i;
    }

    // 增加数字num，重复加入算多个词频
    public void add(int num) {
        head = add(head, num);
    }

    // 当前来到i号节点，num这个数字一定会加入以i为头的树
    // 树结构有可能变化，返回头节点编号
    private int add(int i, int num) {
        if (i == 0) {
            key[++cnt] = num;
            count[cnt] = size[cnt] = height[cnt] = 1;
            return cnt;
        }
        if (key[i] == num) {
            count[i]++;
        } else if (key[i] > num) {
            left[i] = add(left[i], num);
        } else {
            right[i] = add(right[i], num);
        }
        up(i);
        return maintain(i);
    }

    // 删除数字num，如果有多个，只删掉一个
    public void remove(int num) {
        if (rank(num) != rank(num + 1)) {
            head = remove(head, num);
        }
    }

    // 当前来到i号节点，以i为头的树一定会减少1个num
    // 树结构有可能变化，返回头节点编号
    private int remove(int i, int num) {
        if (key[i] < num) {
            right[i] = remove(right[i], num);
        } else if (key[i] > num) {
            left[i] = remove(left[i], num);
        } else {
            if (count[i] > 1) {
                count[i]--;
            } else {
                if (left[i] == 0 && right[i] == 0) {
                    return 0;
                } else if (left[i] != 0 && right[i] == 0) {
                    i = left[i];
                } else if (left[i] == 0 && right[i] != 0) {
                    i = right[i];
                } else {
                    int mostLeft = right[i];
                    while (left[mostLeft] != 0) {
                        mostLeft = left[mostLeft];
                    }
                    right[i] = removeMostLeft(right[i], mostLeft);
                    left[mostLeft] = left[i];
                    right[mostLeft] = right[i];
                    i = mostLeft;
                }
            }
        }
        up(i);
        return maintain(i);
    }

    // 以i号节点为头的树上，最左节点的编号一定是mostLeft
    // 删掉这个节点，并保证树的平衡性，返回头节点的编号
    private int removeMostLeft(int i, int mostLeft) {
        if (i == mostLeft) {
            return right[i];
        } else {
            left[i] = removeMostLeft(left[i], mostLeft);
            up(i);
            return maintain(i);
        }
    }

    // 查询num的排名，比num小的数字个数+1，就是num的排名
    public int rank(int num) {
        return small(head, num) + 1;
    }

    // 以i为头的树上，比num小的数字有几个
    private int small(int i, int num) {
        if (i == 0) {
            return 0;
        }
        if (key[i] >= num) {
            return small(left[i], num);
        } else {
            return size[left[i]] + count[i] + small(right[i], num);
        }
    }

    public int index(int x) {
        return index(head, x);
    }

    private int index(int i, int x) {
        if (size[left[i]] >= x) {
            return index(left[i], x);
        } else if (size[left[i]] + count[i] < x) {
            return index(right[i], x - size[left[i]] - count[i]);
        }
        return key[i];
    }

    // 小于 num 的最大一个数
    public int floor(int num) {
        return floor(head, num);
    }

    private int floor(int i, int num) {
        if (i == 0) {
            return Integer.MIN_VALUE;
        }
        if (key[i] >= num) {
            return floor(left[i], num);
        } else {
            return Math.max(key[i], floor(right[i], num));
        }
    }

    // 大于 num 的第一个数
    public int ceil(int num) {
        return ceil(head, num);
    }

    private int ceil(int i, int num) {
        if (i == 0) {
            return Integer.MAX_VALUE;
        }
        if (key[i] <= num) {
            return ceil(right[i], num);
        } else {
            return Math.min(key[i], ceil(left[i], num));
        }
    }

    public void clear() {
        for (int i = 0; i <= cnt; i++) {
            key[i] = left[i] = right[i] = count[i] = size[i] = height[i] = 0;
        }
        cnt = 0;
        head = 0;
    }
}