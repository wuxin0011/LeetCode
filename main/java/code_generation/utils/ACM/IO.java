package code_generation.utils.ACM;

import java.io.*;

/**
 * @author: wuxin0011
 * @Description: ACM 模式 输入输出 简单封装
 */
public class IO {
    BufferedReader in;
    PrintWriter out;
    StreamTokenizer tokenizer;

    private String readContent;

    boolean isReadLine;


    double val;

    public IO() {
        this(System.in, System.out, false);
    }

    public IO(boolean line) {
        this(System.in, System.out, line);
    }

    public IO(InputStream in, PrintStream out, boolean line) {
        try {
            this.isReadLine = line;
            this.in = new BufferedReader(new InputStreamReader(in));
            this.tokenizer = new StreamTokenizer(this.in);
            this.out = new PrintWriter(new OutputStreamWriter(out));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasNext() {
        if (in == null || tokenizer == null) {
            return false;
        }
        readContent = null;
        try {
            if (isReadLine) {
                readContent = in.readLine();
                return readContent != null;
            } else {
                int eof = tokenizer.nextToken();
                if (eof == -1) {
                    return false;
                }
                if (isNumber) {
                    val = tokenizer.nval;
                } else {
                    readContent = tokenizer.sval;
                }
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public String read() {
        hasNext();
        return isReadLine ? readContent : isNumber ? String.valueOf(val) : readContent;
    }

    boolean isNumber;

    public void readNumber() {
        isNumber = true;
        read();
        isNumber = false;
    }

    public int readInt() {
        readNumber();
        return isReadLine ? Integer.parseInt(readContent) : (int) val;
    }

    public char readChar() {
        read();
        if (readContent == null) {
            return '\0';
        }
        return readContent.charAt(0);
    }

    public boolean readBoolean() {
        read();
        if (readContent == null) {
            return false;
        }
        return Boolean.parseBoolean(readContent);
    }

    public long readLong() {
        readNumber();
        return isReadLine ? Long.parseLong(readContent) : (long) (val * 1L);
    }

    public double readDouble() {
        readNumber();
        return isReadLine ? Double.parseDouble(readContent) : val;
    }

    public float readFloat() {
        readNumber();
        return isReadLine ? Float.parseFloat(readContent) : (float) val;
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
            if (tokenizer != null) {
                tokenizer = null;
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
