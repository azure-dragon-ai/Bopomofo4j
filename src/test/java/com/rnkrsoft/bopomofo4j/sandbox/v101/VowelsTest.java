package com.rnkrsoft.bopomofo4j.sandbox.v101;

import com.rnkrsoft.bopomofo4j.sandbox.v101.Vowels;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by rnkrsoft.com on 2019/9/19.
 */
public class VowelsTest {

    @Test
    public void testParse1() throws Exception {
        char[] chars = Vowels.parse('ǎ');
        Assert.assertEquals('a', chars[0]);
        Assert.assertEquals("3", "" + chars[1]);
    }

    @Test
    public void testParse2() {
        char[] chars = Vowels.parse('à');
        Assert.assertEquals('a', chars[0]);
        Assert.assertEquals("4", "" + chars[1]);
    }
}