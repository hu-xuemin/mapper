package com.huxuemin.mapper;

import com.huxuemin.mapper.database.DBConnectionFactory;
import com.huxuemin.mapper.domain.UserPublicInfo;
import com.huxuemin.mapper.mapper.MapperRegister;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		DBConnectionFactory.initDataBaseContext();
		
		UserPublicInfo info = (UserPublicInfo) MapperRegister.getMapper(UserPublicInfo.class)
				.findObjectByPrimaryKey("admin");
		System.out.println("Name: " + info.getFirstname() + info.getLastname());
		
		DBConnectionFactory.closeAllConnection();
	}
}
