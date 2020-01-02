package com.autoservis.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Vozilo;

public interface VoziloRepository extends JpaRepository<Vozilo, Integer>{
	
	@Query("select v from Vozilo v where v.vlasnik.idVlasnik = :id")
	public List<Vozilo> findByVlasnik(@Param("id") Integer id);
	
	@Query("select v from Vozilo v where v.regTablice like :regTab")
	public Vozilo findByRegTablice(@Param("regTab") String regTab);

}
