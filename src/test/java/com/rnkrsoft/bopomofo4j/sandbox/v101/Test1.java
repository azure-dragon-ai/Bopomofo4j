package com.rnkrsoft.bopomofo4j.sandbox.v101;

import com.rnkrsoft.bopomofo4j.Bopomofo4j;
import com.rnkrsoft.bopomofo4j.protocol.IPinyinLibrary;
import com.rnkrsoft.bopomofo4j.sandbox.v101.Vowels;
import com.rnkrsoft.bopomofo4j.utils.JacksonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by rnkrsoft.com on 2019/9/19.
 */
public class Test1 {

    @Test
    public void testPinyin() throws Exception {
       //汉语句子->无音调拼音
        String v3 = Bopomofo4j.pinyin("汉语拼音",2, false, false, " ");
        System.out.println(v3);
    }

    @Test
    public void testGetChineseChars() throws Exception {
       //汉语句子->无音调拼音 骨髓
        String v3 = Bopomofo4j.pinyin("24小时监视",2, false, false, " ");
        System.out.println(v3);
        String[] pinyins = v3.split(" ");
        Map<String, String[]> map = new HashMap<>();
        String chineseCharsStr;
        String[] chineseChars;
        String key = "";
        for(int i = 0 ; i < pinyins.length ; i++) {
            key = "pinyin" + i;
            chineseCharsStr = Bopomofo4j.getChineseChars(pinyins[i], " ");
            System.out.println("---------------");
            System.out.println(chineseCharsStr);
            chineseChars = chineseCharsStr.split(" ");
            System.out.println(chineseChars.length);
            map.put(key, chineseChars);
        }
        List<String> list = new ArrayList<>();
        key = "pinyin" + 0;
        chineseChars = map.get(key);
        for(int i = 0 ; i < chineseChars.length ; i++) {
            list.add(chineseChars[i]);
        }
        for(int i = 1 ; i < pinyins.length ; i++) {
            key = "pinyin" + i;
            list = this.make(list, map.get(key));
        }
        
        for(int i = 0 ; i < list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }

    public List<String> make(List<String> list, String[] chars) {
        List<String> newList = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++) {
            for(int j = 0 ; j < chars.length ; j++) {
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