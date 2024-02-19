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
        System.out.println(TreeNode.class.getSimpleName());
//        List<List<Integer>> ans = new ArrayList<>();
//        ArrayList<Integer> integers = new ArrayList<>();
//        integers.add(100);
//        ans.add(integers);
//        System.out.println(ans.getClass().getSimpleName());
//        System.out.println(ans.get(0).getClass().getSimpleName());
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


    public static <T> Object parseArg(Object src, String methodName, Class<T> c, String input) {
        return parseArg(src, methodName, c.getSimpleName(), input);
    }

    public static Object parseArg(Object src, String methodName, String type, String input) {
        if ("".equals(input) || input.length() == 0) {
            System.out.println("read content is null");
            return null;
        }
        if ("void".equals(type)) {
            System.out.println("void type not support place check type !");
            return null;
        }
        input = toString(input);
        try {
            switch (type) {
                case "int":
                case "Integer":
                    return Integer.parseInt(input);
                case "long":
                case "Long":
                    return Long.parseLong(input);
                case "boolean":
                case "Boolean":
                    return input.contains("t");
                case "double":
                    return Double.parseDouble(input);
                case "float":
                    return Float.parseFloat(input);
                case "int[]":
                    return oneIntArray(input);
                case "int[][]":
                    return doubleIntArray(input);
                case "int[][][]":
                    return threeIntArray(input);
                case "char":
                    return input.toCharArray()[0];
                case "char[]":
                    return oneCharArray(input);
                case "char[][]":
                    return doubleCharArray(input);
                case "char[][][]":
                    return threeCharArray(input);
                case "string":
                case "String":
                    return toString(input);
                case "string[]":
                case "String[]":
                    return oneStringArray(input);
                case "string[][]":
                case "String[][]":
                    return doubleStringArray(input);
                case "string[][][]":
                case "String[][][]":
                    return threeStringArray(input);
                case "TreeNode":
                    return TreeNode.widthBuildTreeNode(oneStringArray(input));
                case "ListNode":
                    return ListNode.createListNode(oneIntArray(input));
                case "List":
                case "ArrayList":
                    return toList(src.getClass(), methodName, type, input);
                default:
                    System.out.println(type + " not implement ,place implement!");
                    return null;
            }
        } catch (NumberFormatException e) {
            // e.printStackTrace();
            info(type);
            return null;
        }

    }

    public static String toString(String input) {
        char[] charArray = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            if (isIgnore(c)) continue;
            sb.append(c);
        }
        return sb.toString();
    }

    private static Object threeStringArray(String input) {
        System.out.println("string[][][] format not implement");
        return null;
    }


    public static int[] oneIntArray(String input) {
        List<Integer> ls = parseListInteger(input);
        int[] ans = new int[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            ans[i] = ls.get(i);
        }
        return ans;
    }

    public static int[][] doubleIntArray(String input) {
        List<List<Integer>> ls = parseListDoubleInteger(input);
        int row = ls.size(), col = ls.get(0).size();
        int[][] ans = new int[row][col];
        for (int i = 0; i < row; i++) {
            List<Integer> t = ls.get(i);
            for (int j = 0; j < col; j++) {
                ans[i][j] = t.get(j);
            }
        }
        return ans;
    }

    public static int[][][] threeIntArray(String input) {
        System.out.println("int[][][] format data not implement!");
//        System.out.println("is int[][][]" + ",input = " + input);
        StringBuilder sb = null;
        List<List<List<Integer>>> ls = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        // TODO ...
        // System.out.println("int[][][] convert=>" + Arrays.deepToString(ans));
        return null;
    }


    public static String[] oneStringArray(String input) {
        List<String> ls = parseListString(input);
        String[] ans = new String[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            ans[i] = ls.get(i);
        }
        return ans;
    }

    public static String[][] doubleStringArray(String input) {
        return null;
    }


    public static char[] oneCharArray(String input) {
        // ["a","b","c"]
        List<Character> ls = parseListChar(input);
        char[] cs = new char[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            cs[i] = ls.get(i);
        }
        return cs;
    }

    public static char[][] doubleCharArray(String input) {
        List<List<Character>> ls = parseListDoubleChar(input);
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

    public static <T> Object toList(Class<T> t, String methodName, String type, String input) {
        ArrayList<Object> ans = new ArrayList<>();
        // check
        // System.out.println("input ======>" + input);
        String listType = IoUtil.findListReturnTypeMethod(t, methodName, type);
        switch (listType) {
            case "List<String>":
                return parseListString(input);
            case "List<Integer>":
            case "ArrayList<Integer>":
                return parseListInteger(input);
            case "List<List<Integer>>":
                return parseListDoubleInteger(input);
            case "List<Character>":
            case "ArrayList<Character>":
                return parseListChar(input);
            case "List<List<Character>>":
                return parseListDoubleChar(input);
            default:
                System.out.println("NOT implement" + listType + ",place implement this ,default convert string list");
                return parseListString(input);
        }
    }


    public static char[][][] threeCharArray(String input) {
        // [["a","b","c"],["a","b","c"]]
        System.out.println("char[][][] content not implement");
        return null;
    }


    public static void info(String argType) {
        switch (argType) {
            case "boolean":
                System.out.println("place input a valid boolean content , example true ");
                break;
            case "long":
                System.out.println("place input a valid long number , example 100000000 ");
                break;
            case "double":
                System.out.println("place input a valid double number , example 100.0");
                break;
            case "float":
                System.out.println("place input a valid float number , example 1.1");
                break;
            case "int":
                System.out.println("place input a valid number , example 100,-1 , 0");
                break;
            case "int[]":
            case "ListNode":
                System.out.println("place input this format int[] ,example [1,5,4,2,9,9,9]");
                break;
            case "int[][]":
                System.out.println("place input this format int[][] ,example [[1,2,-1],[4,-1,6],[7,8,9]]");
                break;
            case "char":
                System.out.println("lace input this format char example a ");
                break;
            case "char[]":
                System.out.println("place input this format char[] ,example [\"8\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"]");
                break;
            case "char[][]":
                System.out.println("place input this format char[][] ,example [[\"8\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]]\n");
                break;
            case "List<String>":
            case "string[]":
                System.out.println("place input this format List<String> ,example [\"abbb\",\"ba\",\"aa\"]");
                break;
            case "string[][]":
                System.out.println("place input this format string[][],example [[\"abbb\",\"ba\",\"aa\"],[\"ee\",\"dd\",\"cc\"]");
                break;
            case "TreeNode":
                System.out.println("place input this format TreeNode,example [3,9,20,null,null,15,7]");
                break;
            default:
                System.out.println("unknown support type");
                break;
        }
    }


    public static boolean isIgnore(char c) {
        return c == '\r' || c == '\n' || c == '\t' || c == '\b' || c == '\f' || c == '\0' || c == '\\' || c == ' ' || c == '\'' || c == '\"' || c == '#';
    }


    public static List<Integer> parseListInteger(String input) {
        List<Integer> ls = new ArrayList<>();
        StringBuilder sb = null;
        char[] cs = input.toCharArray();
        // [1,2,3,4,45,5,5,100]
        for (char c : cs) {
            if (c == '[') {
                sb = new StringBuilder();
            } else if (c == ']' || c == ',') {
                if (sb != null) {
                    try {
                        ls.add(Integer.parseInt(sb.toString()));
                    } catch (NumberFormatException e) {
                        // ignore ...
                    }
                }
                sb = new StringBuilder();
            } else {
                if (sb != null) {
                    sb.append(c);
                }
            }
        }
        return ls;
    }

    public static List<List<Integer>> parseListDoubleInteger(String input) {
        StringBuilder sb = null;
        List<List<Integer>> ls = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        char[] cs = input.toCharArray();
        for (int i = 1; i < cs.length; i++) {
            char c = cs[i];
            if (c == '[') {
                sb = new StringBuilder();
                temp = new ArrayList<>();
            } else if (c == ',') {
                if (sb != null) {
                    try {
                        temp.add(Integer.parseInt(sb.toString()));

                    } catch (NumberFormatException e) {
                        // ignore
                    } finally {
                        sb = new StringBuilder();
                    }
                }

            } else if (c == ']') {
                if (sb != null) {
                    temp.add(Integer.parseInt(sb.toString()));
                    ls.add(temp);
                    if (i == cs.length - 2 || cs[i + 1] == ']') break;
                    sb = new StringBuilder();
                    temp = new ArrayList<>();
                }
            } else {
                if (sb != null) {
                    sb.append(c);
                }
            }
        }

        return ls;
    }

    public static List<String> parseListString(String input) {
        List<String> ls = new ArrayList<>();
        StringBuilder sb = null;
        char[] cs = input.toCharArray();
        for (char c : cs) {
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
        return ls;
    }

    public static List<Character> parseListChar(String input) {
        List<Character> ls = new ArrayList<>();
        StringBuilder sb = null;
        char[] charArray = input.toCharArray();
        for (char c : charArray) {
            if (c == '[' || c == ']' || c == ',' || isIgnore(c)) {
                continue;
            }
            ls.add(c);
        }
        return ls;
    }

    public static List<List<Character>> parseListDoubleChar(String input) {
        List<List<Character>> ls = new ArrayList<>();
        char[] charArray = input.toCharArray();
        List<Character> temp = null;
        for (char c : charArray) {
            if (c == ',')
                continue;
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
        return ls;
    }


}
