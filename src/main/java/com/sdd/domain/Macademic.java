package com.sdd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Macademic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long macademicpk;
    private Integer muserfk;
    private String academic_name;
    private String academic_grade;
    private String region;
    private String major;
    private Date graduation_year;
    private String academic_image;


    public Long getMacademicpk() {
        return macademicpk;
    }

    public void setMacademicpk(Long macademicpk) {
        this.macademicpk = macademicpk;
    }

    public Integer getMuserfk() {
        return muserfk;
    }

    public void setMuserfk(Integer muserfk) {
        this.muserfk = muserfk;
    }

    public String getAcademic_name() {
        return academic_name;
    }

    public void setAcademic_name(String academic_name) {
        this.academic_name = academic_name;
    }

    public String getAcademic_grade() {
        return academic_grade;
    }

    public void setAcademic_grade(String academic_grade) {
        this.academic_grade = academic_grade;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Date getGraduation_year() {
        return graduation_year;
    }

    public void setGraduation_year(Date graduation_year) {
        this.graduation_year = graduation_year;
    }

    public String getAcademic_image() {
        return academic_image;
    }

    public void setAcademic_image(String academic_image) {
        this.academic_image = academic_image;
    }
}
