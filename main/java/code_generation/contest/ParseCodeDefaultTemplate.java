package code_generation.contest;

import code_generation.utils.StringUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: wuxin0011
 * @Description:
 */
public class ParseCodeDefaultTemplate implements ParseCodeTemplate {

    private final static String[] defaultNames = {"Solution"};
    public final static String ConstructorClass = ParseCodeInfo.ConstructorClass;


    public static final String leftFlag = "/*";
    public static final String rightFlag = "*/";


    public ParseCodeInfo info;

    public String input;
    public String startFlag;

    public boolean isNewHandler;


    public ParseCodeDefaultTemplate(String startFlag) {
        this("", startFlag, null);
    }


    public ParseCodeDefaultTemplate(String startFlag, String[] expectClassNames) {
        this("", startFlag, expectClassNames);
    }


    public ParseCodeDefaultTemplate(String input, String startFlag, String[] expectClassNames) {
        expectClassNames = expectClassNames == null || expectClassNames.length == 0 ? defaultNames : expectClassNames;
        this.info = new ParseCodeInfo(expectClassNames);
        this.startFlag = startFlag;
        this.info.setOrigin(input);
    }

    public ParseCodeInfo parseCodeTemplate(String input, String startFlag, boolean isNewHandler) {
        this.isNewHandler = isNewHandler;
        return parseCodeTemplate(input, startFlag);
    }


    public ParseCodeInfo parseCodeTemplate(String input, String startFlag) {
        try {
            this.input = input;
            this.startFlag = startFlag;
            this.info.setOrigin(input);
            init();
            return this.info;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public ParseCodeInfo parseCodeTemplate(String input) {
        return this.parseCodeTemplate(input, this.startFlag);
    }

    public void parseProcess() {
        Objects.requireNonNull(this.info, "init fail");
        setClassName();
        setMethod();
        setMethodName();
    }


    public void init() {
        if (StringUtils.isEmpty(startFlag)) {
            throw new RuntimeException("place input start flag");
        }
        int i = StringUtils.kmpSearch(input, startFlag);
        if (i == -1) {
            throw new RuntimeException("parse code fail, not find code flag  " + startFlag + ",place check this problems is not membership question !");
        }
        if (isNewHandler) {
            this.input = findNewHandlerCode(this.input, i);
        } else {
            this.input = input.substring(i + startFlag.length());
        }
        parseProcess();
    }


    public void setClassName() {
        String in = this.input;
        for (String name : this.info.getNoConstructorName()) {
            int i = StringUtils.kmpSearch(in, name);
            if (i != -1) {
                this.info.setClassName(name);
                this.info.setConstructor(false);
                return;
            }
        }

        Pattern compile = Pattern.compile("(public|private)?\\s+class\\s+(\\S+)\\s+\\{(.*?)");
        Matcher matcher = compile.matcher(in);
        String className = "";
        if (matcher.find()) {
            className = matcher.group();
        } else {
            int i = StringUtils.kmpSearch(in, "{");
            if (i != -1) {
                className = in.substring(0, i - 1);
            }
        }
        className = className.replace("\"", "");
        className = className.replace("'", "");
        className = className.replace("}", "");
        className = className.replace("{", "");
        className = className.replace("class", "");
        className = className.replace("public", "");
        className = className.replace("private", "");
        className = className.replace("protected", "");
        className = className.replace(" ", "");
        this.info.setClassName(className);
        this.info.setConstructor(true);
    }

    public void setMethod() {

        String methodStr = this.input;
        String className = this.info.getClassName();
        int i = StringUtils.kmpSearch(methodStr, className);
        if (i != -1) {
            methodStr = methodStr.substring(i + className.length());
        }

        int deep = 0;
        StringBuilder sb = null;
        for (i = 0; i < methodStr.length(); i++) {
            char c = methodStr.charAt(i);
            if (c == '{') {
                deep++;
                if (deep == 1 && sb == null) {
                    sb = new StringBuilder();
                } else if (deep == 2 && sb != null) {
                    sb.append(c);
                }
            } else if (c == '}') {
                deep--;
                if (deep == 0) {
                    break;
                }
                if (sb != null) {
                    sb.append(c);
                }

            } else {
                if (sb != null) sb.append(c);
            }
        }
        methodStr = sb != null ? sb.toString() : null;
        methodStr = StringUtils.handerMethodString(methodStr);
        methodStr = handlerReturnType(methodStr);
        // 移出方法注释内容
        methodStr = removeComment(methodStr);
        this.info.setMethod(methodStr);
    }

    public void setMethodName() {
        // 对于构造类应该直接返回一个标志
        // 后续可以使用这个标志判断调用构造
        if (this.info.isConstructor()) {
            this.info.setMethodName(ConstructorClass);
        } else {
            this.info.setMethodName(StringUtils.getMethodName(this.info.getMethod()));
        }
    }

    // TODO 处理返回值类型
    public String handlerReturnType(String methodStr) {
        Objects.requireNonNull(methodStr, "str not allow null");

        String[] split = methodStr.split("\\}");

        final String regex = "(protected|private|public)\\s+([a-zA-Z<>]+)\\s+(\\w+)\\((.*)\\)";

        final Pattern pattern = Pattern.compile(regex);

        // save match result
        StringBuilder result = new StringBuilder();

        for (String s : split) {
            if (StringUtils.isEmpty(s)) {
                continue;
            }
            if(s.contains(this.info.getClassName())){
                s += buildReturnType("");
                result.append(s);
                result.append("\n\n");
                continue;
            }
            // System.out.println("origin : " + s);
            String m = "";
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                String returnType = matcher.group(2);
                m = buildReturnType(returnType);
            } else {
                // s = "//  " + s + "  }";
                StringBuilder returnType = new StringBuilder();
                int deep = 0;
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    if (c == '<') {
                        deep++;
                        returnType.append(c);
                    } else if (c == '>') {
                        deep--;
                        returnType.append(c);
                        if (deep == 0) {
                            break;
                        }
                    } else if (c == ' ' && deep == 0) {
                        break;
                    } else {
                        returnType.append(c);
                    }
                }
                m = buildReturnType(returnType.toString());
            }
            s += m;
            result.append(s);
            result.append("\n\n");
        }
        return result.toString();
    }


