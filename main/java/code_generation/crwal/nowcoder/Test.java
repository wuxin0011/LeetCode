package code_generation.crwal.nowcoder;

import code_generation.crwal.request.Request;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Test {
    public static final Request REQUEST = new Request(Test.class);

    public static void main(String[] args) {

        String s = REQUEST.requestGet("https://questionbank.nowcoder.com/api/qmp/question/detail/by-uuid?uuid=253d2c59ec3e4bc68da16833f79a38e4", Request.applicationJSON);
        System.out.println(s);

    }
}
