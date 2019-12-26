package com.autoservis.projekat.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.autoservis.projekat.repository.RadnikRepository;
import com.autoservis.projekat.session.Session;

import model.Radnik;

@Service("customUserDetailsService")
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private RadnikRepository rr;
	
	@Autowired
	private HttpServletRequest request;

	@Override    
	public UserDetails loadUserByUsername(String kor_ime) throws UsernameNotFoundException {

		Radnik r = rr.findByKorIme(kor_ime);
		
		if(r != null) {
			
			Session.setRadnik(r);
			Session.setPass(request.getParameter("password"));

			UserDetailImpl userDetail = new UserDetailImpl();

			userDetail.setUsername(r.getKorIme());
			userDetail.setPassword(r.getPassword());
			userDetail.setUlogas(r.getUloga());

			return userDetail;

		}

		return null;
	}

}