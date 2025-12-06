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
    private final static String default_constructor_methodName = "ParseCodeInfo.ConstructorClass";
    private final static String default_constructor_info = "import code_generation.contest.ParseCodeInfo;\n";
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
    public String questionId;
    public boolean isNeedMod = false;


    public ParseCodeInfo codeInfo;

    public ClassTemplate() {
        this.method = "";
        this.methodName = default_methodName;
        this.className = default_className;
        this.author = default_author;
        this.textFileName = StringUtils.wrapperKey(IoUtil.DEFAULT_READ_FILE);
        this.title = "";
        this.url = "";
        this.questionId = "";
        this.packageInfo = "";
        this.importInfo = "";
    }

    public ClassTemplate buildMethod(String method) {
        this.method = method;
        int i = StringUtils.kmpSearch(method, "TreeNode");
        if(i!=-1){
            this.buildImportInfo("import code_generation.bean.TreeNode;\n");
            return this;
        }else{
            i = StringUtils.kmpSearch(method, "ListNode");
            if(i!=-1){
                this.buildImportInfo("import code_generation.bean.ListNode;\n");
            }
            return this;
        }
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

    public ClassTemplate buildCodeInfo(ParseCodeInfo codeInfo) {
        this.codeInfo = codeInfo;
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

    public ClassTemplate buildQuestionId(String questionId) {
        this.questionId = questionId;
        return this;
    }

    public ClassTemplate buildImportInfo(String importInfo) {
        this.importInfo = importInfo;
        return this;
    }

    // 构造类模板
    final static String CONSTRUCTOR_CLASS = "public static class %s { \n" +
            "    %s" +
            "\n\t}";

    public static String createConstructorClassTemplate(String className, String method) {
        return String.format(CONSTRUCTOR_CLASS, className, method);
    }


    final static String TEMPLATE_PATTERN = "package %s;\n" +
            "\n" +
            "import code_generation.utils.IoUtil;\n" +
            "import code_generation.annotation.TestCaseGroup;\n" +
            "import java.util.*;\n" +
            "%s" +
            "/**\n" +
            " * @author: %s\n" +
            " * @Description:\n" +
            " * @url:   <a href=\"%s\">%s</a>\n" +
            " * @title: %s\n" +
            " */\n" +
            "//@TestCaseGroup(start = 1,end = 0x3fff,use = true)\n" +
            "public class %s {\n\n" +
            "    public static void main(String[] args) {\n" +
            "        IoUtil.testUtil(%s.class,%s,%s);\n" +
            "    }" +
            "\n" +
            "    %s " +
            "\n\n" +
            "    %s" +
            "  \n\n" +
            "}";


    public static String getTemplate(ClassTemplate info) {
        ParseCodeInfo codeInfo = info.codeInfo;
        String originClassName = info.className;
        boolean isNot = codeInfo == null || !codeInfo.isConstructor();
        String className = isNot ? info.className : codeInfo.getClassName();
        String method = isNot ? info.method : createConstructorClassTemplate(codeInfo.getClassName(), codeInfo.getMethod());
        String importInfo = isNot ? info.importInfo : info.importInfo + "\n" + default_constructor_info;
        String methodName = isNot ? info.methodName : default_constructor_methodName;
        return String.format(
                TEMPLATE_PATTERN,
                info.packageInfo,
                importInfo,
                info.author,
                info.url,
                info.title,
                info.title,
                originClassName,
                className,
                methodName,
                info.textFileName,
                MOD,
                method
        );
    }


    @Override
    public String toString() {
        return ClassTemplate.getTemplate(this);
    }

    public static void main(String[] args) {
        ClassTemplate classTemplate = new ClassTemplate();
        String methodName = "longestCommonPrefix";
        String method = "public String longestCommonPrefix(String[] ss) {   \n\n return null;\n}";
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
