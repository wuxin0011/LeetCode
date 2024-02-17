package leetcode.utils;

import leetcode.annotation.Description;
import leetcode.utils.Bean.Type;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description:
 */
public class ReflectUtils {


    private static final String EMPTY_STR = "";

    public static void main(String[] args) {
        // test ...
        List<List<Integer>> ans = new ArrayList<>();
        System.out.println(ans.getClass().getSimpleName());
    }

    public static <T> String getClassInfo(Class<T> c) {
        if (c == null) {
            return EMPTY_STR;
        }
        return getDescriptionInfo(c.getAnnotation(Description.class), c);
    }

    public static <T> String getMethodInfo(Class<T> c, String methodName) {
        try {
            return getMethodInfo(c.getDeclaredMethod(methodName, c));
        } catch (NoSuchMethodException e) {
            return EMPTY_STR;
        }
    }


    public static <T> String getMethodInfo(Method method) {
        if (method == null) {
            return EMPTY_STR;
        }
        Description annotation = method.getAnnotation(Description.class);
        return getDescriptionInfo(annotation, method.getClass());
    }

    public static <T> String getDescriptionInfo(Description description, Class<T> c) {
        if (description == null) {
            return EMPTY_STR;
        }
        // tag
        String tag = description.tag() != null ? description.tag().getTag() : EMPTY_STR;
        tag = isEmpty(description.customTag()) ? tag : description.customTag();
        // diff
        String difficulty = description.diff() != null ? description.diff().getDesc() : EMPTY_STR;
        // url
        String url = description.url();
        // desc
        String desc = description.value();
        // types
        String types = getTypes(description);
        // views
        String views = getViews(description);
        return CustomColor.success("======================================题目信息=======================" +
                (!isEmpty(desc) ? "\n简介: " + desc : "") +
                (!isEmpty(tag) ? "\n标签: " + tag : "") +
                (!isEmpty(types) ? "\n类型: " + types : "") +
                (!isEmpty(difficulty) ? "\n难度: " + difficulty : "") +
                (!isEmpty(url) ? "\n题目地址: " + url : "") +
                (!isEmpty(views) ? "\n参考链接: " + views : "") +
                "\n======================================输出结果=========================");
    }


    public static String getTypes(Description description) {
        Type[] types = description.types();
        String[] customTypes = description.customType();
        StringBuilder typeBuilder = new StringBuilder();
        if (types != null) {
            for (Type type : types) {
                if (!isEmpty(type.getType())) {
                    typeBuilder.append(type);
                }
            }
        }
        if (customTypes != null) {
            for (String type : customTypes) {
                if (!isEmpty(type)) {
                    typeBuilder.append(type);
                }
            }
        }
        return typeBuilder.toString();
    }


    private static String getViews(Description description) {
        if (description == null) {
            return EMPTY_STR;
        }
        StringBuilder builder = new StringBuilder();
        String[] views = description.views();
        if (isEmpty(views)) {
            return EMPTY_STR;
        }
        int len = views.length;
        for (int i = 0; i < len; i++) {
            if (!isEmpty(views[i])) {
                builder.append((int) (i + 1));
                builder.append(" 、");
                builder.append(views[i]);
                if (i != len - 1) {
                    builder.append(" , ");
                }
            }

        }

        return "null".contentEquals(builder) || EMPTY_STR.contentEquals(builder) ? EMPTY_STR : builder.toString();
    }


    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isEmpty(String[] s) {
        return s == null || s.length == 0;
    }



    public static <T> Object parseArg(Class<T> c, String input) {
        return parseArg(c.getSimpleName(), input);
    }

    public static Object parseArg(String type, String input) {
        if (String.valueOf(type).equals("null") || "".equals(input) || input.length() == 0) {
            System.out.println("input or type is null");
            return null;
        }
        // System.out.println("simpleName:" + type);
        if ("int".equals(type) || "Integer".equals(type)) {
            return Integer.parseInt(input);
        } else if ("long".equals(type) || "Long".equals(type)) {
            return Long.parseLong(input);
        } else if ("boolean".equals(type) || "Boolean".equals(type)) {
            return Boolean.parseBoolean(input);
        } else if ("double".equals(type) || "Double".equals(type)) {
            return Double.parseDouble(input);
        } else if ("float".equals(type) || "Float".equals(type)) {
            return Float.parseFloat(input);
        } else if ("char".equals(type) || "Char".equals(type)) {
            return input.toCharArray()[0];
        } else if ("String".equals(type)) {
            return input;
        } else if ("int[]".equals(type)) {
            return oneIntArray(input);
        } else if ("int[][]".equals(type)) {
            return doubleIntArray(input);
        } else if ("int[][][]".equals(type)) {
            return threeIntArray(input);
        } else if ("String[]".equals(type)) {
            return oneStringArray(input);
        } else if ("String[][]".equals(type)) {
            return doubleStringArray(input);
        } else if ("char[]".equals(type)) {
            return oneCharArray(input);
        } else if ("char[][]".equals(type)) {
            return doubleCharArray(input);
        }
        // todo more type
        return input;
    }


