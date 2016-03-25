package com.yunshan.bean;

public class User extends BaseBean {

    private int id;
    private String username;
    private String useruuid;
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", useruuid=" + useruuid + "]";
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUseruuid() {
        return useruuid;
    }
    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid;
    }

}
