package com.autoservis.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Usluga;

public interface UslugaRepository extends JpaRepository<Usluga, Integer>{
	
	public Usluga findByNazivUsluge(String naziv);

}
