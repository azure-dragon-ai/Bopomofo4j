package com.rnkrsoft.bopomofo4j.sandbox.v101;

import com.rnkrsoft.bopomofo4j.protocol.IPinyinLibrary;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rnkrsoft.com on 2019/9/19.
 */
public class LocalPinyinLibrary implements IPinyinLibrary {
    static final String PINYIN_SEPARATOR = ","; // 拼音分隔符
    final static LocalPinyinLibrary library = new LocalPinyinLibrary().init();
    final static String PINYINS_FILE_NAME = "META-INF/resources/bopomofo/libs/v101/pinyins.json";
    final static String PINYIN2CHS_FILE_NAME = "META-INF/resources/bopomofo/libs/v101/pinyin2chs.json";
    final static String POLYPHONES_FILE_NAME = "META-INF/resources/bopomofo/libs/v101/polyphones.json";
    final static String CHT2CHS_FILE_NAME = "META-INF/resources/bopomofo/libs/v101/cht2chs.json";
    final static String CHS2CHT_FILE_NAME = "META-INF/resources/bopomofo/libs/v101/chs2cht.json";
    Map<String, String> pinyins;
    Map<String, List<String>> pinyin2chs;
    Map<String, String> polyphones;
    Map<String, String> cht2chs;
    Map<String, String> chs2cht;

    public LocalPinyinLibrary init() {
        // 加载本地的拼音库
        {
            URL url = this.getClass().getClassLoader().getResource(PINYINS_FILE_NAME);
            try {
                this.pinyins = JSON.parse(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        {
            URL url = this.getClass().getClassLoader().getResource(PINYIN2CHS_FILE_NAME);
            try {
                this.pinyin2chs = JSON.parseList(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        {
            URL url = this.getClass().getClassLoader().getResource(POLYPHONES_FILE_NAME);
            try {
                this.polyphones = JSON.parse(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        {
            URL url = this.getClass().getClassLoader().getResource(CHT2CHS_FILE_NAME);
            try {
                this.cht2chs = JSON.parse(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        {
            URL url = this.getClass().getClassLoader().getResource(CHS2CHT_FILE_NAME);
            try {
                this.chs2cht = JSON.parse(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public String[] getPinyins(char w) {
        String py = this.pinyins.get(String.valueOf(w));
        if (py == null) {
            return new String[] {};
        } else {
            return py.split(PINYIN_SEPARATOR);
        }
    }

    public String getChar(String py) {
        String py1 = "";
        char[] chars = py.toCharArray();
        for (int idx = 0; idx < chars.length; idx++) {
            char w = chars[idx];
            //寻找在有声调声母中的位置
            char[] cs = Vowels.parse(w);
            py1 += (cs == null ? w : cs[0]);
        }
        return py1;
    }

    public Map<String, List<String>> initChineseChars() {
        String key = "";
        String value = "";
        String k = "";
        String[] pinyins;
        Map<String, List<String>> pinyin2chs = new HashMap<>();
        List<String> chs;
        for (Map.Entry <String, String> entry : this.pinyins.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            pinyins = value.split(PINYIN_SEPARATOR);
            for(int i = 0 ; i < pinyins.length ; i++) {
                k = this.getChar(pinyins[i]);
                if(pinyin2chs.containsKey(k)) {
                    chs = pinyin2chs.get(k);
                } else {
                    chs = new ArrayList<>();
                    // pinyin2chs.put(k, chs);
                }
                chs.add(key);
                pinyin2chs.put(k, chs);
            }
        }
        return pinyin2chs;
    }

    public List<String> getChineseChars(String w) {
        List<String> chs = this.pinyin2chs.get(w);
        if (chs == null) {
            return new ArrayList<String>();
        } else {
            return chs;
        }
    }

    public Polyphone getPolyphoneWord(String words, char current, int pos, int lastPolyphoneIndex) {
        final List<Polyphone> results = new ArrayList<Polyphone>();
        int maxMatchLen = 0;
        for (String w : this.polyphones.keySet()) {
            int len = w.length();
            int beginPos = pos - len;
            beginPos = Math.max(lastPolyphoneIndex, beginPos);
            int endPos = Math.min(pos + len, words.length());
            String temp = words.substring(beginPos, endPos);
            if (temp.indexOf(w) > -1) {
                if (len > maxMatchLen) {
                    maxMatchLen = len;
                }
                // 当前汉字在多音字词组的偏移位置，用于修正词组的替换
                int offset = w.indexOf(current);
                Polyphone data = new Polyphone(w.toCharArray(), this.polyphones.get(w).split(PINYIN_SEPARATOR), offset,
                        len);
                results.add(data);
            }
        }
        if (results.size() == 1) {
            return results.get(0);
        } else if (results.size() > 1) {// 如果存在多个匹配的多音字词组，以最大匹配项为最佳答案,例如词库中有'中国人'和'中国',最理想的答案应该是最大匹配
            for (int i = 0; i < results.size(); i++) {
                Polyphone value = results.get(i);
                if (value.getLength() == maxMatchLen) {
                    return value;
                }
            }
        }
        return null;
    }

    @Override
    public String chs(char w) {
        String _new = cht2chs.get(String.valueOf(w));
        return _new;
    }

    @Override
    public String cht(char w) {
        String _new = chs2cht.get(String.valueOf(w));
        return _new;
    }

    /**
     * 获取拼音库
     *
     * @return 拼音库
     */
    public static LocalPinyinLibrary getPinyinLibrary() {
        return library;
    }
}
