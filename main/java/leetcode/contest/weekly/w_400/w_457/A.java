package leetcode.contest.weekly.w_400.w_457;

import code_generation.utils.IoUtil;

import java.util.*;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-457/problems/coupon-code-validator">优惠券校验器</a>
 * @title: 优惠券校验器
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"validateCoupons","A.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    static Set<String> s = new HashSet<>();
    static {
        s.add("electronics");
        s.add("grocery");
        s.add("pharmacy");
        s.add("restaurant");
    }
//    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
//        int n = code.length;
//        int[] a = new int[n];
//        Arrays.setAll(a,i->i);
//        Arrays.stream(a).filter(i->{
//            if(!s.contains(businessLine[i])) {
//                return false;
//            }
//            return true;
//        }).map(x->{
//            return x;
//        });
//        return null;
//    }


    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        int n = code.length;
        List<String> ans = new ArrayList<>();
        List<Integer> idx = new ArrayList<>();
        List<String> list = new ArrayList<>(Arrays.asList("electronics", "grocery", "pharmacy", "restaurant"));
        for (int i = 0; i < n; i++) {
            if (code[i] != null && code[i].length() > 0 && valid(code[i]) && isActive[i]) {
                for (String s : list) {
                    if (s.equals(businessLine[i])) idx.add(i);
                }
            }
        }
        idx.sort((i, j) -> {
            if (!businessLine[i].equals(businessLine[j])) return  businessLine[i].compareTo(businessLine[j]);
            return code[i].compareTo(code[j]);
        });
        for (int i : idx) {
            ans.add(code[i]);
        }
        return ans;
    }
    private boolean valid(String s) {
        for (char c : s.toCharArray()) {
            if (!(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9' || c == '_')) {
                return false;
            }
        }
        return  true;
    }

  

}