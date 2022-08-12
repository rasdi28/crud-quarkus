package com.sdd.repo;

import com.sdd.domain.Macademic;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MacademicRepo implements PanacheRepository<Macademic> {
}
