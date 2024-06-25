package com.rnkrsoft.bopomofo4j.sandbox.v101;

/**
 * Created by rnkrsoft.com on 2019/9/19.
 */
class Vowels {
    // aeiouv
    static final char[][] VOWELS = {
            { 'a', '0' },
            { 'a', '1' },
            { 'a', '2' },
            { 'a', '3' },
            { 'a', '4' },
            { 'e', '0' },
            { 'e', '1' },
            { 'e', '2' },
            { 'e', '3' },
            { 'e', '4' },
            { 'i', '0' },
            { 'i', '1' },
            { 'i', '2' },
            { 'i', '3' },
            { 'i', '4' },
            { 'o', '0' },
            { 'o', '1' },
            { 'o', '2' },
            { 'o', '3' },
            { 'o', '4' },
            { 'u', '0' },
            { 'u', '1' },
            { 'u', '2' },
            { 'u', '3' },
            { 'u', '4' },
            { 'v', '0' },
            { 'v', '1' },
            { 'v', '2' },
            { 'v', '3' },
            { 'v', '4' },
    };
    static final String TONE_VOWELS = "aāáǎàeēéěèiīíǐìoōóǒòuūúǔùvǖǘǚǜ";

    /**
     * 给定一个拼音，输出音调和拼音字母
     * 
     * @param w 带音调拼音
     * @return 拼音在[0],音调数字在[1]
     */
    public static char[] parse(char w) {
        // 寻找在有声调声母中的位置
        int k;
        if ((k = TONE_VOWELS.indexOf(w)) > -1) {
            return new char[] { VOWELS[k][0], VOWELS[k][1] };
        } else {
            // 原样
            return null;
        }
    }
}
