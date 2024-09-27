package template.str;

import code_generation.utils.TestUtils;
import template.str.hash.StringHash_Template;
import template.str.kmp.KMP_Template;

import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 * @test url https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 */
public class Test {

    public static void main(String[] args) {

        String text = "aacceeworldafdasworldworld\nfsdafjlkdsafworfdsafworldworfsdafadsfjdasfjkaswroldwordsafaswo5rldworworldffggfdsafasdworld";
        String pattern = "world";
        int n = text.length(), m = pattern.length();
        List<Integer> ks = KMP_Template.kmpList(text, pattern);
        System.out.println("kmp = " + ks);

        StringHash_Template.StringHash sh = StringHash_Template.sh;
        long hash = sh.build(pattern).getHash();
        System.out.println(pattern + " , hash = " + hash);
        sh.build(text);
        List<Integer> strs = sh.getIds(hash, m);
        System.out.println("string hash = " + strs);
        System.out.println("first idx = " + sh.getFirst(hash, m));
        System.out.println("last idx = " + sh.getLast(hash, m));
        System.out.println(TestUtils.deepEqual(strs, ks, true));


        StringHash_Template.StringHashSimple.build(pattern);
        long h1 = StringHash_Template.StringHashSimple.getHash();
        StringHash_Template.StringHashSimple.build(text);
        List<Integer> ids = StringHash_Template.StringHashSimple.getIds(h1, m);
        System.out.println(TestUtils.deepEqual(ids, ks, true));
    }
}
