package com.james090500.APIManager.Database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBManager {

    private static HikariConfig config = new HikariConfig();
    
    private static HikariDataSource hikari;
    
    private static void init() {
    	String sqlFile = "plugins\\APIManager\\database.db";
    	checkSQLFile(sqlFile);
    	
        config.setJdbcUrl("jdbc:sqlite:" + sqlFile);
        config.setUsername("james090500");
        config.setPassword("SecretPassword47");
        config.addDataSourceProperty("cachePrepStmts" , "true");
        config.addDataSourceProperty("prepStmtCacheSize" , "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit" , "2048");
        hikari = new HikariDataSource(config);                      
        
		checkSQLContent();
    }
    
    public static HikariDataSource getHikari(){
    	if(hikari == null) {
    		init();
    	}
        return hikari;
    }
    
	private static void checkSQLFile(String sqlFile) {		
		File f = new File(sqlFile);
		f.getParentFile().mkdirs();
		if(!f.exists()) {
			try { f.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
		}
	}
	
    private static void checkSQLContent() {
    	try(Connection connection = getHikari().getConnection()) {
			Statement statement = connection.createStatement();
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(UUID varchar(32), username VARCHAR(17), time varchar(11))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}