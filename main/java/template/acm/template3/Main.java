package template.acm.template3;

/**
 * @author: wuxin0011
 * @Description:
 */
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {

        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        //in.nextToken()    ：解析下一个标记
        //in.nval           ：标记的double值
        //例如：int a=(int)in.nval;
        //或者：double b=in.nval;
        //in.sval           ：标记的String值
        //例如：String str=sval;

        //如果当前的数据是数字，就通过in.nval字段获取对应值
        //如果是字符串,就通过in.sval字段获取对应值

        //例如：读取10个double型数字并打印
        for(int i=0;i<10;++i){
            //这里的nextToken方法是会抛出异常的，为了代码的简洁，就直接在main方法使用throws声明异常的抛出
            in.nextToken();
            out.println(in.nval);
        }
        out.close();
    }
}

