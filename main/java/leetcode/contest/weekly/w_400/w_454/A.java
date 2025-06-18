package leetcode.contest.weekly.w_400.w_454;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-454/problems/generate-tag-for-video-caption">为视频标题生成标签</a>
 * @title: 为视频标题生成标签
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"generateTag","A.txt");
    }
    private static final int MOD = (int)1e9 + 7;


    // 这题的特殊输入和题解解析设计冲突了但是没必要为了这一个题而修改

    public String generateTag(String a) {
        List<String> lt = new ArrayList<>();
        String[] ss = a.split(" ");
        for(String temp : ss) {
            if(temp.length() == 0) continue;
            lt.add(temp);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for(int i = 0;i < lt.size();i++) {
            if(i==0){
                sb.append(lt.get(i).toLowerCase());
            }else{
                String temp = lt.get(i);
                temp = temp.toLowerCase();
                char[] cs = temp.toCharArray();
                StringBuilder xx = new StringBuilder();
                for(int j = 0;j < cs.length;j++) {
                    if(j == 0) {
                        xx.append(f(cs[j]));
                    }else{
                        xx.append(cs[j]);
                    }
                }
                sb.append(xx);
            }
        }
        char[] ccc = sb.toString().toCharArray();
        int n = Math.min(100,ccc.length);
        sb = new StringBuilder();
        for(int i = 0;i < n;i++) sb.append(ccc[i]);
        return sb.toString();
    }


    public static char f(char x) {
        if('A'<=x &&x<='Z')return x;
        return (char)(x - 'a' + 'A');
    }

  

}