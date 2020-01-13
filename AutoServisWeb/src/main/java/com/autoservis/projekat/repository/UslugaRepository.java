package com.autoservis.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Usluga;

public interface UslugaRepository extends JpaRepository<Usluga, Integer>{
	
	public Usluga findByNazivUsluge(String naziv);
	
	@Query("select u from Usluga u inner join u.popravkas p where p.idPopravka = :id")
	public List<Usluga> getUsluge(@Param("id") Integer id);

}
