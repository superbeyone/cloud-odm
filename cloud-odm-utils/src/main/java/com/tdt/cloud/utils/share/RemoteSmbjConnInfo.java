package com.tdt.cloud.utils.share;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className RemoteSmbConnInfo
 * @description
 * @date 2019-06-21 19:05
 **/

public class RemoteSmbjConnInfo {

    private String username;

    private String password;

    private String host;

    private String sharePath;

    private String folder;

    public RemoteSmbjConnInfo() {
    }

    public RemoteSmbjConnInfo(String username, String password, String host, String sharePath, String folder) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.sharePath = sharePath;
        this.folder = folder;
    }

    public RemoteSmbjConnInfo(String host, String sharePath, String folder) {
        this.host = host;
        this.sharePath = sharePath;
        this.folder = folder;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSharePath() {
        return sharePath;
    }

    public void setSharePath(String sharePath) {
        this.sharePath = sharePath;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    @Override
    public String toString() {
        return "RemoteSmbjConnInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", host='" + host + '\'' +
                ", sharePath='" + sharePath + '\'' +
                ", folder='" + folder + '\'' +
                '}';
    }
}
