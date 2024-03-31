package code_generation.contest;

import code_generation.utils.IoUtil;
import code_generation.utils.StringUtils;

/**
 * @author: wuxin0011
 * @Description:
 */
public class ClassTemplate {

    private final static String default_author = "wuxin0011";
    private final static String default_methodName = "IoUtil.DEFAULT_METHOD_NAME";
    private final static String default_className = "Solution";
    private final static String MOD = "private static final int MOD = (int)1e9 + 7;";

    public String author;
    public String method;
    public String methodName;
    public String className;
    public String title;
    public String url;
    public String textFileName;
    public String packageInfo;
    public String importInfo;
    public boolean isNeedMod = false;

    public ClassTemplate() {
        this.method = "";
        this.methodName = default_methodName;
        this.className = default_className;
        this.author = default_author;
        this.textFileName = StringUtils.wrapperKey(IoUtil.DEFAULT_READ_FILE);
        this.title = "";
        this.url = "";
        this.packageInfo = "";
        this.importInfo = "";
    }

    public ClassTemplate buildMethod(String method) {
        this.method = method;
        return this;
    }

    public ClassTemplate buildMethodName(String methodName) {
        this.methodName = StringUtils.wrapperKey(methodName);
        return this;
    }

    public ClassTemplate buildClassName(String className) {
        if (StringUtils.isEmpty(className)) {
            className = default_className;
        }
        if (className.endsWith(".class")) {
            className = className.replace(".class", "");
        }
        if (className.endsWith(".java")) {
            className = className.replace(".java", "");
        }
        this.className = className;
        return this;
    }

    public ClassTemplate buildTitle(String title) {
        this.title = title;
        return this;
    }

    public ClassTemplate buildUrl(String url) {
        this.url = url;
        return this;
    }

    public ClassTemplate buildAuthor(String author) {
        this.author = StringUtils.isEmpty(author) ? default_author : author;
        return this;
    }

    public ClassTemplate buildIsNeedMod(boolean isNeedMod) {
        this.isNeedMod = isNeedMod;
        return this;
    }

    public ClassTemplate buildTextFileName(String txtFile) {
        if (StringUtils.isEmpty(txtFile)) {
            txtFile = IoUtil.DEFAULT_READ_FILE;
        }
        if (!txtFile.endsWith(".txt")) {
            txtFile = txtFile + ".txt";
        }
        this.textFileName = StringUtils.wrapperKey(txtFile);
        this.textFileName = this.textFileName.replace("\\", "\\\\");
        return this;
    }

    public ClassTemplate buildPackageInfo(String packageInfo) {
        this.packageInfo = packageInfo;
        return this;
    }

    public ClassTemplate buildImportInfo(String importInfo) {
        this.importInfo = importInfo;
        return this;
    }


    final static String TEMPLATE_PATTERN = "package %s;\n" +
            "\n" +
            "import code_generation.utils.IoUtil;\n" +
            "import java.util.*;\n" +
            "%s" +
            "/**\n" +
            " * @author: %s\n" +
            " * @Description:\n" +
            " * @url:   %s\n" +
            " * @title: %s\n" +
            " */\n" +
            "public class %s {\n\n" +
            "    public static void main(String[] args) {\n" +
            "        IoUtil.testUtil(%s.class,%s,%s);\n" +
            "    }" +
            "\n" +
            "\t %s " +
            "\n\n" +
            "\t //%s" +
            "  \n\n" +
            "}";

    public static String getTemplate(ClassTemplate info) {
        return String.format(
                TEMPLATE_PATTERN,
                info.packageInfo,
                info.importInfo,
                info.author,
                info.url,
                info.title,
                info.className,
                info.className,
                info.methodName,
                info.textFileName,
                info.isNeedMod ? MOD : "",
                info.method
        );
    }

    @Override
    public String toString() {
        return ClassTemplate.getTemplate(this);
    }

    public static void main(String[] args) {
        ClassTemplate classTemplate = new ClassTemplate();
        String methodName = "longestCommonPrefix";
        String method = "public String longestCommonPrefix(String[] ss) {    }";
        classTemplate.buildClassName("Solution")
                .buildUrl("https://leetcode.cn/problems/longest-common-prefix/")
                .buildTitle(methodName)
                .buildMethod(method)
                .buildMethodName(methodName)
                .buildImportInfo("")
                .buildTextFileName("\\txt_file\\Solution")
                .buildIsNeedMod(false)
                .buildPackageInfo("code_generation.contest");
        String template = getTemplate(classTemplate);
        System.out.println(template);
    }

}
