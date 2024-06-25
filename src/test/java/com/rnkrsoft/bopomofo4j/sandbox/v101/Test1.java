package com.rnkrsoft.bopomofo4j.sandbox.v101;

import com.rnkrsoft.bopomofo4j.Bopomofo4j;
import com.rnkrsoft.bopomofo4j.protocol.IPinyinLibrary;
import com.rnkrsoft.bopomofo4j.sandbox.v101.Vowels;
import com.rnkrsoft.bopomofo4j.utils.JacksonUtil;

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
       //汉语句子->无音调拼音
        String v3 = Bopomofo4j.getChineseChars("nüe", " ");
        System.out.println(v3);
    }

    @Test
    public void testChineseChar() throws Exception {
       IPinyinLibrary pinyinLibrary = LocalPinyinLibrary.getPinyinLibrary();
       Map<String, List<String>> chieseChars = pinyinLibrary.initChineseChars();
       System.out.println(JacksonUtil.toJson(chieseChars));
    }
}