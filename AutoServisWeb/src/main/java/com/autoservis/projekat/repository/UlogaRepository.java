package com.autoservis.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Uloga;

public interface UlogaRepository extends JpaRepository<Uloga, Integer> {
	
	@Query("select u from Uloga u where u.idUloga = 1")
	public Uloga getUloga();

}
