package com.rnkrsoft.bopomofo4j.sandbox.v101;

import com.rnkrsoft.bopomofo4j.Bopomofo4j;
import com.rnkrsoft.bopomofo4j.protocol.IPinyinLibrary;
import com.rnkrsoft.bopomofo4j.sandbox.v101.Vowels;
import com.rnkrsoft.bopomofo4j.utils.JacksonUtil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by rnkrsoft.com on 2019/9/19.
 */
public class Test1 {

    @Test
    public void testPinyin() throws Exception {
        // 汉语句子->无音调拼音
        String v3 = Bopomofo4j.pinyin("汉语拼音", 2, false, false, " ");
        System.out.println(v3);
    }

    @Test
    public void testGetChineseChars() throws Exception {
        // 大熊猫保护红线
        String path = "docs/block"; // 要遍历的路径
        File file = new File(path); // 获取其file对象
        File[] fs = file.listFiles(); // 遍历path下的文件和目录，放在File数组中
        String fileName;
        for (File f : fs) { // 遍历File[]数组
            if (!f.isDirectory()) {
                fileName = path + "/" + f.getName();

                try (Scanner sc = new Scanner(new FileReader(fileName))) {
                    while (sc.hasNextLine()) { // 按行读取字符串
                        String line = sc.nextLine();
                        if(!line.equals("")) {
                            this.getChineseChars(line);
                        }
                    }
                }
            }       
        }
    }

    public void getChineseChars(String words) throws Exception {
        // 汉语句子->无音调拼音
        String v3 = Bopomofo4j.pinyin(words, 2, false, false, " ");
        System.out.println(v3);
        String[] pinyins = v3.split(" ");
        Map<String, String[]> map = new HashMap<>();
        String chineseCharsStr;
        String[] chineseChars;
        String key = "";
        long num = 1;
        for (int i = 0; i < pinyins.length; i++) {
            key = "pinyin" + i;
            chineseCharsStr = Bopomofo4j.getChineseChars(pinyins[i], " ");
            // System.out.println("---------------");
            // System.out.println(chineseCharsStr);
            chineseChars = chineseCharsStr.split(" ");
            num = num * chineseChars.length;
            // System.out.println(chineseChars.length);
            map.put(key, chineseChars);
        }
        System.out.println(num);
        List<String> list = new ArrayList<>();
        key = "pinyin" + 0;
        chineseChars = map.get(key);
        int min = Math.round(400 / (pinyins.length * pinyins.length));
        int len = Math.min(min, chineseChars.length);
        for (int i = 0; i < len; i++) {
            list.add(chineseChars[i]);
        }
        for (int i = 1; i < pinyins.length; i++) {
            key = "pinyin" + i;
            list = this.make(list, map.get(key), min);
        }

        String filePath = "docs/" + words + ".txt";
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileWriter fileWritter = new FileWriter(file, true);
        for (int i = 0; i < list.size(); i++) {
            // System.out.println(list.get(i));
            fileWritter.write(list.get(i) + "\n");
        }
        fileWritter.close();
    }

    public List<String> make(List<String> list, String[] chars, int min) {
        List<String> newList = new ArrayList<>();
        int len = Math.min(min, chars.length);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < len; j++) {
                newList.add(list.get(i) + chars[j]);
            }
        }
        return newList;
    }

    @Test
    public void testChineseChar() throws Exception {
        IPinyinLibrary pinyinLibrary = LocalPinyinLibrary.getPinyinLibrary();
        Map<String, List<String>> chieseChars = pinyinLibrary.initChineseChars();
        System.out.println(JacksonUtil.toJson(chieseChars));
    }
}