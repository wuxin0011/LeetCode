package leetcode.utils;

import leetcode.annotation.Description;
import leetcode.utils.Bean.Type;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * @author: wuxin0011
 * @Description:
 */
@SuppressWarnings("all")
public class ReflectUtils {


    private static final String EMPTY_STR = "";

    public static void main(String[] args) {
        IoUtil.testUtil(ReflectUtils.class, "t1", "test.txt");
    }


    public static int t1(int[][][] arr) {
        // System.out.println("result=>" + Arrays.deepToString(arr));
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int t = 0;
                // System.out.println("arr[][] = >" + Arrays.toString(arr[i][j]));
                for (int z = 0; z < arr[i][j].length; z++) {
                    t += arr[i][j][z];
                }
                max = Math.max(max, t);
            }
        }
        return max;
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


    public static <T> Object parseArg(Object src, String methodName, Class<T> c, String input, int idx,int argsSize) {
        return parseArg(src, methodName, c.getSimpleName(), input, idx, argsSize);
    }

    public static Object parseArg(Object src, String methodName, String type, String input, int idx, int argsSize) {
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
                case "boolean[]":
                    return oneBooleanArray(input);
                case "double":
                    return Double.parseDouble(input);
                case "double[]":
                    return oneDoubleArray(input);
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
                    return toList(src.getClass(), methodName, type, input, idx, argsSize);
                default:
                    System.out.println(type + " not implement ,place implement!");
                    return null;
            }
        } catch (NumberFormatException e) {
            // e.printStackTrace();
            errorInfo(type);
            return null;
        }

    }


    public static <T> Object toList(Class<T> t, String methodName, String type, String input, int idx, int argsSize) {
        String listType = IoUtil.findListReturnTypeMethod(t, methodName, type,idx,argsSize);
        switch (listType) {
            case "List<String>":
                return parseListString(input);
            case "List<List<String>>":
                return parseDoubleString(input);
            case "List<List<List<String>>>":
                return parseThreeString(input);
            case "List<Integer>":
                return parseListInteger(input);
            case "List<Boolean>":
                return parseListBoolean(input);
            case "List<Double>":
                return parseDoubleList(input);
            case "List<List<Integer>>":
                return parseListDoubleInteger(input);
            case "List<List<List<Integer>>>":
                return parseListThreeInteger(input);
            case "List<Character>":
                return parseListChar(input);
            case "List<List<Character>>":
                return parseListDoubleChar(input);
            case "List<List<List<Character>>>":
                return parseThreeCharArray(input);
            default:
                System.err.println("NOT implement " + listType + ",place implement this ,default convert string list");
                return parseListString(input);
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


    public static boolean[] oneBooleanArray(String input) {
        List<Boolean> ls = parseListBoolean(input);
        boolean[] ans = new boolean[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            ans[i] = ls.get(i);
        }
        return ans;
    }

    private static List<Boolean> parseListBoolean(String input) {
        List<String> strings = parseListString(input);
        List<Boolean> ans = new ArrayList<>();
        for (String s : strings) {
            if (s.contains("t")) ans.add(true);
            else ans.add(false);
        }
        return ans;
    }


    public static double[] oneDoubleArray(String input) {
        List<Double> ls = parseDoubleList(input);
        double[] ans = new double[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            ans[i] = ls.get(i);
        }
        return ans;
    }


    public static List<Double> parseDoubleList(String input) {
        List<String> list = parseListString(input);
        ArrayList<Double> doubles = new ArrayList<>();
        for (String s : list) {
            try {
                doubles.add(Double.parseDouble(s));
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return doubles;
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
            for (int j = 0; j < ls.get(i).size(); j++) {
                ans[i][j] = ls.get(i).get(j);
            }
        }
        return ans;
    }

    public static int[][][] threeIntArray(String input) {
        List<List<List<Integer>>> ls = parseListThreeInteger(input);
        int[][][] result = new int[ls.size()][][];
        for (int i = 0; i < ls.size(); i++) {
            List<List<Integer>> d = ls.get(i);
            result[i] = new int[d.size()][];
            for (int j = 0; j < d.size(); j++) {
                List<Integer> t = d.get(j);
                result[i][j] = new int[t.size()];
                for (int k = 0; k < t.size(); k++) {
                    result[i][j][k] = t.get(k);
                }
            }
        }
        return result;
    }


    public static char[] oneCharArray(String input) {
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


    public static char[][][] threeCharArray(String input) {
        List<List<List<Character>>> lists = parseThreeCharArray(input);
        int row = lists.size(), col = lists.get(0).size(), vol = lists.get(0).get(0).size();
        char[][][] ans = new char[row][col][vol];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                for (int z = 0; z < lists.get(i).get(j).size(); z++) {
                    ans[i][j][z] = lists.get(i).get(j).get(z);
                }
            }
        }
        return ans;
    }


    public static boolean isIgnore(char c) {
        return c == '\r' || c == '\n' || c == '\t' || c == '\b' || c == '\f' || c == '\0' || c == '\\' || c == ' ' || c == '\'' || c == '\"' || c == '#';
    }


    public static List<Integer> parseListInteger(String input) {
        List<String> strings = parseListString(input);
        ArrayList<Integer> ans = new ArrayList<>();
        for (String s : strings) {
            try {
                ans.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return ans;
    }

    public static List<List<Integer>> parseListDoubleInteger(String input) {
        List<List<Integer>> ls = new ArrayList<>();
        List<List<String>> lists = parseDoubleString(input);
        for (List<String> row : lists) {
            if (row == null) {
                continue;
            }
            List<Integer> temp = new ArrayList<>();
            for (String s : row) {
                if (s == null) continue;
                try {
                    temp.add(Integer.parseInt(s));
                } catch (NumberFormatException e) {
                    // ignore
                }
            }
            ls.add(temp);
        }
        return ls;
    }

    public static List<List<List<Integer>>> parseListThreeInteger(String input) {
        List<List<List<Integer>>> ans = new ArrayList<>();
        List<List<List<String>>> lists = parseThreeString(input);
        for (List<List<String>> list : lists) {
            List<List<Integer>> d = new ArrayList<>();
            if (list == null) continue;
            for (List<String> strings : list) {
                if (strings == null) continue;
                List<Integer> t = new ArrayList<>();
                for (String string : strings) {
                    if (string == null) continue;
                    try {
                        t.add(Integer.parseInt(string));
                    } catch (NumberFormatException e) {
                        // ignore
                    }
                }
                d.add(t);
            }
            ans.add(d);
        }
        return ans;
    }


    public static List<Character> parseListChar(String input) {
        List<Character> ls = new ArrayList<>();
        List<String> strings = parseListString(input);
        for (String s : strings) {
            ls.add(s.charAt(0));
        }
        return ls;
    }

    public static List<List<Character>> parseListDoubleChar(String input) {
        List<List<String>> lists = parseDoubleString(input);
        List<List<Character>> ls = new ArrayList<>();
        List<Character> temp = null;
        for (List<String> row : lists) {
            temp = new ArrayList<>();
            for (String s : row) {
                temp.add(s.charAt(0));
            }
            ls.add(temp);
        }
        return ls;
    }

    public static List<List<List<Character>>> parseThreeCharArray(String input) {
        List<List<List<Character>>> ans = new ArrayList<>();
        List<List<List<String>>> lists = parseThreeString(input);
        for (List<List<String>> list : lists) {
            List<List<Character>> d = new ArrayList<>();
            if (list == null) continue;
            for (List<String> strings : list) {
                if (strings == null) continue;
                List<Character> t = new ArrayList<>();
                for (String string : strings) {
                    if (string == null) continue;
                    t.add(string.charAt(0));
                }
                d.add(t);
            }
            ans.add(d);
        }
        return ans;
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
        List<List<String>> lists = parseDoubleString(input);
        int row = lists.size(), col = lists.get(0).size();
        String[][] ans = new String[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                ans[i][j] = lists.get(i).get(j);
            }
        }
        return ans;
    }

    public static String[][][] threeStringArray(String input) {
        List<List<List<String>>> lists = parseThreeString(input);
        int row = lists.size(), col = lists.get(0).size(), vol = lists.get(0).get(0).size();
        String[][][] ans = new String[row][col][vol];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                for (int z = 0; z < lists.get(i).get(j).size(); z++) {
                    ans[i][j][z] = lists.get(i).get(j).get(z);
                }
            }
        }
        return ans;
    }


    public static List<String> parseListString(String input) {
        List<String> ls = new ArrayList<>();
        if ("[]".equals(input)) return ls;
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


    public static List<List<String>> parseDoubleString(String input) {
        StringBuilder sb = null;
        List<List<String>> ls = new ArrayList<>();
        List<String> temp = null;
        Stack<Character> sk = new Stack<>();
        char[] cs = input.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c == '[') {
                sk.push(c);
                if (sk.size() == 2) temp = new ArrayList<>();
            } else if (c == ']') {
                if (!sk.isEmpty()) {
                    sk.pop();
                }
                if (sb != null && temp != null) {
                    temp.add(sb.toString());
                    ls.add(temp);
                }
                sb = null;
                temp = null;
            } else if (c == ',') {
                if (temp != null && sb != null) {
                    temp.add(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(c);
            }
        }

        return ls;
    }


    public static List<List<List<String>>> parseThreeString(String input) {
        List<List<List<String>>> ans = new ArrayList<>();
        char[] charArray = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean f1 = false, f2 = false, f3 = false;
        List<List<String>> d = new ArrayList<>();
        List<String> t = new ArrayList<>();
        Stack<Character> sk = new Stack<>();
        for (char c : charArray) {
            if (isIgnore(c)) continue;
            if (c == '[') {
                sk.push(c);
                if (sk.size() == 2) {
                    d = new ArrayList<>();
                } else if (sk.size() == 3) {
                    t = new ArrayList<>();
                }
            } else if (c == ']') {
                if (!sk.isEmpty()) {
                    sk.pop();
                }
                if (sk.size() == 1) {
                    ans.add(d);
                } else if (sk.size() == 2) {
                    if (sb != null) {
                        t.add(sb.toString());
                        sb = null;
                    }
                    d.add(t);
                }
            } else if (c == ',') {
                if (sb != null) {
                    t.add(sb.toString());
                }
                sb = null;
            } else {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(c);
            }
        }
        return ans;
    }


    public static void errorInfo(String argType) {
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
            case "List":
            case "string[]":
                System.out.println("place input this format List<String> ,example [\"abbb\",\"ba\",\"aa\"] if content is List<Integer> [1,5,4,2,9,9,9]");
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

}
