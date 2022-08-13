package com.sdd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Tarchivement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tarchivementpk;
    private Long muserfk;
    private String title;
    private String institution;
    private Date relase_date;
    private Date expire_date;
    private String image;

    public Long getTarchivementpk() {
        return tarchivementpk;
    }

    public void setTarchivementpk(Long tarchivementpk) {
        this.tarchivementpk = tarchivementpk;
    }

    public Long getmuserfk() {
        return muserfk;
    }

    public void setmuserfk(Long muserfk) {
        this.muserfk = muserfk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Date getRelase_date() {
        return relase_date;
    }

    public void setRelase_date(Date relase_date) {
        this.relase_date = relase_date;
    }

    public Date getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(Date expire_date) {
        this.expire_date = expire_date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
