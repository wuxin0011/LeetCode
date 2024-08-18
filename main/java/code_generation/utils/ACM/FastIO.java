package code_generation.utils.ACM;

import java.io.*;

/**
 * @author: wuxin0011
 * @Description:
 */
public class FastIO {


    public static class Main {

        public static void main(String[] args) {
            int t = 1;
            // t = read()
            while (t > 0) {
                solve();
                t--;
            }
            close();
        }

        public static void solve() {
            // todo ...
        }

        public static final String DEFAULT_FILE_NAME = "in.txt"; // default read file
        public static final boolean DELETE_CLOSE = false; // auto close ?
        public static BufferedInputStream br = null;
        public static PrintWriter ptr = null;

        static {
            try {
                File file = new File(DEFAULT_FILE_NAME);
                br = file.exists() ? new BufferedInputStream(new FileInputStream(file)) : br;
            } catch (Exception e) {

            }
            initIO(false);
        }

        public static void initIO(boolean flushIO) {
            br = flushIO || br == null ? new BufferedInputStream(System.in) : br;
            ptr = flushIO || ptr == null ? new PrintWriter(new BufferedOutputStream(System.out)) : ptr;
        }

        public static int read() {
            long x = readLong();
            if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) {
                throw new RuntimeException("overflow int type");
            }
            return (int) x;
        }

        static long readLong() {
            try {
                int c = br.read();
                int f = 1;
                long x = 0;
                while (c < '0' || c > '9') {
                    if (c == '-') {
                        f = -1;
                    }
                    c = br.read();
                }
                while (c >= '0' && c <= '9') {
                    x = x * 10 + (c - '0');
                    c = br.read();
                }
                return x * f;
            } catch (IOException e) {
                System.err.println("read Error,place check your input is number !");
                return -1;
            }
        }

        public static void pln(Object obj) {
            ptr.println(obj);
        }

        public static void print(Object obj) {
            ptr.print(obj);
        }

        public static void close() {
            try {
                if (br != null)
                    br.close();

                if (ptr != null) {
                    ptr.flush();
                    ptr.close();
                }
                if (DELETE_CLOSE) {
                    br = null;
                    ptr = null;
                }
            } catch (Exception ignore) {

            }
        }

        // --------------------------------------------string-----------------------------------------------------------

        // MAX string SK
        public static int MAX_STRING_LENGTH = (int) 1e5 + 1;

        public static char[] STRING_ARRAY = null;

        public static String readLine() {
            return readString(true);
        }

        public static String readString() {
            return readString(false);
        }

        public static String readString(boolean line) {
            try {
                if (STRING_ARRAY == null) {
                    STRING_ARRAY = new char[MAX_STRING_LENGTH];
                }
                int x = br.read();
                if (x == -1) {
                    System.err.println("No Any Input !");
                    return "";
                }
                while (ignore(x)) {
                    x = br.read();
                }
                int curSisze = 0;
                while (line ? (x == ' ' || !ignore(x)) : (x != ' ' && !ignore(x))) {
                    STRING_ARRAY[curSisze++] = (char) x;
                    x = br.read();
                }
                return new String(STRING_ARRAY, 0, curSisze);
            } catch (Exception e) {
                return "";
            }
        }

        public static boolean ignore(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == '\b' || c == '\f' || c == -1;
        }

        public static boolean readBoolean() {
            return "true".equalsIgnoreCase(readString());
        }

        public static double readDouble() {
            return Double.parseDouble(readString());
        }

        public static float readFloat() {
            return Float.parseFloat(readString());
        }

    }

}
