package template.io.template;

/**
 * @author: wuxin0011
 * @Description:
 */
import java.io.*;


/**
 * @author: wuxin0011
 * @Description: io 快读模板 因为调整需要将IO方式封装成一个类 这样可以折叠同样也不影响
 * @see code_generation.utils.ACM.FastIO 这个是没有封装的
 */
public class IOTemplate {

    public static class MainClass{
        void solve(){

        }
    }


    static IO io;

    public static void main(String[] args) {
        io = new IO();
        int t = 1;
        // t = io.read();
        for(;t > 0;t--) {
            new MainClass().solve();
        }
        io.close();
    }

    public static class IO {
        static int MAXN = (int) 1e6 + 1;

        public static final String DEFAULT_FILE_NAME = "in.txt"; // default read file
        public static final boolean DELETE_CLOSE = false; // auto close ?
        public  BufferedInputStream br = null;
        public  PrintWriter ptr = null;
        public  boolean splitDOT = true; // if string contains ',' ,need split ?,default true


        {
            try {
                File file = new File(DEFAULT_FILE_NAME);
                br = file.exists() ? new BufferedInputStream(new FileInputStream(file)) : br;
            } catch (Exception ignored) {

            }
            initIO(false);
        }

        public  void initIO(boolean flushIO) {
            br = flushIO || br == null ? new BufferedInputStream(System.in) : br;
            ptr = flushIO || ptr == null ? new PrintWriter(new BufferedOutputStream(System.out)) : ptr;
        }

        public  int read() {
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

        public  void dbg(Object ...args) {
            if(!isDebug){
                return;
            }
            String argsStringIndex = "";
            // argsStringIndex += "{ ";
            // for(int i = 0;i < args.length;i++) {
            //     argsStringIndex += i;
            //     if(i != args.length-1) {
            //         argsStringIndex +=",";
            //     }
            // }
            // argsStringIndex += " }";
            String str = "";
            str += "{ ";
            for(int i = 0 ;i < args.length;i++) {
                str += String.valueOf(args[i]);
                if(i < args.length - 1) {
                    str += " ,";
                }
            }
            str += " }";
            pln(argsStringIndex + (argsStringIndex.length() != 0 ? " = " : "") + str);
        }



        public  int[] readIntArray(){
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


        public  long[] LONG_ARRAY = null;
        public  long[] readLongArray(){
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

        public  String[] STRING_ARRAY = null;
        public  String[] readStringArray(){
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

        public  void pln(Object obj) {
            print(obj);
            print("\n");
        }

        public  void print(Object obj) {
            ptr.print(obj);
        }

        public  void printf(String format,Object ...obj) {
            ptr.printf(format,obj);
        }


        public  void close() {
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

        public  char[] CHAR_ARRAY = null;

        public  char[] readCharArray(boolean line){
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

        public  String readLine() {
            return readString(true);
        }

        public  String readString() {
            return readString(false);
        }

        public  String readString(boolean line) {
            char[] temp = readCharArray(line);
            return temp == null ? null : new String(temp);
        }

        public  boolean ignore(int c) {
            if (c == ',') {
                return splitDOT;
            }
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == '\b' || c == '\f' || c == -1;
        }

        public  boolean readBoolean() {
            return "true".equalsIgnoreCase(readString());
        }

        public  double readDouble() {
            return Double.parseDouble(readString());
        }

        public  float readFloat() {
            return Float.parseFloat(readString());
        }
    }
}
