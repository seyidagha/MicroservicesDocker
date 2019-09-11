package com.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.models.RecordService;

@Controller
public class WebController {

	@Autowired
	private RecordService service;

	@Value("${urlWeb}")
	private String urlWeb;
	
	@Value("${urlKafka}")
	private String urlKafka;
	
	@Value("${urlSocket}")
	private String urlSocket;

	// @Bean
	// public void printCount() {
	// System.out.println(service.getRecordsNumber());
	// }

	@RequestMapping(value= {"/index", "/"})
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		System.out.println("MyURLs are : web: " + urlWeb + " kafka: "+ urlKafka+ " socket: " + urlSocket);
		model.addObject("urlWeb", urlWeb);
		model.addObject("urlKafka", urlKafka);
		model.addObject("urlSocket", urlSocket);
		model.setViewName("index");
		return model;
	}

	@RequestMapping("/retrieveRecordsNumber")
	@ResponseBody
	@CrossOrigin
	public long retrieveRecords() {
		System.out.println("records number");
		return service.getRecordsNumber();
	}
}
