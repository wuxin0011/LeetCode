package code_generation.crwal.request;

import code_generation.crwal.Constant;
import code_generation.utils.IoUtil;
import code_generation.utils.ReflectUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Config {

    public final static String config_suffix = ".properties";

    private Config() {

    }

    public static Properties getConfig(Class<?> c) {
        return getConfig(c, Constant.CONFIG_NAME);
    }

    public static Properties getConfig(Class<?> c, String configName) {
        try {
            File file = null;
            Properties properties = new Properties();
            if ((file = createConfigFile(configName, c)) == null) {
                System.out.println("place config " + configName);
                return properties;
            }
            properties.load(new FileReader(file));
            if (file.getName().equals(check(Constant.headers))) {
                String cookieInfo = getCookieContent(c, properties);
                properties.setProperty("Cookie", cookieInfo);
            }
            return properties;
        } catch (IOException e) {
            return null;
        }
    }


    private static final String defaultTemplate = "Host=Host\n" +
            "Referer=Referer\n" +
            "Origin=Origin\n" +
            "Connection=keep-alive\n" +
            "Cookie=Cookie\n" +
            "Accept=*/*\n" +
            "Sec-Fetch-Mode=cors\n" +
            "Sec-Ch-Ua-Platform=Windows\n" +
            "User-Agent=\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36 Edg/116.0.1938.62\";\n" +
            "Accept-Language=zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\n" +
            "Cache-Control=max-age=0";

    private static File createConfigFile(String configName, Class<?> c) {
        configName = check(configName);
        configName = IoUtil.wrapperAbsolutePath(c, configName);
        File file = new File(configName);
        return createConfigFile(file, c);
    }


    @SuppressWarnings("all")
    private static File createConfigFile(File file, Class<?> c) {
        BufferedWriter writer = null;
        try {
            if (file.exists()) {
                return file;
            }
            file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(defaultTemplate);
            writer.flush();
            writer.close();
            System.out.println("create " + file.getName() + " succcess !");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            IoUtil.close(writer);
        }
    }


    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0 || "null".equals(s);
    }

    private static void configTips() {

    }


    public static Map<String, String> configToMap(Class<?> c, String configName, String[] names) {
        Properties config = getConfig(c, configName);
        return configToMap(config, names);
    }

    public static Map<String, String> configToMap(Properties config, String[] names) {
        Map<String, String> map = new HashMap<>();
        if (config == null) {
            return map;
        }
        for (String name : names) {
            if (!Config.isEmpty(name)) {
                String property = config.getProperty(name);
                if (!Config.isEmpty(property)) {
                    map.put(name, config.getProperty(name));
                }
            }
        }
        return map;
    }


    public static Map<String, String> configToMap(Class<?> c, String configName) {
        Map<String, String> map = new HashMap<>(0);
        Properties config = getConfig(c, configName);
        if (config == null) {
            return map;
        }
        Set<Map.Entry<Object, Object>> entries = config.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            String key = String.valueOf(entry.getKey());
            String value = String.valueOf(entry.getValue());

            // ignore null str
            if ("null".equals(key) || "null".equals(value)) {
                continue;
            }

            map.put(key, value);
        }
        return map;
    }

    private static String check(String name) {
        return name.endsWith(config_suffix) ? name : (name + config_suffix);
    }


    public static Map<String, Map<String, String>> initConfig(Class<?> aClass) {
        String[] configs = Constant.CONFIGS;
        Map<String, Map<String, String>> maps = new HashMap<>(configs.length);
        for (String name : configs) {
            maps.put(name, configToMap(aClass, name));
        }
        return maps;
    }


    private static String getCookieContent(final Class<?> aClass, final Properties properties) {
        final String cookieFile = Constant.cookies + ".txt";

        String s = IoUtil.readContent(aClass, cookieFile);
        // from cookies.txt read content is must high


        if (s.length() > 0) {
            s = ReflectUtils.toString(s);
            checkCookie(s);
            return s;
        }
        s = properties.getProperty("Cookie");
        if (s != null  && s.length() > 0) {
            s = ReflectUtils.toString(s);
            checkCookie(s);
            return s;
        }

        // check cookie name is error try cookies
        s = properties.getProperty(Constant.cookies);
        if (s.length() > 0) {
            s = ReflectUtils.toString(s);
            checkCookie(s);
            return s;
        }
        // if cookies.txt not exist create it
        IoUtil.createFile(aClass, cookieFile);
        System.out.println("place config your cookie in cookies.txt or !" + Constant.headers + ".properties file !");
        return "";
    }


    public static void checkCookie(String cookie) {
        if (cookie == null || cookie.length() == 0 || cookie.equalsIgnoreCase("cookie") || cookie.equalsIgnoreCase(Constant.cookies)) {
            throw new RuntimeException("place check your cookie !");
        }
    }


}
