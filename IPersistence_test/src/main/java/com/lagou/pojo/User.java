package com.lagou.pojo;

/**
 * @ClassName User
 * @Description TODO
 * @Author demon
 * @Date 2020/11/22 23:55
 * @Version 1.0
 */
public class User {
    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
