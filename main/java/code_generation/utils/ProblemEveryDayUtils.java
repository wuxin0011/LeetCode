package code_generation.utils;

import code_generation.contest.Constant;

import java.io.File;

/**
 * @author: wuxin0011
 * @Description:
 */
public class ProblemEveryDayUtils {

    public static String buildTxtFilePath(String dir, String name) {
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        if (name.contains(dir)) {
            name = name.replace(dir, "");
        }
        if (!name.endsWith(Constant.TXT_FILE_SUFFIX)) {
            name = name + Constant.TXT_FILE_SUFFIX;
        }
        if (!dir.contains(Constant.TXT_FILE_PREFIX)) {
            dir = dir + Constant.TXT_FILE_PREFIX + File.separator;
        }
        return dir + name;
    }

    public static String buildJavaFilePath(String dir, String name) {
        if (!name.endsWith(Constant.JAVA_FILE_SUFFIX)) {
            name = name + Constant.JAVA_FILE_SUFFIX;
        }
        if (dir == null || dir.length() == 0) {
            return name;
        }
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        return dir + name;
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

    public static int getJavaFileCount(Class<?> c) {
        String path = IoUtil.buildAbsolutePath(c);
        int count = countDirJavaFile(new File(path));
        return (int) count;
    }

    public static boolean isJavaFile(File f) {
        if (f == null || !f.isFile()) return false;
        return f.getName().startsWith(Constant.FIlE_PREFIX) && f.getName().endsWith(Constant.JAVA_FILE_SUFFIX);
    }


    public static int countDirJavaFile(File file) {
        if (file == null) {
            return 0;
        }
        int res = 0;
        if (isJavaFile(file)) {
            res++;
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return 0;
            }
            for (File f : files) {
                if (isJavaFile(f)) {
                    res++;
                } else if (f.isDirectory()) {
                    res += countDirJavaFile(f);
                }
            }
        }
        return res;
    }

    public static String convertDir(int count) {
        if (count >= 1000) {
            return String.valueOf(count);
        }
        if (count >= 100) {
            return "0" + count;
        }
        if (count >= 10) {
            return "00" + count;
        }
        return "000" + count;
    }

    public static String createPrefix(int count) {
        int baseDir = Math.max(0, count / 100 * 100);
        return Constant.DIR_PREFIX + (count < 100 ? "000" : String.valueOf(baseDir)) + File.separator;
    }
}
