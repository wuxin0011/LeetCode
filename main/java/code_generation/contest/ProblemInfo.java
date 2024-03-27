package code_generation.contest;

import code_generation.utils.IoUtil;

import java.util.StringJoiner;

/**
 * @author: wuxin0011
 * @Description:
 */
public class ProblemInfo {

    private String javaFile;
    private String txtFile;
    private String testCase;
    private ClassTemplate classTemplate;
    private Class<?> aClass;

    public ProblemInfo(String javaFile, String txtFile, String testCase, ClassTemplate classTemplate, Class<?> aClass) {
        this.aClass = aClass;
        this.javaFile = IoUtil.wrapperAbsolutePath(aClass, javaFile);
        this.txtFile = IoUtil.wrapperAbsolutePath(aClass, txtFile);
        this.testCase = testCase;
        this.classTemplate = classTemplate;
    }


    public void setJavaFile(String javaFile) {
        this.javaFile = IoUtil.wrapperAbsolutePath(aClass, javaFile);
    }

    public void setTxtFile(String txtFile) {
        this.txtFile = IoUtil.wrapperAbsolutePath(aClass, txtFile);
    }

    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

    public void setClassTemplate(ClassTemplate classTemplate) {
        this.classTemplate = classTemplate;
    }

    public void setaClass(Class<?> aClass) {
        this.aClass = aClass;
    }

    public String getJavaFile() {
        return javaFile;
    }

    public String getTxtFile() {
        return txtFile;
    }

    public String getTestCase() {
        return testCase;
    }

    public ClassTemplate getClassTemplate() {
        return classTemplate;
    }

    public Class<?> getaClass() {
        return aClass;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", ProblemInfo.class.getSimpleName() + "[", "]")
                .add("javaFile='" + javaFile + "'")
                .add("txtFile='" + txtFile + "'")
                .add("testCase='" + testCase + "'")
                .add("classTemplate=" + ClassTemplate.getTemplate(classTemplate))
                .add("aClass=" + aClass)
                .toString();
    }
}
