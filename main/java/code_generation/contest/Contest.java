package code_generation.contest;

import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
public interface Contest {


    /***
     * 获取本次比赛编号
     * @return ID
     */
    int getId();


    /**
     * 获取比赛所有题目链接
     *
     * @param id id
     * @return urls
     */
    List<String> getUrls(int id);


    /**
     * 解析测试案例
     *
     * @return 返回测试案例
     */
    String parseTestCase(String input);


    /**
     * 解析代码快内容
     *
     * @return 返回代码快内容
     */
    Object parseCodeTemplate(String input);


    /**
     * 生成template
     */
    void generatorTemplate();

}
