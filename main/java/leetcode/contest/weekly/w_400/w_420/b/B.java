package leetcode.contest.weekly.w_400.w_420.b;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-420/problems/count-substrings-with-k-frequency-characters-i
 * @title: 字符至少出现 K 次的子字符串 I
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"numberOfSubstrings","B.txt");
    }
     

    public int numberOfSubstrings(String s, int k) {
        char[] a = s.toCharArray();
        int n = a.length;
        int l = 0,r = 0,mx = 0,cnt[] = new int[26];
        int ans = 0;
        int type = -1;
        while(r < n) {
            int id = a[r] - 'a';
            cnt[id]++;
            if(cnt[id] > mx) {
                mx = cnt[id];
                type = id;
            }
            while(mx >= k) {
                ans += (n - r);
                id = a[l] -'a';
                cnt[id]--;
                if(id == type) mx--;
                l++;
            }
            r++;
        }
        return ans;
	}

  

}