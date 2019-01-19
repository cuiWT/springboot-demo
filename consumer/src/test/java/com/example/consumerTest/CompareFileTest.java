package com.example.consumerTest;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class CompareFileTest {

    @Test
    public void test() {
        String test = "/Users/yicheng/Downloads/language/python/python-code/git/git/miu-intemarket/webinterface/activity-backend/src/test/java/cn/wonhigh/o2o/activity/AppTest.javaAppTest.java";
        String[] wonhigh = test.split("cn/wonhigh/o2o");
        System.out.print(wonhigh[0]);
    }

    @Test
    public void count() throws FileNotFoundException {

        File file = new File("/Users/yicheng/Downloads/language/python/python-code/git/git");
        // 根据用户输入的文件名和目录执行代码量统计
        Map<String, String> javaMap = new HashMap<>();
        Map<String, String> configMap = new HashMap<>();
        Map<String, String> sqlMap = new HashMap<>();
        Map<String, String> otherMap = new HashMap<>();
        codeStat(file, javaMap, configMap, sqlMap, otherMap);

        //-----------------------------------------------------

        File newFile = new File("/Users/yicheng/Downloads/language/python/python-code/git/git");
        // 根据用户输入的文件名和目录执行代码量统计
        Map<String, String> newJavaMap = new HashMap<>();
        Map<String, String> newConfigMap = new HashMap<>();
        Map<String, String> newSqlMap = new HashMap<>();
        Map<String, String> newOtherMap = new HashMap<>();
        codeStat(file, newJavaMap, newConfigMap, newSqlMap, newOtherMap);

        //------------------------------------------------------

        Set<String> javaKey = javaMap.keySet();
        Set<String> configKey = configMap.keySet();
        Set<String> sqlKey = sqlMap.keySet();
        Set<String> otherKey = otherMap.keySet();
        Set<String> newJavaKey = newJavaMap.keySet();
        Set<String> newConfigKey = newConfigMap.keySet();
        Set<String> newSqlKey = newSqlMap.keySet();
        Set<String> newOtherKey = newOtherMap.keySet();

        System.out.println("－－－－－－－－－－统计结果－－－－－－－－－\n");
        System.out.print("java:\n" );
        System.out.print(newJavaKey.removeAll(javaKey));
        System.out.print("config:\n");
        System.out.print(newConfigKey.removeAll(configKey));
        System.out.print("sql:\n");
        System.out.print(newSqlKey.removeAll(sqlKey));
        System.out.print("other:\n");
        System.out.print(newOtherKey.removeAll(otherKey));
    }

    /**
     * 若需要统计返回 true
     * @param pathname
     * @return
     */
    private Boolean needScan(File pathname) {
        return !pathname.getName().endsWith(".iml")
                && !pathname.getName().endsWith(".jupiter")
                && !pathname.getName().contains(".gitignore")
                && !pathname.getName().contains(".git")
                && !pathname.getName().endsWith(".review");
    }

    private void codeStat(File file, Map<String, String> javaMap,
                          Map<String, String> configMap, Map<String, String> sqlMap,
                          Map<String, String> otherMap) throws FileNotFoundException {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException(file + "，文件不存在！");
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles(new FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    return needScan(pathname);
                }
            });

            for (File target : files) {
                codeStat(target, javaMap, configMap, sqlMap, otherMap);
            }
        } else {
            String fileName = file.getAbsolutePath();
            String[] wonhigh = fileName.split("cn/wonhigh/o2o/");
            String shortName =  wonhigh.length > 1
                    ? wonhigh[1]
                    : fileName.replaceFirst("/Users/yicheng/Downloads/language/python/python-code/git/git/", "")
                    .replace("member-integral-union/", "")
                    .replace("miu-base/", "")
                    .replace("miu-cap/", "")
                    .replace("miu-common-utils/","")
                    .replace("miu-intemarket/","")
                    .replace("miu-mshop/", "")
                    .replace("miu-tag/", "");

            if (fileName.endsWith(".java")) {
                javaMap.put(shortName, fileName);
            } else if (fileName.endsWith(".sql")) {
                sqlMap.put(shortName, fileName);
            } else if (fileName.endsWith(".xml") || fileName.endsWith(".properties")) {
                configMap.put(shortName, fileName);
            } else {
                otherMap.put(shortName, fileName);
            }
        }
    }

}
