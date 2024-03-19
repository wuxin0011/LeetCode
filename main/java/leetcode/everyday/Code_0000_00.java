package leetcode.everyday;

import code_generation.Problem;
import code_generation.utils.IoUtil;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Code_0000_00 {

    public static void main(String[] args) {

        start();

    }

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        String id = "";
        do {
            System.out.print("place input a No as problem NO : ");
            id = scanner.next();

        } while (!check(id));
        // System.out.println("you input No  = " + id);
        int count = getJavaFileCount() - 1;
        String prefix = String.format("Code_0%s_%s", (count < 100 ? "0" + count : String.valueOf(count)), id);
        String javaPath = prefix + ".java";
        String txtPath = "txt_file" + File.separator + prefix + ".txt";
        Problem.create(javaPath, txtPath, Code_0000_00.class);
    }

    public static boolean check(String id) {
        try {
            Integer.valueOf(id);
        } catch (Exception e) {
            System.out.println("place input a valid Number !");
            return false;
        }
        return true;
    }

    public static int getJavaFileCount() {
        String path = IoUtil.buildAbsolutePath(Code_0000_00.class);
        long count = Arrays.stream(Objects.requireNonNull(new File(path).listFiles())).filter(file -> file != null && file.getAbsolutePath().endsWith(".java")).count();
        return (int) count;
    }


}
