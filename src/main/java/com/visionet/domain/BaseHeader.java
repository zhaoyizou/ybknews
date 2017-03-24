package com.visionet.domain;

public class BaseHeader {

    private String screeny;//屏幕尺寸
    private String screenx;
    private String cd;// MD5(用户密码+de+aid+mos)
    private String mos;//系统
    private String ver;//版本
    private String de;//时间
    private String aid;//应用名
    private String sync;
    private String phone;//手机号

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getScreenx() {
        return screenx;
    }

    public void setScreenx(String screenx) {
        this.screenx = screenx;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getMos() {
        return mos;
    }

    public void setMos(String mos) {
        this.mos = mos;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }

    public String getScreeny() {
        return screeny;
    }

    public void setScreeny(String screeny) {
        this.screeny = screeny;
    }

}
