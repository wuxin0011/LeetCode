package code_generation;

import java.util.Scanner;

/**
 * @author: wuxin0011
 * @Description:
 */
public interface Contest {


    /**
     * 根据时间自动生成
     */
    default void auto() {
        next();
    }


    /**
     * 下一场次
     */
    default void next() {

    }


    /**
     * 根据输入自定义生成
     */
    default void createNo() {
        int NO;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("place input a valid contest number : ");
            try {
                NO = sc.nextInt();
                if (NO <= 0) {
                    continue;
                }
                break;
            } catch (Exception ignored) {
                sc.next();
            }
        }
        createNo(NO);
    }


    /**
     * 根据序号生成
     */
    default void createNo(int NO) {
        System.out.println("place implement this method");
    }


}
