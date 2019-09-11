package com.test.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

	@Autowired
	private RecordRepository repo;

	public void saveAll(List<Record> r) {
		for (Record record : r) {
			repo.save(record);
		}
	}

	public void save(Record record) {
		repo.save(record);
	}

	public long getRecordsNumber() {
		return repo.count();
	}

}
