package code_generation.utils;

/**
 * @author: wuxin0011
 * @Description:
 */
public class CustomColor {
    public static void main(String[] args) throws Exception {
        testColor();
        System.out.println(success("hello world success color"));
    }


    public static void testColor() throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            System.out.println(backgroundColor("java.version=", System.getProperty("java.version")));
        }
        System.out.println("==========================");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            System.out.println(fontColor("java.version=", System.getProperty("java.version")));
        }
        System.out.println("==========================");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            System.out.println(colorTemplate(new MyColor(), new MyColor(), "java.version=", System.getProperty("java.version")));
        }
        Thread.sleep(200);
        System.out.println("==============custom color ============");
        System.out.println(colorTemplate(new MyColor(0, 0, 0), new MyColor(255, 255, 255), "java.version=", System.getProperty("java.version")));
    }

    private static final int RGB_VALUE_MAX = 255;
    private static final int BACKGROUND_COLOR = 48;
    private static final int FONT_COLOR = 38;
    private static final String CLEAR_COLOR = "\u001B[0m";


    public static class MyColor {
        public int R;
        public int G;
        public int L;

        public MyColor(int r, int g, int l) {
            this.R = r;
            this.G = g;
            this.L = l;
        }

        public MyColor() {
            this.R = randomColorValue();
            this.G = randomColorValue();
            this.L = randomColorValue();
        }
    }


    public static int randomColorValue() {
        return (int) Math.floor(Math.random() * RGB_VALUE_MAX);
    }

    public static String fontColor(Object... content) {
        int R = randomColorValue();
        int G = randomColorValue();
        int L = randomColorValue();
        return fontColor(R, G, L, content);
    }

    public static String fontColor(MyColor myColor, Object... content) {
        return fontColor(myColor.R, myColor.G, myColor.L, content);
    }

    public static String fontColor(int R, int G, int L, Object... content) {
        return colorTemplate(FONT_COLOR, R, G, L, content);
    }

    public static String backgroundColor(Object... content) {
        int R = randomColorValue();
        int G = randomColorValue();
        int L = randomColorValue();
        return backgroundColor(R, G, L, content);
    }

    public static String backgroundColor(MyColor myColor, Object... content) {
        return backgroundColor(myColor.R, myColor.G, myColor.L, content);
    }

    public static String backgroundColor(int R, int G, int L, Object... content) {
        return colorTemplate(BACKGROUND_COLOR, R, G, L, content);
    }


    public static String colorTemplate(int type, int R, int G, int L, Object... content) {
        valid(R, G, L);
        StringBuilder s = new StringBuilder();
        for (Object s1 : content) {
            s.append(s1);
        }
        return String.format("\u001B[%d;2;%d;%d;%dm%s\u001B[0m", type, R, G, L, s);
    }

    public static String colorTemplate(int R1, int G1, int L1, int R2, int G2, int L2, String... content) {
        valid(R1, G1, L1, R2, G2, L2);
        StringBuilder s = new StringBuilder();
        for (String s1 : content) {
            s.append(s1);
        }
        return String.format("\u001B[%s;2;%d;%d;%dm\u001B[%s;2;%d;%d;%dm%s\u001B[0m", BACKGROUND_COLOR, R1, G1, L1, FONT_COLOR, R2, G2, L2, s.toString());
    }

    public static String colorTemplate(MyColor bc, MyColor fc, String... content) {
        return colorTemplate(bc.R, bc.G, bc.L, fc.R, fc.G, fc.L, content);
    }


    public static void valid(MyColor... myColors) {
        for (MyColor myColor : myColors) {
            valid(myColor.R, myColor.G, myColor.L);
        }
    }

    public static void valid(int... ints) {
        for (int result : ints) {
            if (result > RGB_VALUE_MAX) {
                throw new RuntimeException("color value max is " + RGB_VALUE_MAX);
            }
        }
    }


    public static String success(Object... content) {
        return fontColor(89, 168, 105, content);
    }

    public static String pink(Object... content) {
        return fontColor(253, 54, 110, content);
    }

    public static String error(Object... content) {
        return fontColor(253, 54, 110, content);
    }


    public static void printSuccess(Object... content) {
        System.out.println(success(content));
    }

}
