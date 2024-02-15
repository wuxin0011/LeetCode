package leetcode.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author: wuxin0011
 * @Description:
 */
public class NetUtils {

    public static String getPageContent(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置请求方法为 GET
        connection.setRequestMethod("GET");
        connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.58");
        connection.setRequestProperty("Cookie", "gr_user_id=8629fa68-9b44-4f19-a05f-dbc83d442db9; _bl_uid=h0lwsdt7tdOx2985elsOa13bInk0; a2873925c34ecbd2_gr_last_sent_cs1=agitated-curranfnd; csrftoken=2v93FvRtZ6NH3BozzsBzxkIgINXASjKQvSac7Hxwiaenj0j2LBPS4MKbjCZwmSZq; Hm_lvt_fa218a3ff7179639febdb15e372f411c=1687531137,1687670717; _gid=GA1.2.666744156.1687929804; Hm_lvt_f0faad39bcf8471e3ab3ef70125152c3=1687509138,1687656393,1687929803,1687970499; Hm_lpvt_f0faad39bcf8471e3ab3ef70125152c3=1687973608; _ga=GA1.1.2037691452.1675754961; LEETCODE_SESSION=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuZXh0X2FmdGVyX29hdXRoIjoiL3Byb2JsZW1zL3BsdXMtb25lL3N1Ym1pc3Npb25zLyIsIl9hdXRoX3VzZXJfaWQiOiI0NzgwNjY3IiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiZGphbmdvLmNvbnRyaWIuYXV0aC5iYWNrZW5kcy5Nb2RlbEJhY2tlbmQiLCJfYXV0aF91c2VyX2hhc2giOiIwYmQ2MTMxYWVjOTIyNDg5YzZiYjUwODBmMTliZTg3MTcwYjAwYzAxMDcyNDQ3MTAxOTk3MGM0ZGZlYjcyYTZhIiwiaWQiOjQ3ODA2NjcsImVtYWlsIjoiIiwidXNlcm5hbWUiOiJhZ2l0YXRlZC1jdXJyYW5mbmQiLCJ1c2VyX3NsdWciOiJhZ2l0YXRlZC1jdXJyYW5mbmQiLCJhdmF0YXIiOiJodHRwczovL2Fzc2V0cy5sZWV0Y29kZS5jbi9hbGl5dW4tbGMtdXBsb2FkL2RlZmF1bHRfYXZhdGFyLnBuZyIsInBob25lX3ZlcmlmaWVkIjp0cnVlLCJfdGltZXN0YW1wIjoxNjg3ODI4ODE0LjY2Mzk3OSwiZXhwaXJlZF90aW1lXyI6MTY5MDM5ODAwMCwidmVyc2lvbl9rZXlfIjoxLCJsYXRlc3RfdGltZXN0YW1wXyI6MTY4Nzk3MzYwOX0.GWjv_1TMRsnyothnnVHwQNcx00VvxmLB57CFSMwWK8U; _ga_PDVPZYN3CW=GS1.1.1687970499.59.1.1687974309.60.0.0; a2873925c34ecbd2_gr_session_id=29b68797-676e-45c5-b387-f827016664a3; a2873925c34ecbd2_gr_last_sent_sid_with_cs1=29b68797-676e-45c5-b387-f827016664a3; a2873925c34ecbd2_gr_cs1=agitated-curranfnd; a2873925c34ecbd2_gr_session_id_sent_vst=29b68797-676e-45c5-b387-f827016664a3");
        connection.setRequestProperty("Host", "leetcode.cn");

        Map<String, List<String>> requestProperties = connection.getRequestProperties();
        System.out.println("properties");
        System.out.println(requestProperties);
        // 获取响应状态码
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // 创建输入流读取器
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder content = new StringBuilder();
            char[] buf = new char[1024 * 1024];
            int len = -1;
            // 逐行读取页面内容并添加到 StringBuilder 中
            while ((len = reader.read(buf)) != -1) {
                content.append(String.valueOf(buf, 0, len));
            }
            // 关闭读取器和连接
            reader.close();
            connection.disconnect();
            return content.toString();
        } else {
            // 请求失败，返回错误信息
            throw new IOException("请求失败，错误代码：" + responseCode);
        }
    }

    public static void main(String[] args) {
        try {
            String url = "https://leetcode.cn/problems/path-sum/";
            String pageContent = getPageContent(url);
            System.out.println(pageContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
