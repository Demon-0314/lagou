package com.lagou.pojo;

/**
 * @ClassName User
 * @Description TODO
 * @Author demon
 * @Date 2020/11/25 1:31
 * @Version 1.0
 */
public class User {
    private Integer id;
    private String username;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
