package com.autoservis.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Vlasnik;

public interface KlijentRepository extends JpaRepository<Vlasnik, Integer>{

}
