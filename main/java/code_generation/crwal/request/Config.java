package code_generation.crwal.request;

import code_generation.crwal.Constant;
import code_generation.utils.IoUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Config {

    private Config() {

    }

    public static Properties getConfig(Class<?> c) {
        return getConfig(c, Constant.CONFIG_NAME);
    }

    public static Properties getConfig(Class<?> c, String configName) {
        try {
            File file = null;
            if ((file = createConfigFile(configName, c)) == null) {
                configTips();
            }
            Properties properties = new Properties();
            properties.load(new FileReader(file));
            return properties;
        } catch (IOException e) {
            return null;
        }
    }


    private static final String defaultTemplate = "csrftoken=null\nsession=null\nhost=null\nreferrer=null";

    private static File createConfigFile(String configName, Class<?> c) {
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
        throw new RuntimeException("place config your session or csrftoken " + Constant.CONFIG_NAME);
    }


    public static Map<String, String> getCookieMap(Class<?> c, String configName, String[] names) {
        Properties config = getConfig(c, configName);
        return getCookieMap(config, names);
    }

    public static Map<String, String> getCookieMap(Properties config, String[] names) {
        Map<String, String> cookies = new HashMap<>();
        if (config == null) {
            return cookies;
        }
        for (String name : names) {
            if (!Config.isEmpty(name)) {
                String property = config.getProperty(name);
                if (!Config.isEmpty(property)) {
                    cookies.put(name, config.getProperty(name));
                }
            }
        }
        return cookies;
    }


}
