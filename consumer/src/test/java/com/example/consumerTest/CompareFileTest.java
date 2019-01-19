package com.example.consumerTest;

import javafx.util.Pair;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
        List<FileNameDTO> javaDtoList = new ArrayList<>();
        List<FileNameDTO> configDtoList = new ArrayList<>();
        List<FileNameDTO> sqlDtoList = new ArrayList<>();
        List<FileNameDTO> otherDtoList = new ArrayList<>();
        codeStat(file, javaDtoList, configDtoList, sqlDtoList, otherDtoList);

        //-----------------------------------------------------

        File newFile = new File("/Users/yicheng/Downloads/language/python/python-code/git/git");
        // 根据用户输入的文件名和目录执行代码量统计
        List<FileNameDTO> newJavaDtoList = new ArrayList<>();
        List<FileNameDTO> newConfigDtoList = new ArrayList<>();
        List<FileNameDTO> newSqlDtoList = new ArrayList<>();
        List<FileNameDTO> newOtherDtoList = new ArrayList<>();
        codeStat(newFile, newJavaDtoList, newConfigDtoList, newSqlDtoList, newOtherDtoList);

        //------------------------------------------------------

        List<String> javaKey = javaDtoList.stream().map(FileNameDTO::getShotName).collect(Collectors.toList());
        List<String> configKey = configDtoList.stream().map(FileNameDTO::getShotName).collect(Collectors.toList());
        List<String> sqlKey = sqlDtoList.stream().map(FileNameDTO::getShotName).collect(Collectors.toList());
        List<String> otherKey = otherDtoList.stream().map(FileNameDTO::getShotName).collect(Collectors.toList());

        List<String> newJavaKey = newJavaDtoList.stream().map(FileNameDTO::getShotName).collect(Collectors.toList());
        List<String> newConfigKey = newConfigDtoList.stream().map(FileNameDTO::getShotName).collect(Collectors.toList());
        List<String> newSqlKey = newSqlDtoList.stream().map(FileNameDTO::getShotName).collect(Collectors.toList());
        List<String> newOtherKey = newOtherDtoList.stream().map(FileNameDTO::getShotName).collect(Collectors.toList());

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
                && !pathname.getName().endsWith(".gitignore")
                && !pathname.getName().contains(".git")
                && !pathname.getName().endsWith(".review");
    }

    private void codeStat(File file, List<FileNameDTO> javaDtoList,
                          List<FileNameDTO> configDtoList, List<FileNameDTO> sqlDtoList,
                          List<FileNameDTO> otherDtoList) throws FileNotFoundException {
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
                codeStat(target, javaDtoList, configDtoList, sqlDtoList, otherDtoList);
            }
        } else {
            String fileName = file.getAbsolutePath();
            String fileMd5 = Md5Util.getMd5ByFile(file);
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
            Pair<String, String> pair = new Pair<>(fileName, fileMd5);
            FileNameDTO fileNameDTO = new FileNameDTO();
            fileNameDTO.setFullName(fileName);
            fileNameDTO.setShotName(shortName);
            fileNameDTO.setMd5(fileMd5);
            if (fileName.endsWith(".java")) {
                javaDtoList.add(fileNameDTO);
            } else if (fileName.endsWith(".sql")) {
                sqlDtoList.add(fileNameDTO);
            } else if (fileName.endsWith(".xml") || fileName.endsWith(".properties")) {
                configDtoList.add(fileNameDTO);
            } else {
                otherDtoList.add(fileNameDTO);
            }
        }
    }

    private void writeCsv(List<ResultDTO> resultDTOList) {
        try {
            if (CollectionUtils.isEmpty(resultDTOList)) {
                return;
            }
            File csv = new File("/Users/yicheng/belle/githubcode/springboot-demo/result.csv");//CSV文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
            //新增一行数据
            bw.write("类别 1、新项目中无对应文件。2、新项目中文件变更" + "," + "旧文件路径名" + "," + "新文件路径名");
            for (ResultDTO resultDTO : resultDTOList) {
                bw.newLine();
                bw.write(resultDTO.getClassify() + "," + resultDTO.getOldFileName() + "," + resultDTO.getNewFileName());
            }
            bw.close();
        } catch (FileNotFoundException e) {
            //捕获File对象生成时的异常
            e.printStackTrace();
        } catch (IOException e) {
            //捕获BufferedWriter对象关闭时的异常
            e.printStackTrace();
        }
    }


}
