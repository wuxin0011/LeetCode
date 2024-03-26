package code_generation.crwal.request;

import code_generation.crwal.Constant;
import code_generation.utils.IoUtil;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Request {

    public static final String applicationJSON = "application/json";
    public static final String applicationFORM = "application/x-www-form-urlencoded";
    public static final String applicationText = "application/text; charset=utf-8";
    public static final String applicationHtml = "text/html; charset=utf-8";
    public static final byte[] buff = new byte[1024 * 1024];


    private Map<String, String> headers;
    private Class<?> aClass;

    public Request() {
        this(Request.class);
    }

    public Request(Class<?> aClass) {
        this.aClass = Objects.requireNonNull(aClass, "class not allow null");
        Map<String, Map<String, String>> maps = Config.initConfig(aClass);
        this.headers = maps.get(Constant.headers);
    }


    public Class<?> getaClass() {
        return aClass;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }


    public void initHttpURLConnection(HttpURLConnection connection) {
        Objects.requireNonNull(connection, "HttpURLConnection connection not allow null !");
        if (headers != null && headers.size() > 0) {
            this.headers.forEach(connection::setRequestProperty);
            // System.out.println(this.headers.get("Cookie"));
        }

    }


    public static HttpURLConnection getConnection(String url) {
        try {
            System.out.println("access url : " + url);
            URL apiUrl = new URL(url);
            return (HttpURLConnection) apiUrl.openConnection();
        } catch (IOException e) {
            return null;
        }
    }

    public String requestGet(String url) {
        return requestGet(url, null, null);
    }

    public String requestGet(String url, String Accept) {
        return requestGet(url, Accept, null);
    }


    public String requestGet(String url, String Accept, Map<String, String> params) {
        url = wrapperUrl(url, params);
        HttpURLConnection connection = getConnection(url);
        initHttpURLConnection(connection);
        if (connection != null) {
            connection.setRequestProperty("Content-Type", Accept == null ? applicationHtml : Accept);
            return requestGet(connection);
        }
        return "";
    }


    /**
     * 发送一个GET请求
     *
     * @param connection 一个响应内容 可以自定义
     * @return 返回一个字符串 内容
     */
    public String requestGet(HttpURLConnection connection) {
        try {
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException(" response error ! code = " + connection.getResponseCode());
            }
            return response(connection);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public String requestPost(String url, String jsonStr) {

        return requestPost(url, applicationJSON, jsonStr, null);
    }

    public String requestPost(String url, String ContentType, String jsonStr, Map<String, String> params) {
        url = wrapperUrl(url, params);

        try {
            HttpURLConnection connection = getConnection(url);
            if (connection == null) {
                return "";
            }
            connection.setRequestProperty("Content-Type", ContentType == null ? applicationJSON : ContentType);
            initHttpURLConnection(connection);
            connection.setRequestMethod("POST");
            if (jsonStr != null && jsonStr.length() > 0) {
                connection.setDoOutput(true);
                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(jsonStr);
                outputStream.flush();
                outputStream.close();
            }
            return response(connection);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }


    public static String wrapperUrl(String url, Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return url;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        sb.append("?");
        sb.append(buildString(params, "=", "&"));
        return sb.toString();
    }


    // // key1=value1;key2=value2
    public static String buildCookies(Map<String, String> cookies) {
        return buildString(cookies, "=", ";");
    }

    // key1=value1&key2=value2
    public static String buildBody(Map<String, String> body) {
        return buildString(body, "=", "&");
    }

    public static String buildString(Map<String, String> map, String connectionTag, String endTag) {
        Objects.requireNonNull(map, "map not allow null");
        Objects.requireNonNull(connectionTag, "connectionTag not allow null");
        Objects.requireNonNull(endTag, "endTag not allow null");
        int size = 0;
        int end = map.size();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> item : map.entrySet()) {
            sb.append(item.getKey());
            sb.append(connectionTag);
            sb.append(item.getValue());
            if (size != end - 1) {
                sb.append(endTag);
            }
            size++;
        }
        return sb.toString();
    }


    public static String response(HttpURLConnection connection) {
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            int l = -1;
            while ((l = bis.read(buff)) != -1) {
                response.append(new String(buff, 0, l)).append("\n");
            }
            connection.disconnect();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            IoUtil.close(bis);
        }


    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Request.class.getSimpleName() + "[", "]")
                .add("headers=" + headers)
                .add("aClass=" + aClass)
                .toString();
    }
}
