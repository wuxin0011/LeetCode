package code_generation.crwal.nowcoder;

import code_generation.contest.ParseCodeInfo;
import code_generation.contest.ParseCodeTemplate;
import code_generation.contest.TestCase;
import code_generation.crwal.TestCaseUtil;

import java.text.ParseException;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Practice {

    public ParseCodeTemplate codeTemplate;
    public TestCase testCase;
    public String input;
    public ParseCodeInfo codeInfo;
    public String test;


    public Practice(String input) {
        this.input = input;
        this.testCase = new PracticeTestCase();
        this.codeTemplate = new PracticePCTemplate();
        init();
    }

    void init() {
        try {
            this.test = TestCaseUtil.testCaseToString(testCase.parseContest(this.input));
            this.codeInfo = codeTemplate.parseCodeTemplate(this.input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
