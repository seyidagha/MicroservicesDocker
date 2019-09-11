package com.consumerApp.models;

import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.consumerApp.configuration.SocketConfig;

@Service
public class Consumer {
	private final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@Autowired
	private RecordService service;
	
	@Autowired
	private SocketConfig config;

	@KafkaListener(topics = "data", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
	public void consume(ConsumerRecord<String, List<Record>> message) throws ParseException, URISyntaxException {
		logger.info(String.format("$$ -> Consumed Message -> %s", "message"));
		System.out.println(message.value());
		List<Record> list = message.value();
		for (Record record : list) {
			service.save(record);
		}
		
		// System.out.println(message.value());
//		for (Map<?, ?> m : message.value()) {
//			Record r = new Record();
//			r.setRegion((String) m.get("region"));
//			r.setCountry((String) m.get("country"));
//			r.setItemType((String) m.get("itemType"));
//			r.setSalesChannel((String) m.get("salesChannel"));
//			r.setOrderPriority(m.get("orderPriority").toString().charAt(0));
//			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//			Long date1 = (Long) m.get("orderDate");
//			r.setOrderDate(new Date(date1));
//			r.setOrderId((String) m.get("orderId"));
//			Long date2 = (Long) m.get("shipDate");
//			r.setShipDate(new Date(date2));
//			r.setUnitsSold((Integer) m.get("unitsSold"));
//			Double d = (Double) m.get("unitPrice");
//			r.setUnitPrice(d.floatValue());
//			r.setUnitCost((Float) ((Double) m.get("unitCost")).floatValue());
//			r.setTotalRevenue((Double) m.get("totalRevenue"));
//			r.setTotalCost((Double) m.get("totalCost"));
//			r.setTotalProfit((Double) m.get("totalProfit"));
//			service.save(r);
//			Iterator it = m.entrySet().iterator();
//			while (it.hasNext()) {
//				Map.Entry pair = (Map.Entry) it.next();
//				// System.out.println(pair.getKey() + " = " + pair.getValue());
//				it.remove(); // avoids a ConcurrentModificationException
//			}
//		}
		// SocketConfig.socketConfig();
		// service.saveAll(message.value());
		try {
			config.socketIOsendMessage();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}