    private String build(String s) {
        boolean is = this.info.isConstructor();
        final String p = "\n\n" + (is ? "\t  " : " ") + "       %s \n" + (is ? "\t  " : "\t") + "}";
        return String.format(p, s);
    }


    public String buildReturnType(String returnType) {
        String m = "";
        returnType = returnType.trim();
        switch (returnType) {
            case "byte":
            case "Byte":
            case "short":
            case "Short":
            case "int":
            case "Integer":
            case "double":
            case "Double":
            case "float":
            case "Float":
            case "long":
            case "Long":
            case "BigInteger":
            case "BigDecimal":
            case "AtomicLong":
            case "AtomicInger":
                m = build("return 0;");
                break;
            case "boolean":
                m = build("return false;");
                break;
            case "char":
            case "Character":
                m = build("return ' ';");
                break;
            case "void":
            case "": // constructor method
                m = build("");
                break;
            default:
                m = build("return null;");
                break;
        }
        return m;
    }

    public static String removeComment(String methodStr) {
        int commentLeft = StringUtils.kmpSearch(methodStr, leftFlag);
        int commentRight = StringUtils.kmpSearch(methodStr, rightFlag);
        if (commentLeft != -1 && commentRight != -1) {
            methodStr = methodStr.substring(rightFlag.length() + commentRight);
        }
        return methodStr;
    }

    public static String findNewHandlerCode(String codeInfo, int ed) {
        int deep = 0;
        StringBuilder sb = new StringBuilder();
        boolean find = false;
        final String classStr = "class";
        for (int i = ed; i >= classStr.length(); i--) {
            char c = codeInfo.charAt(i);
            if (c == '}') {
                deep++;
                find = true;
            } else if (c == '{') {
                deep--;
                find = true;
            }
            sb.append(c);
            if (codeInfo.startsWith(classStr, i)) {
                break;
            }
        }

        return sb.reverse().toString();
    }


    public static void main(String[] args) {
        String constructorMethod = "" +
                "private String handlerReturnType1(int[] arr,int b){}  " +
                "private String handlerReturnType1(int a){} " +
                "private int handlerReturnType2(int b){}" +
                "private void handlerReturnType3(List<String> a){} " +
                "private double handlerReturnType4(List<TreeNode> tree){}" +
                "private boolean handlerReturnType4(List<TreeNode> tree){}" +
                "private double handlerReturnType4(List<TreeNode> tree){}" +
                "private long handlerReturnType4(List<TreeNode> tree){}" +
                "private float handlerReturnType4(List<TreeNode> tree){}" +
                "private char handlerReturnType4(List<TreeNode> tree){}" +
                "private Double handlerReturnType4(List<TreeNode> tree){}" +
                "String handlerReturnType5(int[] arr,int b){}" +
                "String[] handlerReturnType5(int[] arr,int b){}" +
                "Map<String,String> handlerReturnType5(int[] arr,int b){}" +
                "List<Integer> handlerReturnType5(int[] arr,int b){}";
        // methodSignature = methodSignature.replace("{", "").replace("}","");

        String defaultMethod = "private String handlerReturnType1(int[] arr,int b){}";
        ParseCodeDefaultTemplate code = new ParseCodeDefaultTemplate("");
        // System.out.println(code.handlerReturnType(constructorMethod));
        System.out.println(code.handlerReturnType(defaultMethod));
    }
}
