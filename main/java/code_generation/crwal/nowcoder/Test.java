package code_generation.crwal.nowcoder;

import code_generation.contest.ParseCodeInfo;
import code_generation.utils.IoUtil;
import code_generation.utils.StringUtils;

import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Test {

    static PracticePCTemplate codeTemplate = new PracticePCTemplate();

    public static void main(String[] args) {

        t1();

    }

    static void t1() {
        String url = "https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=295&tqId=23286&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj";

        String s = NCUrl.getPractice(url);
        // String s = IoUtil.readContent(Test.class, "./test/practice.txt");
        t2(s);
    }

    static void t2(String s) {
        ParseCodeInfo parseCodeInfo = codeTemplate.parseCodeTemplate(s);
        System.out.println(parseCodeInfo);
        PracticeTestCase practiceTestCase = new PracticeTestCase();
        int i = StringUtils.kmpSearch(s, "samples");
        List<String> strings = practiceTestCase.parseDefault(s.substring(i));
        for (String string : strings) {
            System.out.println(string);
        }
    }


}
