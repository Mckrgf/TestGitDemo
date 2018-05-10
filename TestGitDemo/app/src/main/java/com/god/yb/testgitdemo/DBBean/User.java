package com.god.yb.testgitdemo.DBBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tfhr on 2018/2/1.
 */

@Entity(indexes = {
        @Index(value = "username,password", unique = true)
})

public class User {
    @Id
    private Long id;

    @NotNull
    private String username;
    private String password;
    private String sex;
    private String sex1;
    private String sex2;
    private String sex7;
    private String sex8;
    private String sex9;
    private String sex10;
    private String sex11;
    private String sex12;
    private String sex14;
    private String sex15;

    @Generated(hash = 1315159771)
    public User(Long id, @NotNull String username, String password, String sex,
            String sex1, String sex2, String sex7, String sex8, String sex9,
            String sex10, String sex11, String sex12, String sex14, String sex15) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.sex1 = sex1;
        this.sex2 = sex2;
        this.sex7 = sex7;
        this.sex8 = sex8;
        this.sex9 = sex9;
        this.sex10 = sex10;
        this.sex11 = sex11;
        this.sex12 = sex12;
        this.sex14 = sex14;
        this.sex15 = sex15;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex1() {
        return this.sex1;
    }

    public void setSex1(String sex1) {
        this.sex1 = sex1;
    }

    public String getSex2() {
        return this.sex2;
    }

    public void setSex2(String sex2) {
        this.sex2 = sex2;
    }

    public String getSex7() {
        return this.sex7;
    }

    public void setSex7(String sex7) {
        this.sex7 = sex7;
    }

    public String getSex8() {
        return this.sex8;
    }

    public void setSex8(String sex8) {
        this.sex8 = sex8;
    }

    public String getSex9() {
        return this.sex9;
    }

    public void setSex9(String sex9) {
        this.sex9 = sex9;
    }

    public String getSex10() {
        return this.sex10;
    }

    public void setSex10(String sex10) {
        this.sex10 = sex10;
    }

    public String getSex11() {
        return this.sex11;
    }

    public void setSex11(String sex11) {
        this.sex11 = sex11;
    }

    public String getSex12() {
        return this.sex12;
    }

    public void setSex12(String sex12) {
        this.sex12 = sex12;
    }

    public String getSex14() {
        return this.sex14;
    }

    public void setSex14(String sex14) {
        this.sex14 = sex14;
    }

    public String getSex15() {
        return this.sex15;
    }

    public void setSex15(String sex15) {
        this.sex15 = sex15;
    }
}
