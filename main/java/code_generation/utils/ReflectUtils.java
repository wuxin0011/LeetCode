package code_generation.utils;

import code_generation.annotation.Description;
import code_generation.annotation.TestCaseGroup;
import code_generation.bean.ListNode;
import code_generation.bean.TreeNode;
import code_generation.enums.Type;

import java.io.File;
import java.lang.reflect.Constructor;
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
        return getDescriptionInfo(c.getDeclaredAnnotation(Description.class));
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
        return getDescriptionInfo(method.getDeclaredAnnotation(Description.class));
    }

    public static <T> String getDescriptionInfo(Description description) {
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


    public static Object parseArg(Class<?> origin, String methodName, Class<?> src, String input, int idx, int argsSize) {
        return parseArg(origin, methodName, src.getSimpleName(), input, idx, argsSize);
    }

    public static Object parseArg(Class<?> src, String methodName, String type, String input, int idx, int argsSize) {
        if (input == null || "".equals(input) || input.length() == 0) {
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
                case "Boolean[]":
                    return oneBooleanArray(input);
                case "boolean[][]":
                case "Boolean[][]":
                    return doubleBooleanArray(input);
                case "double":
                    return parseDouble(input);
                case "double[]":
                    return oneDoubleArray(input);
                case "double[][]":
                    return doubleDoubleArray(input);
                case "long[]":
                case "Long[]":
                    return oneLongArray(input);
                case "long[][]":
                case "Long[][]":
                    return doubleLongArray(input);
                case "float":
                    return Float.parseFloat(input);
                case "int[]":
                case "Integer[]":
                    return oneIntArray(input);
                case "int[][]":
                case "Integer[][]":
                    return doubleIntArray(input);
                case "int[][][]":
                case "Integer[][][]":
                    return threeIntArray(input);
                case "char":
                case "Character":
                    return input.toCharArray()[0];
                case "char[]":
                case "Character[]":
                    return oneCharArray(input);
                case "char[][]":
                case "Character[][]":
                    return doubleCharArray(input);
                case "char[][][]":
                case "Character[][][]":
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
                case "TreeNode[]":
                    return createListTreeNodeArray(input);
                case "ListNode":
                    return ListNode.createListNode(oneIntArray(input));
                case "ListNode[]":
                    return createListNodeArray(input);
                case "List":
                case "ArrayList":
                    return toList(src, methodName, type, input, idx, argsSize);
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

    private static TreeNode[] createListTreeNodeArray(String input) {
        List<List<String>> lists = parseDoubleString(input);
        TreeNode[] nodes = new TreeNode[lists.size()];
        for (int i = 0; i < lists.size(); i++) {
            try {
                int size = lists.get(i).size();
                String[] ss = new String[size];
                for (int k = 0; k < size; k++) {
                    ss[k] = lists.get(i).get(i);
                }
                nodes[i] = TreeNode.widthBuildTreeNode(ss);
            } catch (Exception e) {
                nodes[i] = null;
            }
        }
        return nodes;
    }

    //  // https://leetcode.cn/problems/merge-k-sorted-lists/
    public static ListNode[] createListNodeArray(String input) {
        List<List<Integer>> ls = parseListDoubleInteger(input);
        int n = ls.size();
        ListNode[] nodes = new ListNode[n];
        for (int i = 0; i < n; i++) {
            try {
                if (ls.get(i) == null) continue;
                int[] array = ls.get(i).stream().mapToInt(Integer::intValue).toArray();
                nodes[i] = ListNode.createListNode(array);
            } catch (Exception e) {
                nodes[i] = null;
            }
        }
        return nodes;
    }

    private static long[][] doubleLongArray(String input) {
        List<List<Long>> longList = parseDoubleLongList(input);
        long[][] longs = new long[longList.size()][];
        for (int i = 0; i < longList.size(); i++) {
            longs[i] = new long[longList.get(i).size()];
            for (int j = 0; j < longList.get(i).size(); j++) {
                longs[i][j] = longList.get(i).get(j);
            }
        }
        return longs;
    }

    private static List<List<Long>> parseDoubleLongList(String input) {
        List<List<String>> list = parseDoubleString(input);
        List<List<Long>> longs = new ArrayList<>();
        for (List<String> one : list) {
            List<Long> temp = new ArrayList<>();
            for (String s : one) {
                temp.add(Long.parseLong(s));
            }
            longs.add(temp);
        }
        return longs;
    }

    private static double[][] doubleDoubleArray(String input) {
        List<List<Double>> doubleDoubleList = parseDoubleDoubleList(input);
        double[][] doubles = new double[doubleDoubleList.size()][];
        for(int i = 0;i<doubleDoubleList.size();i++) {
            doubles[i] = new double[doubleDoubleList.get(i).size()];
            for (int j = 0; j < doubleDoubleList.get(i).size(); j++) {
                doubles[i][j] = doubleDoubleList.get(i).get(j);
            }
        }
        return doubles;
    }


    private static Object oneLongArray(String input) {
        List<Long> ls = parseListLong(input);
        long[] res = new long[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            res[i] = ls.get(i);
        }
        return res;
    }

    private static List<Long> parseListLong(String input) {
        List<String> strings = parseListString(input);
        List<Long> res = new ArrayList<>();
        for (String s : strings) {
            try {
                res.add(Long.parseLong(s));
            } catch (Exception e) {
            }
        }
        return res;
    }


    public static Object toList(Class<?> t, String methodName, String type, String input, int idx, int argsSize) {
        String listType = IoUtil.findListReturnTypeMethod(t, methodName, type, idx, argsSize);
        String originType = listType;
        if (listType.contains("ArrayList")) {
            listType = listType.replace("ArrayList", "List");
        }
        switch (listType) {
            case "List<TreeNode>":
                return parseListTreeNode(input); // 4.2日 新增
            case "List<String>":
                return parseListString(input);
            case "List<List<String>>":
                return parseDoubleString(input);
            case "List<List<List<String>>>":
                return parseThreeString(input);
            case "List<Integer>":
                return parseListInteger(input);
            case "List<Long>":
                return parseListLong(input);
            case "List<Boolean>":
                return parseListBoolean(input);
            case "List<List<Boolean>>":
                return parseDoubleBoolean(input);
            case "List<Double>":
                return parseDoubleList(input);
            case "List<List<Double>>":
                return parseDoubleDoubleList(input);
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
                System.err.println("NOT implement " + originType + ",place implement this ,default convert string list");
                return parseListString(input);
        }
    }

    private static List<TreeNode> parseListTreeNode(String input) {
        List<TreeNode> treeNodes = new ArrayList<>();
        String[][] strings = doubleStringArray(input);
        for (int i = 0; i < strings.length; i++) {
            TreeNode treeNode = TreeNode.widthBuildTreeNode(strings[i]);
            treeNodes.add(treeNode);
        }
        return treeNodes;
    }


    public static String toString(String input) {
        if (input == null || input.length() == 0) {
            // throw new NullPointerException("input content is null");
            return "";
        }
        char[] charArray = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        char st = input.charAt(0);
        for (char c : charArray) {
            if (StringUtils.isIgnore(c, st)) continue;
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

    public static boolean[][] doubleBooleanArray(String input) {
        List<List<Boolean>> ls = parseDoubleBoolean(input);
        boolean[][] ans = new boolean[ls.size()][];
        for (int i = 0; i < ls.size(); i++) {
            ans[i] = new boolean[ls.get(i).size()];
            for (int j = 0; j < ls.get(i).size(); j++) {
                ans[i][j] = ls.get(i).get(j);
            }
        }
        return ans;
    }

    private static List<List<Boolean>> parseDoubleBoolean(String input) {
        List<List<String>> doubles = parseDoubleString(input);
        List<List<Boolean>> ans = new ArrayList<>();
        int m = doubles.size(), n = doubles.get(0).size();
        for (int i = 0; i < m; i++) {
            ArrayList<Boolean> temp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                String s = doubles.get(i).get(j);
                if (s.contains("t")) {
                    temp.add(true);
                } else {
                    temp.add(false);
                }
            }
            ans.add(temp);
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
                doubles.add(parseDouble(s));
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return doubles;
    }

    private static List<List<Double>> parseDoubleDoubleList(String input) {
        List<List<String>> list = parseDoubleString(input);
        List<List<Double>> doubles = new ArrayList<>();
        for (List<String> one : list) {
            List<Double> temp = new ArrayList<>();
            for (String s : one) {
                temp.add(parseDouble(s));
            }
            doubles.add(temp);
        }
        return doubles;
    }
    public static int[] oneIntArray(String input) {
        if ("[]".equals(input) || "{}".equals(input)) {
            return new int[]{};
        }
        List<Integer> ls = parseListInteger(input);
        if (ls.size() == 0) {
            return new int[]{};
        }
        int[] ans = new int[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            ans[i] = ls.get(i);
        }
        return ans;
    }

    public static int[][] doubleIntArray(String input) {
        if ("[]".equals(input) || "[[]]".equals(input) || "{}".equals(input) || "{{}}".equals(input)) {
            return new int[][]{};
        }
        List<List<Integer>> ls = parseListDoubleInteger(input);
        if (ls == null || ls.size() == 0) {
            return new int[][]{};
        }
        int row = ls.size();
        int[][] ans = new int[row][];
        for (int i = 0; i < row; i++) {
            ans[i] = new int[ls.get(i).size()];
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
        int row = ls.size();
        char[][] cs = new char[row][];
        for (int i = 0; i < row; i++) {
            List<Character> cc = ls.get(i);
            cs[i] = new char[cc.size()];
            for (int j = 0; j < cc.size(); j++) {
                cs[i][j] = cc.get(j);
            }
        }
        return cs;
    }


    public static char[][][] threeCharArray(String input) {
        List<List<List<Character>>> lists = parseThreeCharArray(input);
        int row = lists.size();
        char[][][] ans = new char[row][][];
        for (int i = 0; i < row; i++) {
            ans[i] = new char[lists.get(i).size()][];
            for (int j = 0; j < lists.get(i).size(); j++) {
                ans[i][j] = new char[lists.get(i).get(j).size()];
                for (int z = 0; z < lists.get(i).get(j).size(); z++) {
                    ans[i][j][z] = lists.get(i).get(j).get(z);
                }
            }
        }
        return ans;
    }


    public static List<Integer> parseListInteger(String input) {
        List<String> strings = parseListString(input);
        ArrayList<Integer> ans = new ArrayList<>();
        if ("[]".equals(input) || "{}".equals(input)) {
            return ans;
        }
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
        if ("[]".equals(input) || "[[]]".equals(input) || "{}".equals(input) || "{{}}".equals(input)) {
            return new ArrayList<>();
        }
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
        int row = lists.size();
        String[][] ans = new String[row][];
        for (int i = 0; i < row; i++) {
            ans[i] = new String[lists.get(i).size()];
            for (int j = 0; j < lists.get(i).size(); j++) {
                ans[i][j] = lists.get(i).get(j);
            }
        }
        return ans;
    }

    public static String[][][] threeStringArray(String input) {
        List<List<List<String>>> lists = parseThreeString(input);
        int row = lists.size();
        String[][][] ans = new String[row][][];
        for (int i = 0; i < row; i++) {
            ans[i] = new String[lists.get(i).size()][];
            for (int j = 0; j < lists.get(i).size(); j++) {
                ans[i][j] = new String[lists.get(i).get(j).size()];
                for (int z = 0; z < lists.get(i).get(j).size(); z++) {
                    ans[i][j][z] = lists.get(i).get(j).get(z);
                }
            }
        }
        return ans;
    }


    public static List<String> parseListString(String input) {
        List<String> ls = new ArrayList<>();
        char[] flag = getFlag(input);
        char startFlag = flag[0];
        char endFlag = flag[1];
        char interruptFlag = flag[2];
        if (!input.contains(String.valueOf(startFlag)) && !input.contains(String.valueOf(endFlag))) {
            ls.add(input);
            return ls;
        }
        String nullStr = new String(new char[]{startFlag, endFlag});
        if (nullStr.equals(input)) return ls;
        StringBuilder sb = null;
        char[] cs = input.toCharArray();
        for (char c : cs) {
            if (c == startFlag) {
                sb = new StringBuilder();
                continue;
            }
            if (sb == null) break;
            if (c == endFlag) {
                ls.add(sb.toString());
                break;
            } else if (c == interruptFlag) {
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
        char[] flag = getFlag(input);
        char startFlag = flag[0];
        char endFlag = flag[1];
        char interruptFlag = flag[2];
        List<List<String>> ls = new ArrayList<>();
        List<String> temp = null;
        Stack<Character> sk = new Stack<>();
        char[] cs = input.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c == startFlag) {
                sk.push(c);
                if (sk.size() == 2) temp = new ArrayList<>();
            } else if (c == endFlag) {
                if (!sk.isEmpty()) {
                    sk.pop();
                }
                if (sk != null && !sk.isEmpty() && temp != null) {
                    if (sb != null) {
                        temp.add(sb.toString());
                    }
                    ls.add(temp);
                }
                sb = null;
                temp = null;
            } else if (c == interruptFlag) {
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
        char[] flag = getFlag(input);
        char startFlag = flag[0];
        char endFlag = flag[1];
        char interruptFlag = flag[2];
        char stChar = input.charAt(0);
        for (char c : charArray) {
            if (StringUtils.isIgnore(c, stChar)) continue;
            if (c == startFlag) {
                sk.push(c);
                if (sk.size() == 2) {
                    d = new ArrayList<>();
                } else if (sk.size() == 3) {
                    t = new ArrayList<>();
                }
            } else if (c == endFlag) {
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
            } else if (c == interruptFlag) {
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


    public static char[] getFlag(String input) {
        char st = '\0';
        for (int i = 0; i < input.length(); i++) {
            if ((st = input.charAt(i)) != ' ' && st != '\0') {
                break;
            }
        }
        char[] flag = new char[4];
        flag[3] = 'Y';
        if (st == '{') {
            flag[0] = '{';
            flag[1] = '}';
            flag[2] = ',';
        } else if (st == '[') {
            flag[0] = '[';
            flag[1] = ']';
            flag[2] = ',';
        } else {
            flag[3] = 'N';
            //  throw new RuntimeException("NO this parse format, place implement ,start flag is " + st);
        }
        return flag;
    }


    public static String getPackageInfo(String classFile) {
        if (classFile == null) {
            throw new NullPointerException();
        }
        classFile = classFile.replace("\\\\\\", "\\").replace("\\\\", "\\");
        File file = new File(classFile);
        String dir = "";
        if (file.exists()) {
            if (file.isFile()) {
                int i = classFile.lastIndexOf(File.separator);
                if (i != -1) {
                    dir = classFile.substring(0, i);
                }
            } else { // is dir
                dir = classFile;
            }

        } else {
            if (classFile.endsWith(".java")) {
                int i = classFile.lastIndexOf(File.separator);
                if (i != -1) {
                    dir = classFile.substring(0, i);
                }
            } else {
                dir = classFile;
            }
        }
        if (StringUtils.isEmpty(dir) || !dir.contains(IoUtil.getWorkDir())) {
            throw new RuntimeException("place check project running on " + IoUtil.getWorkDir());
        }
        int id = dir.indexOf(IoUtil.getProjectRootDir());
        if (id == -1) {
            throw new RuntimeException("place check " + dir + " is local work dir ");
        }
        String packageDir = dir.substring(id + IoUtil.getProjectRootDir().length());
        char[] charArray = packageDir.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c != '\\' && c != '/') {
                continue;
            }
            if (i == charArray.length - 1) {
                charArray[i] = ' ';
                break;
            } else {
                c = '.';
            }
            charArray[i] = c;
        }
        return new String(charArray);
    }


    // constructor class parse
    public static String[] parseConstrunctorClassString(String s) {
        int st = 0, ed = s.length() - 1;
        char start = 0;
        char end = 0;
        char inter = 0;
        char[] flag = ReflectUtils.getFlag(s);
        if (flag[3] == 'Y') {
            start = flag[0];
            end = flag[1];
            inter = flag[2];
            while (st < s.length() && s.charAt(st) != start) {
                st++;
            }
            while (ed >= 0 && s.charAt(ed) != end) {
                ed--;
            }
        } else {
            start = '[';
            end = ']';
            inter = ',';
            ed = s.length();
            st = -1;
        }
        int deep = 0;
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = st + 1; i < ed; i++) {
            char c = s.charAt(i);
            if (c == start) {
                deep++;
                if (sb == null) sb = new StringBuilder();
                sb.append(c);
            } else if (c == end) {
                deep--;
                if (sb != null) {
                    sb.append(c);
                }
                if (deep == 0) {
                    ans.add(sb.toString());
                    sb = null;
                }
            } else if (c == inter) {
                if (sb != null) {
                    if (deep == 0) {
                        ans.add(sb.toString());
                        sb = null;
                    } else {
                        sb.append(c);
                    }
                } else {
                    sb = new StringBuilder();
                }
            } else {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(c);

            }


        }
        if (sb != null) {
            ans.add(sb.toString());
        }

        String[] strings = new String[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            strings[i] = StringUtils.ingoreString(ans.get(i));
        }

        return strings;
    }



    public static void handlerConstructorMethodInput(String arg, List<String> result, Method method) {
        String[] ss = parseConstrunctorClassString(arg);
        for (String s : ss) {
            result.add(s);
        }
    }

    public static void handlerConstructorMethodInput(String arg, List<String> result) {
        handlerConstructorMethodInput(arg, result, null);
    }



    public static void handlerConstructorMethodOutput(String exp, List<String> result, Method method) {
        Class<?> returnType = method.getReturnType();
        String returnName = returnType.getSimpleName();
        if (returnName.contains("[]")
                || returnName.contains("List")
                || returnName.contains("ArrayList")
                || returnName.contains("TreeNode")
                || returnName.contains("LinkedList")
                || returnName.contains("ListNode")
                || returnName.contains("Queue")
        ) {
            result.add(exp);
        } else {
            String s = parseConstrunctorClassString(exp)[0];
            result.add(s);
        }
    }

    public static <T> Class<?> loadOrigin(Class<T> src) {
        if (src == null) {
            return null;
        }
        String name = src.getName();
        if (name.contains("$")) {
            String[] ss = name.split("\\$");
            try {
                return Class.forName(ss[0]);
            } catch (ClassNotFoundException e) {
                return src;
            }
        } else {
            return src;
        }
    }

    public static double parseDouble(String d) {
        return parseDouble(Double.parseDouble(d));
    }

    // https://leetcode.cn/problems/minimum-cost-to-hire-k-workers
    // 结果保留五位小数
    public static double parseDouble(double d) {
        return Double.parseDouble(String.format("%.5f", d));
    }

    public static Object initObjcect(Class<?> src,Object[] args) {
        Object obj = null;

        try {
            obj = src.newInstance();
        } catch (IllegalAccessException e) {
            try {
                Constructor<?> constructor = src.getDeclaredConstructor(null);
                if (constructor != null) {
                    constructor.setAccessible(true);
                    obj = constructor.newInstance(args);
                }
            } catch (Exception ex) {

            }
        } catch (Exception e) {

        }

        return obj;
    }


    public static boolean isBaseType(Class<?> c) {
        if (c == null) {
            return true;
        }
        String name = c.getSimpleName();

        // char byte short int long double float
        if ("char".equals(name) || "Character".equals(name)) {
            return true;
        }
        if ("byte".equalsIgnoreCase(name)) {
            return true;
        }
        if ("Integer".equals(name) || "int".equals(name)) {
            return true;
        }
        if ("boolean".equalsIgnoreCase(name)) {
            return true;
        }
        if ("long".equalsIgnoreCase(name)) {
            return true;
        }
        if ("double".equalsIgnoreCase(name)) {
            return true;
        }
        if ("float".equalsIgnoreCase(name)) {
            return true;
        }
        return false;
    }

    public static int handlerVoidReturnType(Class<?>[] parameterTypes) {
        if (parameterTypes == null) {
            return -1;
        }
        for (int i = 0; i < parameterTypes.length; i++) {
            if (isBaseType(parameterTypes[i])) {
                continue;
            }
            return i;
        }
        return -1;
    }




    public static int[] getTestCaseInfo(Class<?> aClass) {
        return getTestCaseInfo(aClass.getDeclaredAnnotation(TestCaseGroup.class));
    }

    public static int[] getTestCaseInfo(Method method) {
        return getTestCaseInfo(method.getDeclaredAnnotation(TestCaseGroup.class));
    }

    /**
     * 获取需要测试的测试用力
     *
     * @param annotation TestCaseGroup 注解
     * @return
     * @see code_generation.annotation.TestCaseGroup
     */
    public static int[] getTestCaseInfo(TestCaseGroup annotation) {
        if (annotation == null || !annotation.use()) {
            return new int[]{1, Integer.MAX_VALUE};
        }
        int start = Math.max(1, Math.min(annotation.start(), annotation.end()));
        int end = Math.max(1, Math.max(annotation.start(), annotation.end()));
        return new int[]{start, end};
    }

}
