package com.rnkrsoft.bopomofo4j.sandbox.v101;

import com.rnkrsoft.bopomofo4j.ToneType;
import com.rnkrsoft.bopomofo4j.sandbox.v101.LocalKernel;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by rnkrsoft.com on 2019/9/19.
 */
public class LocalKernelTest {

    @Test
    public void testPinyin() throws Exception {
        Assert.assertEquals("I am chinese!1234", new LocalKernel().pinyin("I am chinese!1234", ToneType.WITH_NUMBER_TONE.getCode(), null, null, null));
        Assert.assertEquals("wo3 men0 shi4 huan0 nan0 yu3 gong0 de0", new LocalKernel().pinyin("我们是患难与共的", ToneType.WITH_NUMBER_TONE.getCode(), null, null, null));
        Assert.assertEquals("mu4 di4 di4", new LocalKernel().pinyin("目的地", ToneType.WITH_NUMBER_TONE.getCode(), null, null, null));
    }

    @Test
    public void testPinyin2() throws Exception {
        Assert.assertEquals("gōng gòng chū xíng", new LocalKernel().pinyin("公共出行", ToneType.WITH_VOWEL_TONE.getCode(), null, null, null));
        Assert.assertEquals("wǒ mén yòng zhòng xíng tōng", new LocalKernel().pinyin("我们用众行通", ToneType.WITH_VOWEL_TONE.getCode(), null, null, null));
        Assert.assertEquals("dà zhòng chū xíng", new LocalKernel().pinyin("大众出行", ToneType.WITH_VOWEL_TONE.getCode(), null, null, null));
        Assert.assertEquals("rén mín yín háng", new LocalKernel().pinyin("人民银行", ToneType.WITH_VOWEL_TONE.getCode(), null, null, null));
        Assert.assertEquals("rén mín hěn xíng", new LocalKernel().pinyin("人民很行", ToneType.WITH_VOWEL_TONE.getCode(), null, null, null));
    }

    @Test
    public void testPinyin3() throws Exception {
        Assert.assertEquals("xiǎo ài tóng xué", new LocalKernel().pinyin("小爱同学", ToneType.WITH_VOWEL_TONE.getCode(), null, null, null));
        Assert.assertEquals("xiao3 ai4 tong2 xue2", new LocalKernel().pinyin("小爱同学", ToneType.WITH_NUMBER_TONE.getCode(), null, null, null));
        Assert.assertEquals("xīn kuān tǐ pán", new LocalKernel().pinyin("心宽体胖", ToneType.WITH_VOWEL_TONE.getCode(), null, null, null));
        Assert.assertEquals("xin1 kuan1 ti3 pan2", new LocalKernel().pinyin("心宽体胖", ToneType.WITH_NUMBER_TONE.getCode(), null, null, null));
    }

    @Test
    public void testHandlePinyin() throws Exception {

    }

    @Test
    public void testCapitalize() throws Exception {
        String py = new LocalKernel().capitalize("che");
        Assert.assertEquals("Che", py);
    }
}