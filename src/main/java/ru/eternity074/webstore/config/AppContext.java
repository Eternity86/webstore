package ru.eternity074.webstore.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.DriverManagerDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:application.properties", "classpath:db.properties" })
@ComponentScan("ru.eternity074.webstore")
public class AppContext {
	
	@Autowired
	private Environment env;
	
	@Value("ru.eternity074.webstore.entity")
	private String packagesToScan;
	
	@Bean
	public DataSource ds() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClass(env.getProperty("database.driver"));
		ds.setJdbcUrl(env.getProperty("database.url"));
		ds.setUser(env.getProperty("database.user"));
		ds.setPassword(env.getProperty("database.password"));
		
		return ds;
	}

//	@Bean
//	public DataSource ds() {
//		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL).addScript("db/sql/create-table.sql")
//				.addScript("db/sql/insert-data.sql").build();
//
//		return db;
//	}

	@Bean
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return new NamedParameterJdbcTemplate(ds());
	}

}
