package code_generation.contest;

import code_generation.utils.ProblemEveryDayUtils;

import java.util.Objects;
import java.util.Scanner;


/**
 * @author: wuxin0011
 * @Description: 默认的 每日一题模板
 */
public class EveryDay implements CustomProblem {


    public static final EveryDay EVERY_DAY = new EveryDay();


    public void start(Class<?> c) {
        start(c, true);
    }


    public void start(Class<?> c, boolean input) {
        Objects.requireNonNull(c, "class not allow null");
        String id = "";
        if (input) {
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.print("place input a No as problem NO : ");
                id = scanner.next();
            } while (!ProblemEveryDayUtils.check(id));
        }
        int count = Math.max(ProblemEveryDayUtils.getJavaFileCount(c), 0);
        String dir = ProblemEveryDayUtils.createPrefix(count);
        String base = ProblemEveryDayUtils.convertDir(count);
        String name = dir + Constant.FIlE_PREFIX + base;
        if (input) {
            name = name + "_" + id;
        }
        String javaPath = ProblemEveryDayUtils.buildJavaFilePath("", name);
        String txtPath = ProblemEveryDayUtils.buildTxtFilePath(dir, name);
        Problem.create(javaPath, txtPath, c);
    }

}
