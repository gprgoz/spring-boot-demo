package com.example.demo.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "db")
@Component
public class PropertiesDemo {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    @Value("${db.aa.bb}zzz"+123)
    private String aaBb;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getAaBb() {
        return aaBb;
    }

    public void setAaBb(String aaBb) {
        this.aaBb = aaBb;
    }

    @Override
    public String toString() {
        return "PropertiesDemo{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driverClassName='" + driverClassName + '\'' +
                ", aaBb='" + aaBb + '\'' +
                '}';
    }
}
