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
import java.util.Properties;

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


    private Map<String, String> cookies;
    private String host;
    private String referrer;
    private Properties properties;
    private String configName;
    private Class<?> aClass;

    public Request(Class<?> aClass, String[] names) {
        this(null, null, aClass, names);
    }


    public Request(String host, String referrer, Class<?> aClass, String[] names) {
        this.aClass = Objects.requireNonNull(aClass, "class not allow null");
        this.properties = Config.getConfig(aClass);
        this.cookies = Config.getCookieMap(properties, names);
        this.host = host == null ? properties.getProperty("host") : host;
        this.referrer = referrer == null ? properties.getProperty("referrer") : referrer;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public Class<?> getaClass() {
        return aClass;
    }

    public void setaClass(Class<?> aClass) {
        this.aClass = aClass;
    }


    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public String getHost() {
        return host;
    }

    public String getReferrer() {
        return referrer;
    }

    public void initHttpURLConnection(HttpURLConnection connection) {
        Objects.requireNonNull(connection, "HttpURLConnection connection not allow null !");
        connection.setRequestProperty("User-Agent", Constant.getUserAgent());
        if (cookies != null && cookies.size() != 0) {
            String s = buildCookies(cookies);
            System.out.println("cookies=====>" + s);
            connection.setRequestProperty("Cookie", s);
        }
        connection.setRequestProperty("Connection", "keep-alive");
        if (host != null && host.length() != 0) {
            connection.setRequestProperty("host", host);
        }
        if (referrer != null && referrer.length() != 0) {
            connection.setRequestProperty("referrer", referrer);
        }
    }

    public static HttpURLConnection getConnection(String url) {
        try {
            URL apiUrl = new URL(url);
            return (HttpURLConnection) apiUrl.openConnection();
        } catch (IOException e) {
            return null;
        }
    }

    public String requestGet(String url) {
        return requestGet(url, null, null);
    }


    public String requestGet(String url, String Accept, Map<String, String> params) {
        url = wrapperUrl(url, params);
        HttpURLConnection connection = getConnection(url);
        initHttpURLConnection(connection);
        if (connection != null) {
            connection.setRequestProperty("Accept", Accept == null ? applicationHtml : Accept);
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
            System.out.println("place check csrftoken and session is not expire ！");
            System.out.println("============================");
            e.printStackTrace();
            System.out.println("============================");
            return "";
        }
    }

    public String requestPost(String url, Map<String, String> requestBody) {
        return requestPost(url, null, requestBody, null);
    }

    public String requestPost(String url, String ContentType, Map<String, String> requestBody, Map<String, String> params) {
        url = wrapperUrl(url, params);

        try {
            HttpURLConnection connection = getConnection(url);
            if (connection == null) {
                return "";
            }
            connection.setRequestProperty("Content-Type", applicationJSON);
            initHttpURLConnection(connection);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            String postData = buildBody(requestBody);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(postData);
            outputStream.flush();
            outputStream.close();
            return response(connection);
        } catch (Exception e) {
            System.out.println("place check csrftoken and session is not expire ! ");
            System.out.println("============================");
            e.printStackTrace();
            System.out.println("============================");
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
        for (Map.Entry<String, String> cookie : map.entrySet()) {
            sb.append(cookie.getKey());
            sb.append(connectionTag);
            sb.append(cookie.getValue());
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
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException(" response error ! code = " + connection.getResponseCode());
            }
            bis = new BufferedInputStream(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            int l = -1;
            while ((l = bis.read(buff)) != -1) {
                response.append(new String(buff, 0, l)).append("\n");
            }
            connection.disconnect();
            return response.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(bis);
        }
    }


}
