package com.example.consumerTest;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DO {

    @Test
    public void test() throws FileNotFoundException {
        File file = new File("/Users/yicheng/belle/allcode/member-center/");

        List<FileNameDTO> strList = new ArrayList<>();
        statFile(file, strList);
        writeCsv(strList);
    }

    private void statFile(File file, List<FileNameDTO> strList) throws FileNotFoundException {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException(file + "，文件不存在！");
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles(new FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    return !pathname.getAbsolutePath().contains("target");
                }
            });

            for (File target : files) {
                statFile(target, strList);
            }
        } else {
            if (file.getName().endsWith(".js") || file.getName().endsWith(".jsp")) {


                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(file));
                    String tempString = null;
                    FileNameDTO fileNameDTO;
                    int line = 1;
                    // 一次读入一行，直到读入null为文件结束
                    while ((tempString = reader.readLine()) != null) {
                        // 显示行号
                        if (tempString.length() < 200
                                && (tempString.contains(".do?") || tempString.contains(".do\""))
                                && !tempString.contains("contextPath")
                                && (tempString.contains("+") && ! tempString.contains("do?"))) {
                            fileNameDTO = new FileNameDTO();
                            fileNameDTO.setCode(tempString);
                            fileNameDTO.setFileName(file.getAbsolutePath().replace("/Users/yicheng/belle/allcode/member-center/", ""));
                            fileNameDTO.setLineNum(line);
                            fileNameDTO.setProject(file.getAbsolutePath().split("/src")[0].replace("/Users/yicheng/belle/allcode/member-center/", ""));
                            strList.add(fileNameDTO);
                        }
                        line++;
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ignored) {
                        }
                    }
                }
            }
        }
    }

    private void writeCsv(List<FileNameDTO> fileNameDTOS) {
        try {

            File csv = new File("/Users/yicheng/belle/githubcode/springboot-demo/do.csv");//CSV文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
            bw.write("项目" + "," + "文件名" +
                    ","+ "代码行" + "," + "代码");
            for (FileNameDTO fileNameDTO : fileNameDTOS) {
                bw.newLine();

                bw.write(fileNameDTO.getProject() + "," + fileNameDTO.getFileName()
                        + "," + fileNameDTO.getLineNum() + "," + fileNameDTO.getCode());
            }
            bw.close();
        } catch (IOException e) {
            //捕获BufferedWriter对象关闭时的异常
            e.printStackTrace();
        }
    }
}
