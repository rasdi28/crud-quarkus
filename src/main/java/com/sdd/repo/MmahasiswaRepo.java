package com.sdd.repo;

import com.sdd.domain.Mmahasiswa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class MmahasiswaRepo implements PanacheRepository<Mmahasiswa> {


}
