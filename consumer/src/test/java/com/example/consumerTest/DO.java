package com.example.consumerTest;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DO {

    @Test
    public void test() {
        File file = new File("/Users/yicheng/belle/allcode/member-center/do.log");
        BufferedReader reader = null;
        List<String> strList = new ArrayList<>();
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                if (tempString.length() < 200 && (tempString.contains("+") && ! tempString.contains("do?")) && !tempString.contains("contextPath")) {
                    strList.add(tempString);
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
                } catch (IOException e1) {
                }
            }
        }
        writeCsv(strList);
    }

    private void writeCsv(List<String> strList) {
        try {

            File csv = new File("/Users/yicheng/belle/githubcode/springboot-demo/do.log");//CSV文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
            //新增一行数据
            bw.write("类别 1、新项目中无对应文件。2、新项目中文件变更。3、重复文件" + "," + "项目" +
                    ","+ "旧文件路径名" + "," + "新文件路径名");
            for (String str : strList) {
                bw.newLine();
                bw.write(str);
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
