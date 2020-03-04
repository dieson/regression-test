package com.ifchange.regressiontest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifchange.regressiontest.validator.order.Create;
import com.ifchange.regressiontest.validator.order.Get;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    @NotEmpty(message = "username不能为空", groups = {Create.class, Get.class})
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "password不能为空", groups = {Create.class, Get.class})
    private String password;

    @Email(message = "邮箱的格式不正确", groups = {Create.class})
    private String email;

    private Boolean state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}