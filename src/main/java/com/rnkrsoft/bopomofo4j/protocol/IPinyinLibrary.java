package com.rnkrsoft.bopomofo4j.protocol;

import java.util.List;
import java.util.Map;

/**
 * Created by rnkrsoft.com on 2019/9/19.
 */
public interface IPinyinLibrary {
    /**
     * 封装多音字
     */
    final class Polyphone {
        /**
         * 多音字词组
         */
        char[] words;
        /**
         * 多音字音调
         */
        String[] tones;
        /**
         * 偏移位置
         */
        int offset;
        /**
         * 词组长度
         */
        int length;

        public Polyphone(char[] words, String[] tones, int offset, int length) {
            this.words = words;
            this.tones = tones;
            this.offset = offset;
            this.length = length;
        }

        public char[] getWords() {
            return words;
        }

        public String[] getTones() {
            return tones;
        }

        public int getOffset() {
            return offset;
        }

        public int getLength() {
            return length;
        }
    }
    /**
     * 将汉字转换为拼音数组
     * @param w 汉字
     * @return 拼音数组
     */
    String[] getPinyins(char w);

    public String getChar(String py);

    public Map<String, List<String>> initChineseChars();

    public List<String> getChineseChars(String w);

    /**
     * 获取多音字词，返回时返回替换起始位置和结束位置
     * @param words 句子
     * @param current 当前字
     * @param pos 当前汉字的位置
     * @param lastPolyphoneIndex 最近一次多音字词组处理索引
     * @return 多音字词组
     */
    Polyphone getPolyphoneWord(String words, char current, int pos, int lastPolyphoneIndex);

    /**
     * 将繁体转换为简体
     * @param w 繁体
     * @return 简体
     */
    String chs(char w);
    /**
     * 将简体转换为繁体
     * @param w 简体
     * @return 繁体
     */
    String cht(char w);
}
