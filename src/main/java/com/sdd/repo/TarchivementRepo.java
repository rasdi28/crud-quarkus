package com.sdd.repo;

import com.sdd.domain.Tarchivement;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TarchivementRepo implements PanacheRepository<Tarchivement> {
}
