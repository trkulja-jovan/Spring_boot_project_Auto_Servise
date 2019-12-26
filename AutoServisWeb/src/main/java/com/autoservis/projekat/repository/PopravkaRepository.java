package com.autoservis.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Popravka;

public interface PopravkaRepository extends JpaRepository<Popravka, Integer>{
	
	@Query("select p from Popravka p where p.status.opis like :status")
	public List<Popravka> getPopravkaByStatus(@Param("status") String param);

}