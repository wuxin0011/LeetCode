package template.io.template;

import java.io.*;



public class IOTemplate {


    static int MAXN = (int) 1e6 + 1;
    static int MOD7 = (int) 1e9 + 7,MOD8 =  80112002,MOD9 = 998244353,inf = Integer.MAX_VALUE;
    static int MOD  =  (int) MOD9;


    public static class MainClass {


        void solve(){

        }


    }


    static IO io;

    public static void main(String[] args) {
        io = new IO();
        int t = 1;
        // t = io.read();
        for(; t > 0; t--) {
            new MainClass().solve();
        }
        io.close();
    }


    public static class IO {

        private static final int MAX_N = (int) 1e6 + 1;

        private static final boolean USE_READFILE = true;
        private static final String DEFAULT_ROOT_DIR = "src";
        private static final String DEFAULT_FILE_NAME = "B.txt"; // default read file
        private static final boolean DELETE_CLOSE = false; // auto close ?
        private BufferedInputStream br = null;
        private PrintWriter ptr = null;
        private boolean splitDOT = true; // if string contains ',' ,need split ?,default true


        {
            try {
                File file = new File(USE_READFILE ? getAbsPath() : DEFAULT_FILE_NAME);

                br = file.exists() ? new BufferedInputStream(new FileInputStream(file)) : br;
            } catch (Exception ignored) {

            }
            initIO(false);
        }

        private static String getAbsPath() {
            String packagePath = "";
            Package pkg = IO.class.getPackage();
            if (pkg != null && !"null".equals(String.valueOf(pkg))) {
                packagePath = String.valueOf(IO.class.getPackage()).replace("package ", "").replace(".",
                        File.separator);
                packagePath += File.separator;
            }

            return System.getProperty("user.dir") + File.separator + DEFAULT_ROOT_DIR + File.separator
                    + packagePath + DEFAULT_FILE_NAME;
        }

        public void initIO(boolean flushIO) {
            br = flushIO || br == null ? new BufferedInputStream(System.in) : br;
            ptr = flushIO || ptr == null ? new PrintWriter(new BufferedOutputStream(System.out)) : ptr;
        }

        public int read() {
            long x = readLong();
            if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) {
                throw new RuntimeException("overflow int type");
            }
            return (int) x;
        }

        public long readLong() {
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

        boolean isDebug = true;

        public void dbg(Object... args) {
            if (!isDebug) {
                return;
            }
            String argsStringIndex = "";
            // argsStringIndex += "{ ";
            // for(int i = 0;i < args.length;i++) {
            // argsStringIndex += i;
            // if(i != args.length-1) {
            // argsStringIndex +=",";
            // }
            // }
            // argsStringIndex += " }";
            String str = "";
            str += "{ ";
            for (int i = 0; i < args.length; i++) {
                str += String.valueOf(args[i]);
                if (i < args.length - 1) {
                    str += " ,";
                }
            }
            str += " }";
            println(argsStringIndex + (argsStringIndex.length() != 0 ? " = " : "") + str);
        }

        public int[] readIntArray() {
            long[] temp = readLongArray();
            if (temp == null) {
                return null;
            }
            int[] ints = new int[temp.length];
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] < Integer.MIN_VALUE || temp[i] > Integer.MAX_VALUE) {
                    throw new RuntimeException("overflow int type");
                }
                ints[i] = (int) temp[i];
            }
            return ints;
        }

        public long[] LONG_ARRAY = null;

        public long[] readLongArray() {
            if (LONG_ARRAY == null) {
                LONG_ARRAY = new long[MAX_N];
            }
            int size = 0;
            while (true) {
                try {
                    int f = 1;
                    long x = 0;
                    int c = br.read();
                    if (c == -1) {
                        break;
                    }
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
                    LONG_ARRAY[size++] = x * f;
                    if (c == '\n' || c == -1) {
                        break;
                    }
                } catch (Exception E) {
                    return null;
                }
            }
            if (size == 0) {
                return null;
            }
            long[] temp = new long[size];
            System.arraycopy(LONG_ARRAY, 0, temp, 0, size);
            return temp;
        }

        public String[] STRING_ARRAY = null;

        public String[] readStringArray() {
            if (STRING_ARRAY == null) {
                STRING_ARRAY = new String[MAX_N];
            }
            int size = 0;
            while (true) {
                try {
                    if (CHAR_ARRAY == null) {
                        CHAR_ARRAY = new char[MAX_N];
                    }
                    int x = br.read();
                    if (x == -1) {
                        return null;
                    }
                    while (ignore(x)) {
                        x = br.read();
                    }
                    if (x == -1) {
                        return null;
                    }
                    int currentSize = 0;
                    while (x != ' ' && x != ',' && !ignore(x)) {
                        CHAR_ARRAY[currentSize++] = (char) x;
                        x = br.read();
                    }
                    STRING_ARRAY[size++] = new String(CHAR_ARRAY, 0, currentSize);
                    if (x == '\n' || x == -1) {
                        break;
                    }
                } catch (Exception E) {
                    return null;
                }
            }
            if (size == 0) {
                return null;
            }
            String[] temp = new String[size];
            System.arraycopy(STRING_ARRAY, 0, temp, 0, size);
            return temp;
        }

        public void println(Object... objs) {
            for (int i = 0; i < objs.length; i++) {
                ptr.print(objs[i]);
            }
            ptr.print("\n");
        }

        public void print(Object... objs) {
            for (int i = 0; i < objs.length; i++) {
                ptr.print(objs[i]);
            }
        }

        public void printf(String format, Object... obj) {
            ptr.printf(format, obj);
        }

        public void close() {
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


        public char[] CHAR_ARRAY = null;

        public char[] readCharArray(boolean line) {
            try {
                if (CHAR_ARRAY == null) {
                    CHAR_ARRAY = new char[MAX_N];
                }
                int x = br.read();
                if (x == -1) {
                    return null;
                }
                while (ignore(x)) {
                    x = br.read();
                }
                int currentSize = 0;
                while (line ? (x == ' ' || (x == ',') || !ignore(x)) : (x != ' ' && !ignore(x))) {
                    CHAR_ARRAY[currentSize++] = (char) x;
                    x = br.read();
                }
                if (currentSize == 0 && x == -1) {
                    return null;
                }
                char[] temp = new char[currentSize];
                System.arraycopy(CHAR_ARRAY, 0, temp, 0, currentSize);
                return temp;
            } catch (Exception e) {
                return null;
            }
        }

        public String readLine() {
            return readString(true);
        }

        public String readString() {
            return readString(false);
        }

        public String readString(boolean line) {
            char[] temp = readCharArray(line);
            return temp == null ? null : new String(temp);
        }

        public boolean ignore(int c) {
            if (c == ',') {
                return splitDOT;
            }
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == '\b' || c == '\f' || c == -1;
        }

        public boolean readBoolean() {
            return "true".equalsIgnoreCase(readString());
        }

        public double readDouble() {
            return Double.parseDouble(readString());
        }

        public float readFloat() {
            return Float.parseFloat(readString());
        }
    }

}