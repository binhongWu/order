package com.ibeetl.admin.core.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;

/**
 * @author Administrator
 */
@Configuration
public class DataSourceConfig {	
	@Bean(name = "dataSource")
	public DataSource datasource(Environment env) {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
		ds.setUsername(env.getProperty("spring.datasource.username"));
		ds.setPassword(env.getProperty("spring.datasource.password"));
		ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		//oracle 获取注释使用  代码生成的时候需要使用
//		Properties prop = new Properties();
//		prop.setProperty("remarks", "true");
//		ds.setDataSourceProperties(prop);
		return ds;
	}
}