    public static int[] oneIntArray(String input) {
        List<Integer> ls = new ArrayList<>();
        StringBuilder sb = null;
        char[] cs = input.toCharArray();
        // [1,2,3,4,45,5,5,100]
        for (char c : cs) {
            if (c == ' ') continue;
            if (c == '[') {
                sb = new StringBuilder();
            } else if (c == ']' || c == ',') {
                assert sb != null;
                try {
                    ls.add(Integer.parseInt(sb.toString()));
                } catch (NumberFormatException e) {
                    // ignore ...
                }
                sb = new StringBuilder();
            } else {
                assert sb != null;
                sb.append(c);
            }
        }

        int[] ans = new int[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            ans[i] = ls.get(i);
        }
        return ans;
    }

    public static int[][] doubleIntArray(String input) {
//        System.out.println("is int[][]" + ",input = " + input);
        StringBuilder sb = null;
        List<List<Integer>> ls = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        char[] cs = input.toCharArray();
        for (int i = 1; i < cs.length; i++) {
            char c = cs[i];
            if (c == ' ') continue;

            if (c == '[') {
                sb = new StringBuilder();
                temp = new ArrayList<>();
            } else if (c == ',') {
                assert sb != null;
                try {
                    temp.add(Integer.parseInt(sb.toString()));

                } catch (NumberFormatException e) {
                    // ignore
                } finally {
                    sb = new StringBuilder();
                }
            } else if (c == ']') {
                assert sb != null;
                temp.add(Integer.parseInt(sb.toString()));
                ls.add(temp);
                if (i == cs.length - 2 || cs[i + 1] == ']') break;
                sb = new StringBuilder();
                temp = new ArrayList<>();
            } else {
                assert sb != null;
                sb.append(c);
            }
        }

        int row = ls.size(), col = ls.get(0).size();
        int[][] ans = new int[row][col];
        for (int i = 0; i < row; i++) {
            List<Integer> t = ls.get(i);
            for (int j = 0; j < col; j++) {
                ans[i][j] = t.get(j);
            }
        }
       //  System.out.println("int[][] convert=>" + Arrays.deepToString(ans));
        return ans;
    }

    public static int[][][] threeIntArray(String input) {
//        System.out.println("is int[][][]" + ",input = " + input);
        StringBuilder sb = null;
        List<List<List<Integer>>> ls = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        // TODO ...
        // System.out.println("int[][][] convert=>" + Arrays.deepToString(ans));
        return null;
    }

    public static String[] oneStringArray(String input) {
        List<String> ls = new ArrayList<>();
        StringBuilder sb = null;
        char[] cs = input.toCharArray();
        for (char c : cs) {
            if (c == '\'' || c == '\"') continue;
            if (c == '[') {
                sb = new StringBuilder();
                continue;
            }
            if (sb == null) break;
            if (c == ']') {
                ls.add(sb.toString());
                break;
            } else if (c == ',') {
                ls.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }
        String[] ans = new String[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            ans[i] = ls.get(i);
        }
//        System.out.println("parse args =>" + Arrays.toString(ans));
        return ans;
    }

    public static String[][] doubleStringArray(String input) {
        return null;
    }


    public static char[] oneCharArray(String input) {
        // ["a","b","c"]
        List<Character> ls = new ArrayList<>();
        StringBuilder sb = null;
        char[] charArray = input.toCharArray();
        for (char c : charArray) {
            if (c == '[' || c == ']' || c == ',' || c == '\'' || c == '\"') {
                continue;
            }
            ls.add(c);
        }
        char[] cs = new char[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            cs[i] = ls.get(i);
        }
        return cs;
    }

    public static char[][] doubleCharArray(String input) {
        // [["a","b","c"],["a","b","c"]]
        List<List<Character>> ls = new ArrayList<>();
        char[] charArray = input.toCharArray();
        List<Character> temp = null;
        for (char c : charArray) {
            if (c == ' ' || c == '\'' || c == '\"' || c == '\n' || c == '\t' || c == '\b' || c == ',') continue;
            if (c == '[') {
                temp = new ArrayList<>();
            } else if (c == ']') {
                if (temp != null) {
                    ls.add(temp);
                }
                temp = null;
            } else {
                if (temp != null) {
                    temp.add(c);
                }
            }
        }
        int row = ls.size(), col = ls.get(0).size();
        char[][] cs = new char[row][col];
        for (int i = 0; i < row; i++) {
            List<Character> cc = ls.get(i);
            for (int j = 0; j < cc.size(); j++) {
                cs[i][j] = cc.get(j);
            }
        }
        return cs;
    }
}
