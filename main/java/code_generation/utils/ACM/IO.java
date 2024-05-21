package code_generation.utils.ACM;

import java.io.*;

/**
 * @author: wuxin0011
 * @Description: ACM 模式 输入输出 简单封装
 */
public class IO {
    BufferedReader in;
    PrintWriter out;

    public IO() {
        this(System.in, System.out);
    }

    public IO(InputStream in, PrintStream out) {
        try {
            this.in = new BufferedReader(new InputStreamReader(in));
            this.out = new PrintWriter(new OutputStreamWriter(out));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String read() {
        try {
            return in.readLine();
        } catch (Exception e) {
            return "";
        }
    }

    public int readInt() {
        return Integer.parseInt(read());
    }

    public char readChar() {
        return read().charAt(0);
    }

    public boolean readBoolean() {
        return Boolean.parseBoolean(read());
    }

    public long readLong() {
        return Long.parseLong(read());
    }

    public double readDouble() {
        return Double.parseDouble(read());
    }

    public float readFloat() {
        return Float.parseFloat(read());
    }

    public void println(Object obj) {
        out.println(obj);
    }

    public void print(Object obj) {
        out.print(obj);
    }

    public void close() {
        try {
            if (in != null) {
                in.close();
                in = null;
            }
            if (out != null) {
                out.flush();
                out.close();
                out = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
