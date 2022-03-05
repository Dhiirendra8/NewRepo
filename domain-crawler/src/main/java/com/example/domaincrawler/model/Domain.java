package com.example.domaincrawler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Domain {
	
	private String domain;
	private String create_date;
	private String update_date;
	private String country;
	private boolean isDead;
	private String A;
	private String NS;
	private String CNAME;
	private String MX;
	private String TXT;
	
	
}
