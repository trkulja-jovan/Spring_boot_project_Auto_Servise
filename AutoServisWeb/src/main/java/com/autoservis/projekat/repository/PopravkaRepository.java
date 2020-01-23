package com.autoservis.projekat.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Popravka;

public interface PopravkaRepository extends JpaRepository<Popravka, Integer>{
	
	@Query("select p from Popravka p where p.status.opis like :status")
	public List<Popravka> getPopravkaByStatus(@Param("status") String param);
	
	@Query("select count(*) from Popravka p where p.status.opis like :status")
	public Long getPopravkaBroj(@Param("status") String param);
	
	@Query("select count(*) from Popravka p inner join p.radniks r where p.status.opis like :status and "
																	  + "r.idRadnik = :id")
	public Integer getBrojPopravkiZaRadnikaByStatus(@Param("id") Integer id, @Param("status") String status);
	
	@Query("select p from Popravka p inner join p.radniks r where r.korIme like :korIme")
	public List<Popravka> getPopravkeZaRadnika(@Param("korIme") String korIme);
	
	@Query("select p from Popravka p inner join p.radniks r where p.status.opis like :status and r.korIme like :korIme")
	public List<Popravka> getPopravkeZaRadnikaStatus(@Param("status") String status, @Param("korIme") String korIme);
	
	@Query("select p from Popravka p inner join p.radniks r where p.datumPrijema >= :datumOd and "
															   + "p.datumZavrsetka <= :datumDo and "
															   + "p.status.opis like :status and "
															   + "r.idRadnik like :id")
	public List<Popravka> getPopravkasBetweenDates(@Param("datumOd") Date datumOd, 
												   @Param("datumDo") Date datumDo,
												   @Param("status") String status,
												   @Param("id") Integer idRadnik);
	
	@Query("select p from Popravka p where p.datumPrijema >= :datumOd and "
			   							+ "p.datumZavrsetka <= :datumDo and "
			   							+ "p.status.opis like :status")
	public List<Popravka> getPopravkasBetweenDates(@Param("datumOd") Date datumOd, 
												   @Param("datumDo") Date datumDo,
												   @Param("status") String status);
	
	@Query("select p from Popravka p inner join p.radniks r where p.status.opis like :status "
															 	+ "and r.idRadnik != :id")
	public List<Popravka> getPopravkeZaPridruzivanje(@Param("status") String status,
													 @Param("id") Integer id);
	
	

}