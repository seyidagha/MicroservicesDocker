package com.consumerApp.configuration;

import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@Configuration
public class SocketConfig {

	@Value("${urlSocket}")
	private String urlSocket;
	
	private static final Logger logger = LoggerFactory.getLogger(SocketConfig.class);


	public void socketIOsendMessage() throws URISyntaxException, InterruptedException {
		logger.info("sending socket message from consumer!");
		//String urlSocket = env.getProperty("urlSocket");
		io.socket.client.Socket socket = IO.socket("http://" + urlSocket+":9097");
		socket.connect();
		socket.emit("join", "Java Consumer Client "+ urlSocket+ ":9093 connected");
		socket.emit("success", "inserted!");
		Thread.sleep(1000);
		socket.disconnect();
	}


}
