package ru.magic.school.model;

import javax.persistence.Entity;
public class AppInfo {
    private String appName;
    private String appVersion;
    private String getAppVersion;

    public AppInfo() {
    }

    public AppInfo(String appName, String appVersion, String getAppVersion) {
        this.appName = appName;
        this.appVersion = appVersion;
        this.getAppVersion = getAppVersion;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getGetAppVersion() {
        return getAppVersion;
    }

    public void setGetAppVersion(String getAppVersion) {
        this.getAppVersion = getAppVersion;
    }
}
