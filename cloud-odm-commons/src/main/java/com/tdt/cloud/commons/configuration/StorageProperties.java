package com.tdt.cloud.commons.configuration;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className StorageProperties
 * @description 存储相关配置
 * @date 2019-04-01 16:39
 **/

public class StorageProperties {

    private String root = "/data";

    private String remoteLogRoot = "smb://hby:tianditu_1818@192.9.104.229//hby/logs/";

    private String xmlPrefix = "tdt_wmts_";

    private String xmlSavePath = "/data/xml";


    /**
     * true使用正则匹配，false使用in匹配
     */
    private boolean useRegex = false;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getRemoteLogRoot() {
        return remoteLogRoot;
    }

    public void setRemoteLogRoot(String remoteLogRoot) {
        this.remoteLogRoot = remoteLogRoot;
    }

    public String getXmlSavePath() {
        return xmlSavePath;
    }

    public void setXmlSavePath(String xmlSavePath) {
        this.xmlSavePath = xmlSavePath;
    }

    public String getXmlPrefix() {
        return xmlPrefix;
    }

    public void setXmlPrefix(String xmlPrefix) {
        this.xmlPrefix = xmlPrefix;
    }

    public boolean isUseRegex() {
        return useRegex;
    }

    public void setUseRegex(boolean useRegex) {
        this.useRegex = useRegex;
    }
}
