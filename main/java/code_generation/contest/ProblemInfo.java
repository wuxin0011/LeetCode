package code_generation.contest;

import code_generation.utils.IoUtil;
import code_generation.utils.ReflectUtils;
import code_generation.utils.StringUtils;

import java.io.File;

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

    private String dirPrefix;


    private void updateDir(String javaFile, String txtFile) {


        if (javaFile.endsWith(".txt")) {
            javaFile = javaFile.replace(".txt", ".java");
        }
        if (!javaFile.endsWith(".java")) {
            javaFile = javaFile + ".java";
        }

        if (txtFile.endsWith(".java")) {
            txtFile = txtFile.replace(".java", ".txt");
        }
        if (!txtFile.endsWith(".txt")) {
            txtFile = txtFile + ".txt";
        }


        // update java class Name
        if (classTemplate != null) {
            String tempJavaName = javaFile.replace(".java", "");
            if (tempJavaName.contains("\\")) {
                String[] split = tempJavaName.split("\\\\");
                tempJavaName = split[split.length - 1]; // last as className
            }
            if (StringUtils.isEmpty(classTemplate.className) || !tempJavaName.equals(classTemplate.className)) {
                classTemplate.buildClassName(tempJavaName);
            }
        }


        this.javaFile = IoUtil.wrapperAbsolutePath(aClass, StringUtils.isEmpty(dirPrefix) ? javaFile : (dirPrefix + File.separator + javaFile));
        this.txtFile = IoUtil.wrapperAbsolutePath(aClass, StringUtils.isEmpty(dirPrefix) ? txtFile : (dirPrefix + File.separator + txtFile));


        // update package info
        if (StringUtils.isEmpty(classTemplate.packageInfo)) {
            classTemplate.buildPackageInfo(ReflectUtils.getPackageInfo(this.javaFile));
        }
    }


    public ProblemInfo(String javaFile, String txtFile, String dirPrefix, String testCase, ClassTemplate classTemplate, Class<?> aClass) {
        this.aClass = aClass;
        this.dirPrefix = dirPrefix;
        this.testCase = testCase;
        this.classTemplate = classTemplate;
        updateDir(javaFile, txtFile);
    }

    public ProblemInfo() {
    }

    public ProblemInfo(String javaFile, String txtFile, String testCase, ClassTemplate classTemplate, Class<?> aClass) {
        this(javaFile, txtFile, "", testCase, classTemplate, aClass);
    }


    public void setJavaFile(String javaFile) {
        updateDir(javaFile, txtFile);
    }

    public void setTxtFile(String txtFile) {
        updateDir(javaFile, txtFile);
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

    public String getDirPrefix() {
        return dirPrefix;
    }

    public void setDirPrefix(String dirPrefix) {
        this.dirPrefix = dirPrefix;
        updateDir(javaFile, txtFile);
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
        System.out.println("\n====================================java file=========================\n");
        System.out.println(javaFile);
        System.out.println("\n====================================txt file=========================\n");
        System.out.println(txtFile);
        System.out.println("\n====================================template=========================\n");
        System.out.println(classTemplate);
        System.out.println("\n====================================template=========================\n");
        System.out.println(testCase);
        return "";
    }
}
