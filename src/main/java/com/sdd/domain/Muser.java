package com.sdd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Muser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long muserpk;
    private Long musergroupfk;
    private String userid;
    private String password;
    private String email;
    private Date lastlogin;
    private Date createdtime;
    private String createdby;
    private Date lastupdated;
    private String updatedby;

    public Long getMuserpk() {
        return muserpk;
    }

    public void setMuserpk(Long muserpk) {
        this.muserpk = muserpk;
    }

    public Long getMusergroupfk() {
        return musergroupfk;
    }

    public void setMusergroupfk(Long musergroupfk) {
        this.musergroupfk = musergroupfk;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }
}
