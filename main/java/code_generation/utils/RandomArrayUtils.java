package code_generation.utils;

import java.util.Random;

/**
 * @author: wuxin0011
 * @Description:
 */
public class RandomArrayUtils {

    public static final int int_10_1 = (int) 1e1;
    public static final int int_10_2 = (int) 1e2;
    public static final int int_10_3 = (int) 1e3;
    public static final int int_10_4 = (int) 1e4;
    public static final int int_10_5 = (int) 1e5;
    public static final int int_10_6 = (int) 1e6;
    public static final int int_10_7 = (int) 1e7;
    public static final int int_10_8 = (int) 1e8;
    public static final int int_10_9 = (int) 1e9;


    public static void main(String[] args) {

    }

    private static final Random random = new Random();

    public static int randomValue(int minValue, int maxValue) {
        if (minValue == maxValue) {
            return minValue;
        }
        if (minValue > maxValue) {
            int t = minValue;
            minValue = maxValue;
            maxValue = t;
        }
        return random.nextInt(maxValue - minValue + 1) + minValue;
    }

    public static int[] randomIntArray(int maxArrayLength, int minValue, int maxValue) {
        return randomIntArray(0, maxArrayLength, minValue, maxValue);
    }

    /**
     * 随机生成数组
     *
     * @param minArrayLength 最小长度 默认值 0
     * @param maxArrayLength 最大长度
     * @param maxValue       最大值
     * @param minValue       最小值
     * @return int[]
     */
    public static int[] randomIntArray(int minArrayLength, int maxArrayLength, int minValue, int maxValue) {
        int N = randomValue(minArrayLength, maxArrayLength);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = randomValue(minValue, maxValue);
        }
        return arr;
    }

    public static void checkBound(int maxArrayLength, int min, int max) {
        checkBound(0, maxArrayLength, min, Integer.MIN_VALUE, max, Integer.MAX_VALUE);
    }

    public static void checkBound(int minArrayLength, int maxArrayLength, int min, int max) {
        checkBound(minArrayLength, maxArrayLength, min, Integer.MIN_VALUE, max, Integer.MAX_VALUE);
    }

    public static void checkBound(int minArrayLength, int maxArrayLength, int min, int minBound, int max, int maxBound) {
        if (minArrayLength < 0) {
            throw new RuntimeException("len must > 0");
        }
        if (max < min || minArrayLength > maxArrayLength) {
            throw new RuntimeException("max  > min");
        }
        if (min < minBound) {
            throw new RuntimeException("minBound  = " + minBound + ",but = " + min);
        }
        if (max > maxBound) {
            throw new RuntimeException("maxBound  = " + minBound + ",but = " + max);
        }
    }

    public static char[] randomCharArray(int maxArrayLength) {
        return randomCharArray(0, maxArrayLength, 'a', 'z');
    }

    public static char[] randomCharArray(int minArrayLength, int maxArrayLength) {
        return randomCharArray(minArrayLength, maxArrayLength, 'a', 'z');
    }

    public static char[] randomCharArray(int maxArrayLength, int minValue, int maxValue) {
        return randomCharArray(0, maxArrayLength, minValue, maxValue);
    }

    public static char[] randomCharArray(int minArrayLength, int maxArrayLength, int minValue, int maxValue) {
        checkBound(minArrayLength, maxArrayLength, minValue, 0, maxValue, 127);
        int N = randomValue(minArrayLength, maxArrayLength);
        char[] chars = new char[N];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (randomValue(minValue, maxValue));
        }
        return chars;
    }


    public static String randomString(int maxArrayLength) {
        return randomString(0, maxArrayLength, 'a', 'z');
    }

    public static String randomString(int maxArrayLength, int minValue, int maxValue) {
        return randomString(0, maxArrayLength, minValue, maxValue);
    }

    public static String randomString(int minArrayLength, int maxArrayLength, int minValue, int maxValue) {
        return new String(randomCharArray(minArrayLength, maxArrayLength, minValue, maxValue));
    }

    public static String[] randomStringArray(int maxArrayLength, int minStringLength, int maxStringLength) {
        return randomStringArray(0, maxArrayLength, minStringLength, maxStringLength, 'a', 'z');
    }


    public static String[] randomStringArray(int minArrayLength, int maxArrayLength, int minStringLength, int maxStringLength, int minValue, int maxValue) {
        int N = randomValue(minArrayLength, maxArrayLength);
        String[] ss = new String[N];
        for (int i = 0; i < ss.length; i++) {
            ss[i] = randomString(minStringLength, maxStringLength, minValue, maxValue);
        }
        return ss;
    }


}
