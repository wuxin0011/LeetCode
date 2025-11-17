package leetcode.contest.weekly.w_400.w_405.d;

import code_generation.utils.IoUtil;

import java.util.*;

/**
 * 字符串哈希dp
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-405/problems/construct-string-with-minimum-cost
 * @title: 最小代价构造字符串
 */
public class D {


    static class T1 {
        public static void main(String[] args) {
            IoUtil.testUtil(T1.class, "minimumCost", "D.txt");
        }


        public static int[] rnq(int[] a) {
            int[] b = Arrays.copyOf(a,a.length);
            Arrays.sort(b);
            int size = 1;
            for(int i = 1;i < b.length;i++) {
                if(b[i] != b[size-1]) b[size++] = b[i];
            }
            a = new int[size];
            System.arraycopy(b, 0, a, 0, size);
            return a;
        }

        public static int lowerbound(int[] a,int l,int r,int x) {
            while(l <= r) {
                int mid = l + ((r - l)>>1);
                if(a[mid]>=x)r = mid - 1;
                else l = mid + 1;
            }
            return l;
        }


        // 提前检查字符串出现
        public static boolean precheck(String t, String[] ws) {
            boolean[] vist = new boolean[26], vis = new boolean[26];
            for (int i = 0; i < t.length(); i++) {
                vist[t.charAt(i) - 'a'] = true;
            }
            for (String w : ws) {
                for (int i = 0; i < w.length(); i++) {
                    vis[w.charAt(i) - 'a'] = true;
                }
            }
            for (int i = 0; i < 26; i++) {
                if (vist[i] && !vis[i]) {
                    return false;
                }
            }
            return true;
        }


        static int inf = Integer.MAX_VALUE / 2;
        public int minimumCost(String target, String[] words, int[] costs) {
            if(!precheck(target,words)) {
                return -1;
            }
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                list.add(words[i].length());
            }
            int[] arr = rnq(list.stream().mapToInt(x->x).toArray());
            Map<Long,Integer>[] maps = new HashMap[arr.length];
            StringHash sh = new StringHash(target);
            int n = target.length();
            for (int i = 0; i < words.length; i++) {
                int len = words[i].length();
                int index = lowerbound(arr,0,arr.length,len);
                if(maps[index]==null) {
                    maps[index] = new HashMap<>();
                }
                long hash = new StringHash(words[i]).get(0,words[i].length()-1);
                maps[index].merge(hash,costs[i],Integer::min);
            }
            int[] f = new int[n + 1];
            Arrays.fill(f,inf);

            f[0]=0;

            //枚举长度
            for(int i = 1;i<=n;i++) {
                for(int j = 0;j<arr.length;j++) {
                    int len = arr[j];
                    if(len>i)break;
                    int r = i - 1;
                    int l = i - len;
                    long cur_hash = sh.get(l,r);
                    f[i] = Math.min(f[i],f[l] + maps[j].getOrDefault(cur_hash,inf));
                }
            }
            return f[n] >= inf? -1 : f[n];
        }



        static class StringHash {
            private long[] pow, hash;
            // 随机 base 防止被 hack ！
            private static final long BASE = (long) (1e9 + 7) + new Random().nextInt((int) 1e8);

            StringHash(String s) {
                int n = s.length();
                pow = new long[n + 1];
                hash = new long[n + 1];
                pow[0] = 1L;
                hash[0] = s.charAt(0);
                for (int i = 1; i < n; i++) {
                    pow[i] = pow[i - 1] * BASE;
                    hash[i] = (hash[i - 1] * BASE + (s.charAt(i)));
                }
            }

            // [L,R)
            long get(int l, int r) {
                r++;
                long ans = hash[r - 1];
                if (l > 0) {
                    ans = (ans - hash[l - 1] * pow[r - l]);
                }
                return ans;
            }
        }
    }




    static class T2 {
        public static void main(String[] args) {
            IoUtil.testUtil(T2.class, "minimumCost", "D.txt");
        }


        // 提前检查字符串出现
        public static boolean precheck(String t, String[] ws) {
            boolean[] vist = new boolean[26], vis = new boolean[26];
            for (int i = 0; i < t.length(); i++) {
                vist[t.charAt(i) - 'a'] = true;
            }
            for (String w : ws) {
                for (int i = 0; i < w.length(); i++) {
                    vis[w.charAt(i) - 'a'] = true;
                }
            }
            for (int i = 0; i < 26; i++) {
                if (vist[i] && !vis[i]) {
                    return false;
                }
            }
            return true;
        }


        static int inf = Integer.MAX_VALUE / 2;
        public int minimumCost(String target, String[] words, int[] costs) {
            if(!precheck(target,words)) {
                return -1;
            }
            StringHash sh = new StringHash(target);
            int n = target.length();
            TreeMap<Integer, Map<Long,Integer>> minCost = new TreeMap<>();
            for (int i = 0; i < words.length; i++) {
                long hash = new StringHash(words[i]).get(0,words[i].length()-1);
                minCost.computeIfAbsent(words[i].length(),__->new HashMap<>()).merge(hash,costs[i],Integer::min);
            }
            int[] f = new int[n + 1];
            Arrays.fill(f,inf);

            f[0]=0;

            //枚举长度
            for(int i = 1;i<=n;i++) {
                for(Map.Entry<Integer,Map<Long,Integer>> item : minCost.entrySet()) {
                    int len = item.getKey();
                    if(len>i)break;
                    // 计算的区间
                    int r = i - 1;
                    int l = i - len;
                    // [l,r] 的hash (i - 1) - (i - len) + 1 == len
//                System.out.printf("{%s,%s} =%s \n",l,r,sh.get(l,r));
                    long cur_hash = sh.get(l,r);
                    f[i] = Math.min(f[i],f[l] + item.getValue().getOrDefault(cur_hash,inf));
                }
            }
            return f[n] >= inf? -1 : f[n];
        }



        static class StringHash {
            private long[] pow, hash;
            // 随机 base 防止被 hack ！
            private static final long BASE = (long) (1e9 + 7) + new Random().nextInt((int) 1e8);

            StringHash(String s) {
                int n = s.length();
                pow = new long[n + 1];
                hash = new long[n + 1];
                pow[0] = 1L;
                hash[0] = s.charAt(0);
                for (int i = 1; i < n; i++) {
                    pow[i] = pow[i - 1] * BASE;
                    hash[i] = (hash[i - 1] * BASE + (s.charAt(i)));
                }
            }

            // [L,R)
            long get(int l, int r) {
                r++;
                long ans = hash[r - 1];
                if (l > 0) {
                    ans = (ans - hash[l - 1] * pow[r - l]);
                }
                return ans;
            }
        }
    }




}