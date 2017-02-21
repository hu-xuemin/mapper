package com.huxuemin.mapper.database;

import java.sql.Connection;
import java.util.Map;

import com.huxuemin.mapper.domain.UserPublicInfoTable;
import com.huxuemin.mapper.mapper.BaseMapper;
import com.huxuemin.mapper.mapper.MapperRegister;
import com.huxuemin.mapper.mapper.TableAnnotationProcesser;
import com.huxuemin.mapper.mapper.TableMap;

public class DBConnectionFactory {

	private static MySqlDBManager connectionManager = new MySqlDBManager();

	public static Connection getConnection() {
		return connectionManager.getConnection();
	}

	public static void initDataBaseContext() {
		initTables();
		initDomainMapper();
	}

	private static void initDomainMapper() {
		Map<Class<?>, String> classToTablename = TableAnnotationProcesser
				.scanClassToTablenameMaps("com.huxuemin.mapper.domain");
		for (Map.Entry<Class<?>, String> entry : classToTablename.entrySet()) {
			Class<?> klass = entry.getKey();
			String tableName = entry.getValue();
			TableMap dm = new TableMap(tableName, klass).buildColumnAnnotation();
			MapperRegister.register(klass, new BaseMapper(dm));
		}
	}

	private static void initTables() {
		initTable(new UserPublicInfoTable());
	}

	static ConnectionAdapter createNewConnectionAdapter(ConnectionPool pool) {
		return new ConnectionAdapter(connectionManager.createNewConnection(), pool);
	}

	private static void initTable(ITable tab) {
		connectionManager.initTable(tab);
	}

	public static void closeAllConnection() {
		connectionManager.closeAllConnection();
	}

}
