package code_generation.utils.ACM;

import java.io.*;
import java.util.*;
/**
 * @author: wuxin0011
 */
public class FastIO {


    static int MAXN = (int) 1e6 + 1;

    // static int[] a = new int[MAXN];
    // static String[] a = new String[MAXN];


    public static void solve() {


    }

    public static void main(String[] args) {
        int t = 1;
        // t = read();
        while(t > 0) {
            solve();
            t--;
        }
        close();
    }


    public static int lowerBound(int[] array, int size, int target) {
        int l = 0, r = size - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (array[mid] >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static long qpow(long x, int n, int mod) {
        long ans = 1L;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = ans * x % mod;
            }
            n >>= 1;
            x = x * x % mod;
        }
        return ans;
    }

    public static long gcd(long x, long y) {
        while (y != 0 && x != 0) {
            long temp = x % y;
            x = y;
            y = temp;
        }
        return x;
    }

    static void printArray(int[] arr, int st, int end) {
        print("[");
        for (int i = st; i < end; i++) {
            print(arr[i]);
            if (i != end - 1) {
                print(", ");
            }
        }
        print("]\n");
    }

    static void printArray(long[] arr, int st, int end) {
        print("[");
        for (int i = st; i < end; i++) {
            print(arr[i]);
            if (i != end - 1) {
                print(", ");
            }
        }
        print("]\n");
    }

    static void printArray(int[][] arr, int rowStart, int rowEnd, int colStart, int colEnd) {
        print("[\n");
        for (int i = rowStart; i < rowEnd; i++) {
            printArray(arr[i], colStart, colEnd);
        }
        print("]\n");
    }

    public static final String DEFAULT_FILE_NAME = "in.txt"; // default read file
    public static final boolean DELETE_CLOSE = false; // auto close ?
    public static BufferedInputStream br = null;
    public static PrintWriter ptr = null;
    public static boolean splitDOT = true; // if string contains ',' ,need split ?,default true

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


    public static int[] readIntArray(){
        long[] temp = readLongArray();
        if(temp == null) {
            return null;
        }
        int[] ints = new int[temp.length];
        for(int i = 0;i < temp.length;i++) {
            if(temp[i] < Integer.MIN_VALUE || temp[i] > Integer.MAX_VALUE){
                throw new RuntimeException("overflow int type");
            }
            ints[i] = (int)temp[i];
        }
        return ints;
    }


    public static long[] LONG_ARRAY = null;
    public static long[] readLongArray(){
        if( LONG_ARRAY == null) {
            LONG_ARRAY = new long[100001];
        }
        int size = 0;
        while(true) {
            try{
                int f = 1;
                long x = 0;
                int c = br.read();
                if(c == -1) {
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
                if(c == '\n' || c == -1) {
                    break;
                }
            }catch(Exception E){
                return null;
            }
        }
        if(size == 0) {
            return null;
        }
        long[] temp = new long[size];
        System.arraycopy(LONG_ARRAY,0,temp,0,size);
        return temp;
    }

    public static String[] STRING_ARRAY = null;
    public static String[] readStringArray(){
        if( STRING_ARRAY == null) {
            STRING_ARRAY = new String[100001];
        }
        int size = 0;
        while(true) {
            try{
                if (CHAR_ARRAY == null) {
                    CHAR_ARRAY = new char[MAX_STRING_LENGTH];
                }
                int x = br.read();
                if(x == -1){
                    return null;
                }
                while (ignore(x)) {
                    x = br.read();
                }
                if(x == -1){
                    return null;
                }
                int currentSize = 0;
                while (x != ' ' &&  x != ',' && !ignore(x)) {
                    CHAR_ARRAY[currentSize++] = (char) x;
                    x = br.read();
                }
                STRING_ARRAY[size++] = new String(CHAR_ARRAY,0,currentSize);
                if(x == '\n' || x == -1) {
                    break;
                }
            }catch(Exception E){
                return null;
            }
        }
        if(size == 0) {
            return null;
        }
        String[] temp = new String[size];
        System.arraycopy(STRING_ARRAY,0,temp,0,size);
        return temp;
    }

    public static void pln(Object obj) {
        ptr.println(obj);
    }

    public static void print(Object obj) {
        ptr.print(obj);
    }

    public static void printf(String format,Object ...obj) {
        ptr.printf(format,obj);
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

    public static char[] CHAR_ARRAY = null;

    public static char[] readCharArray(boolean line){
        try {
            if (CHAR_ARRAY == null) {
                CHAR_ARRAY = new char[MAX_STRING_LENGTH];
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
            if(currentSize == 0 && x == -1){
                return null;
            }
            char[] temp = new char[currentSize];
            System.arraycopy(CHAR_ARRAY,0,temp,0,currentSize);
            return temp;
        } catch (Exception e) {
            return null;
        }
    }

    public static String readLine() {
        return readString(true);
    }

    public static String readString() {
        return readString(false);
    }

    public static String readString(boolean line) {
        char[] temp = readCharArray(line);
        return temp == null ? null : new String(temp);
    }

    public static boolean ignore(int c) {
        if (c == ',') {
            return splitDOT;
        }
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