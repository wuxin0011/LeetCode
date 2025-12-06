package code_generation.crwal.request;

import code_generation.crwal.Constant;
import code_generation.utils.IoUtil;
import code_generation.utils.StringUtils;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

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

    private static final String IGNORE_DIR = File.separator + "request_config" + File.separator;


    private Map<String, String> headers;
    private Class<?> aClass;

    private String configPath;

    public Request() {
        this(Request.class);
    }

    public Request(Class<?> aClass) {
        this.aClass = Objects.requireNonNull(aClass, "class not allow null");
        this.configPath = IoUtil.buildAbsolutePath(aClass) + IGNORE_DIR; // from default class path load config
        checkConfig();
    }


    /**
     * 自定义路径
     *
     * @param configPath 绝对路径
     */
    public Request(String configPath) {
        this.configPath = configPath;
        checkConfig();
    }

    public void checkConfig() {
        if (!IoUtil.isAbsolutePath(configPath)) {
            throw new RuntimeException("place use absolutePath !");
        }
        Map<String, Map<String, String>> maps = Config.initConfig(configPath);
        this.headers = maps == null || maps.size() == 0 ? null : maps.get(Constant.headers);
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

    public String getConfigPath() {
        return configPath;
    }


    public void initHttpURLConnection(HttpURLConnection connection) {
        initHttpURLConnection(connection, false);
    }

    public void initHttpURLConnection(HttpURLConnection connection, boolean removeCookie) {
        Objects.requireNonNull(connection, "HttpURLConnection connection not allow null !");
        if (removeCookie) {
            headers.remove("cookie");
            headers.remove("Cookie");
        }
        if (headers != null && headers.size() > 0) {
            this.headers.forEach(connection::setRequestProperty);
            // System.out.println(this.headers.get("Cookie"));
        }

    }

    public static HttpURLConnection getConnection(String url) {
        try {
            if (!(url.contains("api/info/") || url.contains("https://leetcode.cn/graphql/") || url.contains("submit"))) {
                System.out.println("access url : " + url);
            }
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
        return response(requestBefore(url, ContentType, jsonStr, null, params, false));
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
        try {
            int size = 0;
            int end = map.size();
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> item : map.entrySet()) {
                sb.append(java.net.URLEncoder.encode(item.getKey(), "UTF-8"));
                sb.append(connectionTag);
                sb.append(java.net.URLEncoder.encode(item.getValue(), "UTF-8"));
                if (size != end - 1) {
                    sb.append(endTag);
                }
                size++;
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }


    public static String response(HttpURLConnection connection) {
        if (connection == null) {
            return "";
        }
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

    public static void handlerDataOutPutStream(HttpURLConnection connection, String jsonStr) {
        if (StringUtils.isEmpty(jsonStr) || connection == null) {
            return;
        }
        try {
            connection.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            // 原来使用的
            // outputStream.writeBytes(jsonStr);
            // 使用下面方式可以修复提交代码问题
            outputStream.write(jsonStr.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public HttpURLConnection requestBefore(String url, String ContentType, String jsonStr, String methodType, Map<String, String> params, boolean removeCookie) {


        if (StringUtils.isEmpty(methodType)) {
            methodType = "POST";
        }

        if (methodType.equals("GET")) {
            url = wrapperUrl(url, params);
        }

        try {
            HttpURLConnection connection = getConnection(url);
            if (connection == null) {
                throw new RuntimeException("response Error");
            }
            connection.setRequestProperty("Content-Type", ContentType == null ? applicationJSON : ContentType);
            connection.setRequestMethod(methodType);
            initHttpURLConnection(connection, removeCookie);
            handlerDataOutPutStream(connection, jsonStr);
            if (params != null && params.size() > 0) {
                handlerDataOutPutStream(connection, buildString(params, "=", "&"));
            }
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public HttpURLConnection requestResponse(String url, String ContentType, String jsonStr, Map<String, String> params, boolean removeCookie) {
        HttpURLConnection connection = requestBefore(url, ContentType, jsonStr, null, params, removeCookie);
        return connection;
    }


    public Map<String, List<String>> requestPostResponse(String url, String ContentType, String jsonStr, Map<String, String> params, boolean removeCookie) {
        HttpURLConnection connection = requestBefore(url, ContentType, jsonStr, null, params, removeCookie);
        if (connection == null) {
            return null;
        }
        Map<String, List<String>> headerFields = connection.getHeaderFields();
        connection.disconnect();
        return headerFields;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Request.class.getSimpleName() + "[", "]")
                .add("headers=" + headers)
                .add("aClass=" + aClass)
                .toString();
    }
}
