package com.test.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.models.Producer;
import com.test.models.Record;

@RestController
public class PublisherController {

	@Autowired
	private Producer producer;

	@Autowired
	private BufferedReader br;

	@Bean
	public BufferedReader getBr() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("1500000 Sales Records.csv"));
		br.readLine();
		this.br = br;
		return br;
	}

	@RequestMapping("/hello")
	public String testHello() {
		return "hello world";
	}

	@CrossOrigin
	@RequestMapping("/pro/start")
	public List<Record> startProcess() throws ParseException, IOException {
		// Record.openFile(this.br);
		List<Record> list = Record.readNextHundredRows(this.br);
		this.producer.sendMessage(list);
		return list;
	}

	@CrossOrigin
	@RequestMapping("/pro/stop")
	public String stopProcess() {
		return "process stopped";
	}

}
