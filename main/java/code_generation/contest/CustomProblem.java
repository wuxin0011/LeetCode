package code_generation.contest;

/**
 * @author: wuxin0011
 * @Description: 自定义题目接口
 */
public interface CustomProblem {

    default void start(Class<?> c) {
        start(c, false);
    }

    /**
     * 必须实现
     * <p>
     * 自定义一个生成模板
     *
     * @param c     类
     * @param input 是否需要输入
     * @see code_generation.crwal.leetcode.LCCustom#start(Class, boolean)
     * @see code_generation.crwal.leetcode.LCEveryDay#start(Class, boolean)
     */
    void start(Class<?> c, boolean input);


    /**
     * 创建题目
     * @param problemInfo 题目信息
     * @see code_generation.crwal.leetcode.LCEveryDay#createTemplate(ProblemInfo)
     */
    default void createTemplate(ProblemInfo problemInfo) {
        System.out.println("====info start ======");
        System.out.println(problemInfo);
        System.out.println("====end======");
        System.out.println("if not create file ,place implements !");
    }
}
