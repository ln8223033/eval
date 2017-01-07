package edu.dbke.service.files;


import edu.dbke.mapper.files.FilesMapper;
import edu.dbke.model.org.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Map;

@Service
public class FilesService {
	@Inject
	private FilesMapper filesMapper;
	
	private Map<String, Object> session;
	
	
	public String create(){
		init();
		return null;
	}
	
	public void init(){
		System.out.println("000000000000000000000000000000000000");
		User user = (User) session.get("loginUser");
		String userName = user.getUserName();
		System.out.println("......" + userName);
	}
}
