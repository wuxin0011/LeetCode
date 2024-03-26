package code_generation.contest;

import java.text.ParseException;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
public interface TestCase {

    /**
     * 解析默认周赛
     *
     * @param input
     * @return
     */
    List<String> parseContest(String input) throws ParseException;


    /**
     * 解析默认
     *
     * @param input
     * @return
     */
    List<String> parseDefault(String input) throws ParseException;
}
