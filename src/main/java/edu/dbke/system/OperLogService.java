package edu.dbke.system;

import org.springframework.stereotype.Service;

@Service
public class OperLogService {

	public void insertEntity(OperLog operLog) {
		System.out.println("insert log");
	}

}
