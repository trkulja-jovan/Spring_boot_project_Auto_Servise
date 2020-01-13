package com.autoservis.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Radnik;

public interface RadnikRepository extends JpaRepository<Radnik, Integer> {
	
	@Query("select r from Radnik r where r.korIme like :korIme")
	public Radnik findByKorIme(@Param("korIme") String korIme);
	
	@Query("select r from Radnik r where r.uloga.nazivUloge like :uloga")
	public List<Radnik> findByRole(@Param("uloga") String uloga);
	
	@Query("select r from Radnik r inner join r.popravkas p where p.idPopravka = :id")
	public List<Radnik> getRadniks(@Param("id") Integer id);

}
