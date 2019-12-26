package com.autoservis.projekat.session;

import org.springframework.beans.factory.annotation.Autowired;
import com.autoservis.projekat.repository.RadnikRepository;

import model.Radnik;

public class Session {
	
	@Autowired
	RadnikRepository rr;
	
	private static Radnik r;
	private static String pass;
	
	static {
		r = null;
	}
	
	public static void setRadnik(Radnik r) {
		Session.r = r;
	}
	
	public static Radnik getRadnik() {
		return Session.r;
	}
	
	public static void setPass(String pass) {
		Session.pass = pass;
	}
	
	public static String getPass() {
		return Session.pass;
	}

}
