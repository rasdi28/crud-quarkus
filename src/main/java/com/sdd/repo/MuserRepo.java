package com.sdd.repo;

import com.sdd.domain.Muser;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MuserRepo implements PanacheRepository<Muser> {
}
