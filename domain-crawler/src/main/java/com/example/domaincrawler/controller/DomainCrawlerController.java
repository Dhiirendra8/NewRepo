package com.example.domaincrawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domaincrawler.DomainCrawlerService;

@RestController
@RequestMapping("/domain")
public class DomainCrawlerController {

	@Autowired
	private DomainCrawlerService service;
	
	@GetMapping("/lookup/{name}")
	public String lookup(@PathVariable("name") final String name) {
		
		service.crawl(name);
		
		return "Domain Crawler has scrapped your data";
	}
	
}
