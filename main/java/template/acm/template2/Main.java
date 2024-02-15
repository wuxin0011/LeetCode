package template.acm.template2;

/**
 * @author: wuxin0011
 * @Description:
 */
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out=new BufferedWriter(new OutputStreamWriter(System.out));

        //in.read(),in.readLine().....
        //out.write()......

        in.close();
        out.close();//关流，不然有些数据还在缓冲区
    }
}

