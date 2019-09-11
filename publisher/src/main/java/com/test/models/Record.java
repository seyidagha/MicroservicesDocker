package com.test.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Record {
	private String region;
	private String country;
	private String itemType;
	private String salesChannel;
	private char orderPriority;
	private Date orderDate;
	private String orderId;
	private Date shipDate;
	private int unitsSold;
	private float unitPrice;
	private float unitCost;
	private double totalRevenue;
	private double totalCost;
	private double totalProfit;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	public char getOrderPriority() {
		return orderPriority;
	}

	public void setOrderPriority(char orderPriority) {
		this.orderPriority = orderPriority;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public int getUnitsSold() {
		return unitsSold;
	}

	public void setUnitsSold(int unitsSold) {
		this.unitsSold = unitsSold;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float f) {
		this.unitPrice = f;
	}

	public float getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(float unitCost) {
		this.unitCost = unitCost;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public static List<Record> readNextHundredRows(BufferedReader br) throws IOException, ParseException {
		List<Record> records = new ArrayList<Record>();
		String line;
		int i = 0;
		// br.readLine();
		while (i < 2) {
			if ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				Record record = new Record();
				record.setRegion(values[0]);
				record.setCountry(values[1]);
				record.setItemType(values[2]);
				record.setSalesChannel(values[3]);
				record.setOrderPriority(values[4].charAt(0));
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				// String inputDate = format.format("MM/dd/YYYY");
				record.setOrderDate(format.parse(values[5]));
				record.setOrderId(values[6]);
				record.setShipDate(format.parse(values[7]));
				record.setUnitsSold(Integer.parseInt(values[8]));
				record.setUnitPrice(Float.parseFloat(values[9]));
				record.setUnitCost(Float.parseFloat(values[10]));
				record.setTotalRevenue(Double.parseDouble(values[11]));
				record.setTotalCost(Double.parseDouble(values[12]));
				record.setTotalProfit(Double.parseDouble(values[13]));
				records.add(record);
				// records.add(Arrays.asList(values));
				// System.out.println(Arrays.asList(values));
				i++;
			}
		}
		return records;
	}

}
