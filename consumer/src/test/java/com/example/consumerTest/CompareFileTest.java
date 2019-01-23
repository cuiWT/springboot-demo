package com.example.consumerTest;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompareFileTest {

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

        File newFile = new File("/Users/yicheng/belle/allcode/member-center");
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

        Map<String, List<FileNameDTO>> javaMd5Map = javaDtoList.stream()
                .collect(Collectors.groupingBy(FileNameDTO::getShotName));

        Map<String, List<FileNameDTO>> newJavaMd5Map = newJavaDtoList.stream()
                .collect(Collectors.groupingBy(FileNameDTO::getShotName));

        Map<String, List<FileNameDTO>> configMd5Map = configDtoList.stream()
                .collect(Collectors.groupingBy(FileNameDTO::getShotName));

        Map<String, List<FileNameDTO>> newConfigMd5Map = newConfigDtoList.stream()
                .collect(Collectors.groupingBy(FileNameDTO::getShotName));

        Map<String, List<FileNameDTO>> sqlMd5Map = sqlDtoList.stream()
                .collect(Collectors.groupingBy(FileNameDTO::getShotName));

        Map<String, List<FileNameDTO>> newSqlMd5Map = newSqlDtoList.stream()
                .collect(Collectors.groupingBy(FileNameDTO::getShotName));

        Map<String, List<FileNameDTO>> otherMd5Map = otherDtoList.stream()
                .collect(Collectors.groupingBy(FileNameDTO::getShotName));

        Map<String, List<FileNameDTO>> newOtherMd5Map = newOtherDtoList.stream()
                .collect(Collectors.groupingBy(FileNameDTO::getShotName));

        List<ResultDTO> resultDTOList = new ArrayList<>();
        result(resultDTOList,javaKey,newJavaKey,javaMd5Map,newJavaMd5Map);
        result(resultDTOList,configKey,newConfigKey,configMd5Map,newConfigMd5Map);
        result(resultDTOList,sqlKey,newSqlKey,sqlMd5Map,newSqlMd5Map);
        result(resultDTOList,otherKey,newOtherKey,otherMd5Map,newOtherMd5Map);

        writeCsv(resultDTOList);

        System.out.println("－－－－－－－－－－统计结果－－－－－－－－－\n");
    }

    private void result(List<ResultDTO> resultDTOList, List<String> javaKey,
                        List<String> newJavaKey,
                        Map<String, List<FileNameDTO>> javaMd5Map,
                        Map<String, List<FileNameDTO>> newJavaMd5Map) {
        List<String> newNotHaveJavaList = new ArrayList<>(javaKey);
        newNotHaveJavaList.removeAll(newJavaKey);
        ResultDTO resultDTO;
        for (String shortName : newNotHaveJavaList) {
            resultDTO = new ResultDTO();
            String fullName = javaMd5Map.get(shortName).get(0).getFullName();
            resultDTO.setOldFileName(fullName);
            resultDTO.setProject(fullName.replace(shortName, "")
                    .replace("cn/wonhigh/o2o/", "")
                    .replace("src/test/java/", "")
                    .replace("src/main/java/", ""));
            resultDTO.setClassify(1);
            resultDTOList.add(resultDTO);
        }

        List<String> bothHaveFileList = new ArrayList<>(javaKey);
        bothHaveFileList.retainAll(newJavaKey);
        for (String shortName : bothHaveFileList) {
            List<FileNameDTO> oldList = javaMd5Map.get(shortName);
            List<FileNameDTO> newList = newJavaMd5Map.get(shortName);
            if (oldList.size() > 1 || newList.size() > 1) {
                resultDTO = new ResultDTO();
                resultDTO.setClassify(3);
                String fullName = oldList.get(0).getFullName();
                resultDTO.setOldFileName(fullName);
                resultDTO.setProject(fullName.replace(shortName, "")
                        .replace("cn/wonhigh/o2o/", "")
                        .replace("src/test/java/", "")
                        .replace("src/main/java/", ""));
                resultDTO.setOldFileName(String.join("分割符", oldList.stream().map(fileNameDTO -> fileNameDTO.getFullName()).collect(Collectors.toList())));
                resultDTO.setNewFileName(String.join("分隔符", newList.stream().map(fileNameDTO -> fileNameDTO.getFullName()).collect(Collectors.toList())));
                resultDTO.setProject(fullName.replace(shortName, ""));
                resultDTOList.add(resultDTO);
                continue;
            }

            if (!oldList.get(0).getMd5().equals(newList.get(0).getMd5())) {
                resultDTO = new ResultDTO();
                String fullName = oldList.get(0).getFullName();

                resultDTO.setOldFileName(fullName);
                resultDTO.setNewFileName(newList.get(0).getFullName());
                resultDTO.setClassify(2);
                resultDTO.setProject(fullName.replace(shortName, "")
                        .replace("cn/wonhigh/o2o/", "")
                        .replace("src/test/java/", "")
                        .replace("src/main/java/", ""));
                resultDTOList.add(resultDTO);
            }
        }

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
                && !pathname.getName().contains(".idea")
                && !pathname.getName().contains(".setting")
                && !pathname.getName().endsWith(".review")
                && !pathname.getName().endsWith("pom.xml.bak")
                && !pathname.getName().contains("target")
                && !pathname.getName().endsWith("jar")
                && !pathname.getName().endsWith("application.properties")
                && !pathname.getName().contains("assembly")
                && !pathname.getName().contains("staticcommon")
                && !pathname.getName().contains("mycat")
                && !pathname.getName().contains("DBscript");
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
            String fileName = file.getAbsolutePath().replace("/Users/yicheng/Downloads/language/python/python-code/git/git/", "")
                    .replace("/Users/yicheng/belle/allcode/member-center", "");
            if (fileName.contains("zTreeObj.getSelectedNodes.html")) {
                System.out.print("");
            }
            String fileMd5 = Md5Util.getMd5ByFile(file);
            String[] wonhigh = fileName.split("cn/wonhigh/o2o/");
            String shortName =  wonhigh.length > 1
                    ? wonhigh[1]
                    : fileName.replace("/Users/yicheng/Downloads/language/python/python-code/git/git/", "")
                    .replace("member-integral-union/", "")
                    .replace("miu-base/", "")
                    .replace("miu-cap/", "")
                    .replace("miu-common-utils/","")
                    .replace("miu-intemarket/","")
                    .replace("miu-mshop/", "")
                    .replace("miu-tag/", "")
                    .replace("crm-admin-server/","")
                    .replace("member-api-parent/","")
                    .replace("member-api-server/","")
                    .replace("member-base/","")
                    .replace("member-center/","")
                    .replace("member-center-server/","")
                    .replace("member-common-server/","")
                    .replace("member-label/","")
                    .replace("member-mall/","")
                    .replace("member-mall-server/","")
                    .replace("member-marketing/","")
                    .replace("member-message/","")
                    .replace("member-outside/","")
                    .replace("member-wecaht-server/","")
                    .replace("member-wechat/","")
                    .replace("server/wechat-server", "")
                    .replace("server/wcap-server", "")
                    .replace("server/store-server", "")
                    .replace("server/pointshop-server", "")
                    .replace("server/platform-server", "")
                    .replace("webinterface/member-backend","")
                    .replace("server/frontcommon-server", "")
                    .replace("webinterface/analyze-backend", "")
                    .replace("server/intemarket-server", "")
                    .replace("webinterface/intemarket-backend", "")
                    .replace("server/portal-server", "")
                    .replace("server/customback-server", "")
                    .replace("server/member-server","")
                    .replace("server/extension-server", "")
                    .replace("webinterface/evaluation-backend", "")
                    .replace("server/solr-server", "")
                    .replace("server/crm-server", "")
                    .replace("webinterface/activity-backend", "")
                    .replace("webinterface/activity-backend", "")
                    .replace("server/mmarket-server", "")
                    .replace("server/eye-server", "")
                    .replace("server/pointback-server", "")
                    .replace("server/log-server", "")
                    .replace("server/file-server", "");


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
            bw.write("类别 1、新项目中无对应文件。2、新项目中文件变更。3、重复文件" + "," + "项目" +
                    ","+ "旧文件路径名" + "," + "新文件路径名");
            for (ResultDTO resultDTO : resultDTOList) {
                bw.newLine();
                bw.write(resultDTO.getClassify() + "," + resultDTO.getProject() + "," +
                        resultDTO.getOldFileName() + "," + resultDTO.getNewFileName());
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

    @Test
    public void contain() {
        List<String> list1 = new ArrayList<String>();
        list1.add("A");
        list1.add("activity/AppTest.java");
        list1.add("C");

        List<String> list2 = new ArrayList<String>();
        list2.add("C");
        list2.add("activity/AppTest.java");
        list2.add("D");
//        // 并集
//        list1.addAll(list2);
//        // 去重复并集
//        list2.removeAll(list1);
//        list1.addAll(list2);
//        // 交集
        list1.retainAll(list2);
        // 差集
//        list1.removeAll(list2);
        System.out.print("\n");
    }

    @Test
    public void testMd5() {

        File file = new File("/Users/yicheng/belle/allcode/member-center/member-center/member-center-provider/src/main/java/cn/wonhigh/o2o/center/data/converge/provider/service/order/OrderDataServiceImpl.java");
        File demo = new File("/Users/yicheng/Downloads/language/python/python-code/git/git/miu-tag/center/data-converge-center/data-converge-provider/src/main/java/cn/wonhigh/o2o/center/data/converge/provider/service/order/OrderDataServiceImpl.java");
        String fileMd5 = Md5Util.getMd5ByFile(file);
        String demoMd5 = Md5Util.getMd5ByFile(demo);
        if (fileMd5.equals(demoMd5)) {
            System.out.print("true");
        }
    }


    @Test
    public void test() {
        String test = "/Users/yicheng/Downloads/language/python/python-code/git/git/miu-intemarket/webinterface/activity-backend/src/test/java/cn/wonhigh/o2o/activity/AppTest.javaAppTest.java";
        String[] wonhigh = test.split("cn/wonhigh/o2o");
        System.out.print(wonhigh[0]);
    }

}
