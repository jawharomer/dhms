package com.joh.dhms.commons;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder  b=new  BCryptPasswordEncoder();
		System.out.println(b.encode("inet2018dhms"));
	}
}


