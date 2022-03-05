package com.example.domaincrawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.domaincrawler.model.Domain;
import com.example.domaincrawler.model.DomainList;

import reactor.core.publisher.Mono;

@Service
public class DomainCrawlerService {

	@Autowired
	private KafkaTemplate<String, Domain> kafkaTemplate;
	
	private String KAFKA_TOPIC="web-domains";
	
	
	public void crawl(String name) {
		
		Mono<DomainList> dominListMono =  WebClient.create()
		 	.get()
		 	.uri("https://api.domainsdb.info/v1/domains/search?domain="+name)
		 	.accept(MediaType.APPLICATION_JSON)
		 	.retrieve()
		 	.bodyToMono(DomainList.class);
		
		dominListMono.subscribe(domainList -> {
			domainList.getDomains()
				.forEach(domain -> {
					kafkaTemplate.send(KAFKA_TOPIC, domain);
					
					System.out.println("Domain Message : " + domain.getDomain());
				});
		});
		
	}

}
