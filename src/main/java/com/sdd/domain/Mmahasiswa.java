package com.sdd.domain;


import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mmahasiswa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long mahasiswapk;

    public String name;

    public String npm;

    public String kelas;

    public Long getMahasiswapk() {
        return mahasiswapk;
    }

    public void setMahasiswapk(Long mahasiswapk) {
        this.mahasiswapk = mahasiswapk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
}
