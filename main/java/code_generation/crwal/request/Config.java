package code_generation.crwal.request;

import code_generation.crwal.Constant;
import code_generation.utils.IoUtil;
import code_generation.utils.ReflectUtils;
import code_generation.utils.StringUtils;

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
    private static final String cookieFile = Constant.cookies + ".txt";

    private Config() {

    }

    /**
     * 初始化读取配置文件 并 将配置文件内容加载到 map<String,String> 中保存
     * 同时以配置文件 name 作为 key 方便后续获取
     *
     * @param absolutePath 绝对路径 如果不是绝对路径会报错
     * @return map
     */
    public static Map<String, Map<String, String>> initConfig(String absolutePath) {
        if (!IoUtil.isAbsolutePath(absolutePath)) {
            throw new RuntimeException("path must be absolute path");
        }
        String[] configs = Constant.CONFIGS;
        Map<String, Map<String, String>> maps = new HashMap<>(configs.length);
        for (String configName : configs) {
            maps.put(configName, configToMap(absolutePath, configName));
        }
        return maps;
    }


    public static Properties getConfig(String path, String configName) {
        try {
            File file = null;
            Properties properties = new Properties();
            if ((file = createConfigFile(path, configName)) == null) {
                System.out.println("place config " + configName);
                return properties;
            }
            properties.load(new FileReader(file));
            if (file.getName().equals(check(Constant.headers))) {
                String cookieInfo = getCookieContent(path, properties);
                properties.setProperty("Cookie", cookieInfo);
            }
            return properties;
        } catch (IOException e) {
            return null;
        }
    }


    private static File createConfigFile(String path, String configName) {
        configName = check(configName);

        // if path contains xxxx.properties 将读取
        if (path.contains(configName)) {
            configName = path;
        } else {
            configName = path + File.separator + configName;
        }
        return createConfigFile(new File(configName));
    }


    @SuppressWarnings("all")
    private static File createConfigFile(File file, Class<?> c) {
        return createConfigFile(file);
    }


    @SuppressWarnings("all")
    private static File createConfigFile(File file) {
        BufferedWriter writer = null;
        try {
            if (file.exists()) {
                return file;
            }
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(defaultTemplate);
            writer.flush();
            writer.close();
            System.out.println("create " + file.getAbsolutePath() + " success !");


            // check cookies.txt
            File cookiesFile = new File(parentFile.getAbsolutePath() + File.separator + Constant.cookies + ".txt");
            if (!cookiesFile.exists()) {
                cookiesFile.createNewFile();
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            IoUtil.close(writer);
        }
    }


    /**
     * 指定 name 获取 map
     *
     * @param config config
     * @param names  names[]
     * @return map<String, String>
     */
    public static Map<String, String> configToMap(Properties config, String[] names) {
        Map<String, String> map = new HashMap<>();
        if (config == null) {
            return map;
        }
        for (String name : names) {
            if (!StringUtils.strictIsEmpty(name)) {
                String property = config.getProperty(name);
                if (!StringUtils.strictIsEmpty(property)) {
                    map.put(name, config.getProperty(name));
                }
            }
        }
        return map;
    }


    /**
     * 将 path 路径中 指定配置文件转换为 map
     *
     * @param path       路径
     * @param configName 文件名
     * @return map
     */
    public static Map<String, String> configToMap(String path, String configName) {
        Properties config = getConfig(path, configName);
        if (config == null) {
            return new HashMap<>(0);
        }
        return configToMap(config);
    }


    /**
     * 从配置文件中检查cookie
     *
     * @param configPath 路径 默认会读取 路径中的 cookies.txt 文件 作为 cookie 如果没有 在从 properties 中 读取
     * @param properties config
     * @return cookie
     */
    private static String getCookieContent(String configPath, final Properties properties) {
        configPath = configPath.contains(cookieFile) ? configPath : (configPath + File.separator + cookieFile);
        if (!IoUtil.isAbsolutePath(configPath)) {
            throw new RuntimeException("place use absolute path !");
        }
        String s = IoUtil.readContent(configPath);
        // from cookies.txt read content is must high
        if (s.length() > 0) {
            s = ReflectUtils.toString(s);
            checkCookie(s, configPath);
            return s;
        }
        s = properties.getProperty("Cookie");
        if (s != null && s.length() > 0) {
            s = ReflectUtils.toString(s);
            checkCookie(s, configPath);
            return s;
        }

        // check cookie name is error try cookies
        s = properties.getProperty(Constant.cookies);
        if (s.length() > 0) {
            s = ReflectUtils.toString(s);
            checkCookie(s, configPath);
            return s;
        }
        // if cookies.txt not exist create it
        IoUtil.createFile(configPath + cookieFile);
        System.out.println("place config your cookie in cookies.txt or !" + Constant.headers + ".properties file !");
        return "";
    }


    private static void checkCookie(String cookie, String path) {
        if (cookie == null || cookie.length() == 0 || cookie.equalsIgnoreCase("cookie") || cookie.equalsIgnoreCase(Constant.cookies)) {
            throw new RuntimeException("place check your cookie ! in " + path);
        }
    }

    private static String check(String name) {
        return name.endsWith(config_suffix) ? name : (name + config_suffix);
    }

    /**
     * 配置文件转换为map
     *
     * @param config 配置文件
     * @return map
     */
    public static Map<String, String> configToMap(Properties config) {
        if (config == null) {
            return null;
        }
        Set<Map.Entry<Object, Object>> entries = config.entrySet();
        Map<String, String> map = new HashMap<String, String>(config.size());
        for (Map.Entry<Object, Object> entry : entries) {
            String key = String.valueOf(entry.getKey());
            String value = String.valueOf(entry.getValue());

            // ignore null str
            if (StringUtils.strictIsEmpty(key) || StringUtils.strictIsEmpty(value)) {
                continue;
            }

            map.put(key, value);
        }
        return map;
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
            "Accept-Language=zh-CN,zh;desc=0.9,en;desc=0.8,en-GB;desc=0.7,en-US;desc=0.6\n" +
            "Cache-Control=max-age=0";

}
