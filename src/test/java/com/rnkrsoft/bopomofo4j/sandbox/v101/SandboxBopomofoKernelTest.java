package com.rnkrsoft.bopomofo4j.sandbox.v101;

import com.rnkrsoft.bopomofo4j.sandbox.v101.SandboxBopomofoKernel;
import org.junit.Test;

import java.io.File;

/**
 * Created by rnkrsoft.com on 2019/9/19.
 */
public class SandboxBopomofoKernelTest {

    @Test
    public void testDownload() throws Exception {
        SandboxBopomofoKernel kernel = new SandboxBopomofoKernel();
        String groupId = "com.rnkrsoft.bopomofo4j";
        String artifactId = "bopomofo4j";
        String url = kernel.fetchLastReleaseVersionUrl(null, groupId, artifactId);
        File filePath = kernel.download(url, "./target");
        System.out.println(filePath);
    }

    @Test
    public void testFetchLastReleaseVersionUrl() throws Exception {
        SandboxBopomofoKernel kernel = new SandboxBopomofoKernel();
        String groupId = "com.rnkrsoft.bopomofo4j";
        String artifactId = "bopomofo4j";
        String url = kernel.fetchLastReleaseVersionUrl(null, groupId, artifactId);
        System.out.println(url);
    }
}