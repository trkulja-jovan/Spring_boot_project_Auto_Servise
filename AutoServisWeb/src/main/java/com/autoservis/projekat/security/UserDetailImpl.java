package com.autoservis.projekat.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import model.Uloga;

public class UserDetailImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String kor_ime;
	private String password;
	
	private Uloga role;
	
	public UserDetailImpl() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		 var authorities = new ArrayList<SimpleGrantedAuthority>();      
		 
		 authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getNazivUloge()));
		 
		 return authorities;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUlogas(Uloga u) {
		this.role = u;
	}
	
	public void setUsername(String username) {
		this.kor_ime = username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return kor_ime;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
