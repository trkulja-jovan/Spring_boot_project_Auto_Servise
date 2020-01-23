package com.autoservis.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.autoservis.projekat.repository.KlijentRepository;
import com.autoservis.projekat.repository.PopravkaRepository;
import com.autoservis.projekat.repository.RadnikRepository;
import com.autoservis.projekat.repository.UslugaRepository;
import com.autoservis.projekat.repository.VoziloRepository;
import com.autoservis.projekat.session.Session;

@Controller
@RequestMapping("/")
public class GeneralController {

	@Autowired
	RadnikRepository rr;

	@Autowired
	PopravkaRepository pr;

	@Autowired
	KlijentRepository kr;
	
	@Autowired
	VoziloRepository vr;
	
	@Autowired
	UslugaRepository ur;

	@Autowired
	HttpServletRequest request;

	@GetMapping("/getAll")
	public String getAllValues() {

		var r = Session.getRadnik();
		var pass = Session.getPass();

		if (r.getUloga().getNazivUloge().equals("WORKER")) {

			var mojePopravke = pr.getPopravkeZaRadnikaStatus("Čeka", r.getKorIme());
			request.getSession().setAttribute("mojePopravkeCeka", mojePopravke);
			
			var mojePopravkeOdobreno = pr.getPopravkeZaRadnikaStatus("Odobreno", r.getKorIme());
			request.getSession().setAttribute("mojePopravkeOdobreno", mojePopravkeOdobreno);
			
		} else {

			var l = pr.getPopravkaBroj("Čeka");
			var l2 = pr.getPopravkaBroj("U procesu");

			var rad = rr.count();

			var popravkeCekanje = pr.getPopravkaByStatus("Čeka");

			request.getSession().setAttribute("popravkeCekanje", popravkeCekanje);
			request.getSession().setAttribute("brCekanjePop", l);
			request.getSession().setAttribute("brPopravlja", l2);
			request.getSession().setAttribute("brRadnika", rad);

		}

		request.getSession().setAttribute("radnik", r);
		request.getSession().setAttribute("radnikPass", pass);

		return "index";
	}

	@GetMapping("/admin/refreshData")
	public String getRefreshData() {

		var l = pr.getPopravkaBroj("Čeka");
		var l2 = pr.getPopravkaBroj("U procesu");

		var rad = rr.count();

		var popravkeCekanje = pr.getPopravkaByStatus("Čeka");

		request.getSession().setAttribute("popravkeCekanje", popravkeCekanje);
		request.getSession().setAttribute("brCekanjePop", l);
		request.getSession().setAttribute("brPopravlja", l2);
		request.getSession().setAttribute("brRadnika", rad);

		return "index";
	}

	@GetMapping("/worker/refreshData")
	public String getRefreshDataWorker() {

		var korIme = Session.getRadnik().getKorIme();

		var mojePopravke = pr.getPopravkeZaRadnikaStatus("Čeka", korIme);
		var popravkeRadi = pr.getPopravkeZaRadnikaStatus("U procesu", korIme);
		var mojePopravkeOdobreno = pr.getPopravkeZaRadnikaStatus("Odobreno", korIme);
		
		request.getSession().setAttribute("mojePopravkeOdobreno", mojePopravkeOdobreno);
		request.getSession().setAttribute("mojePopravkeCeka", mojePopravke);
		request.getSession().setAttribute("mojePopravkeRadi", popravkeRadi);

		return "index";
	}

	@GetMapping("/getKlijenti")
	public String getKlijenti() {

		var klijenti = kr.findAll();

		request.getSession().setAttribute("klijenti", klijenti);

		return "klijenti";
	}
	
	@GetMapping("/worker/getDataForPopravka")
	public String dataForPopravka() {
		
		var vozila = vr.findAll();
		var usluge = ur.findAll();

		request.getSession().setAttribute("vozila", vozila);
		request.getSession().setAttribute("usluge", usluge);
		
		return "redirect:/worker/getPopravke";
	}
	
	/*private void primer() {
		
		var svi_radnici = rr.findAll();
		
		//za svakog radnika u gore listi treba da pronadjem broj popravki zavrsenih, u toku i na cekanju
		//primer za jednog radnika
		
		var statusProcesZaRadnika = pr.getPopravkeZaRadnikaStatus("U procesu", //ovde ide kor_ime radnika
																			 // za koga se trazi lista popravki za prosledjeni
																			 // status npr za trenutnog:
																			Session.getRadnik().getKorIme());
		var popravkeByStatusCeka = pr.getPopravkeZaRadnikaStatus("Čeka", Session.getRadnik().getKorIme());
		
		var popravkeByStatusZavrseno = pr.getPopravkeZaRadnikaStatus("Završeno", Session.getRadnik().getKorIme());
		
		//lako cu dobiti broj npr statusProcesZaRadnika.size() ili count
		
		//to sve treba sad u hashMapu
		var mapa = new HashMap<String, List<Integer>>();
		
	}*/

}
