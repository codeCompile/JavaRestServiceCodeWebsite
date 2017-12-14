package aristoweb.springboot.configuration;

import java.util.Properties;
 
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
 
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 

 

@Configuration
@EnableJpaRepositories(basePackages = "aristoweb.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@EnableTransactionManagement
public class JpaConfiguration {
 
    @Autowired
    private Environment environment;
 
    @Value("${datasource.aristoweb.maxPoolSize:10}")
    private int maxPoolSize;
 
    /*
     * Populate SpringBoot DataSourceProperties object directly from application.yml 
     * based on prefix.Thanks to .yml, Hierachical data is mapped out of the box with matching-name
     * properties of DataSourceProperties object].
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.aristoweb")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }
 
    /*
     * Configure HikariCP pooled DataSource.
     */
    @Bean
    public DataSource dataSource() {
        DataSourceProperties dataSourceProperties = dataSourceProperties();
            /*HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
                    .create(dataSourceProperties.getClassLoader())
                    .driverClassName(dataSourceProperties.getDriverClassName())
                    .url(dataSourceProperties.getUrl())
                    .username(dataSourceProperties.getUsername())
                    .password(dataSourceProperties.getPassword())
                    .type(HikariDataSource.class)
                    .build(); */
              
    String connectString = System.getenv("MYSQLCONNSTR_localdb");
if(connectString != null)
{
    System.out.println("connectString: " + connectString);

    String database = "";
    String port = "";
    String username = "";
    String password = "";

    String[] strArray = connectString.split(";");
    for (int i = 0; i < strArray.length; i++) {
        String[] paramArray = strArray[i].split("=");
        switch (i) {
        case 0:
            database = paramArray[1];
            continue;
        case 1:
            port = paramArray[1];
            continue;
        case 2:
            username = paramArray[1];
            continue;
        case 3:
            password = paramArray[1];
            continue;
        }
    }

    String url = "jdbc:mysql://" + port + "/" + database + "?"
            + "user=" + username + "&password=" + password + "&useUnicode=true&characterEncoding=UTF8";

    //response.getWriter().append("url:" + url);
    System.out.println("URL: " + url);
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            
            dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
//            dataSource.setUrl(dataSourceProperties.getUrl());
//            dataSource.setUsername(dataSourceProperties.getUsername());
//            dataSource.setPassword(dataSourceProperties.getPassword());
            
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            //dataSource.setMaximumPoolSize(maxPoolSize);
       
            return dataSource;
    }        
else
{
	 DriverManagerDataSource dataSource = new DriverManagerDataSource();
     
     dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
     dataSource.setUrl(dataSourceProperties.getUrl());
     dataSource.setUsername(dataSourceProperties.getUsername());
     dataSource.setPassword(dataSourceProperties.getPassword());
     
     //dataSource.setMaximumPoolSize(maxPoolSize);
     System.out.println(dataSourceProperties.getUrl());
     return dataSource;
}
    }
            /*spring:
  profiles: local,default
datasource:
  sampleapp:
    url: jdbc:h2:~/test
    username: SA
    password:
    driverClassName: org.h2.Driver
    defaultSchema:
    maxPoolSize: 10
    hibernate:
      hbm2ddl.method: create-drop
      show_sql: true
      format_sql: true
      dialect: org.hibernate.dialect.H2Dialect*/
    
 
    /*
     * Entity Manager Factory setup.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(new String[] { "aristoweb.model" });
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        return factoryBean;
    }
 
    /*
     * Provider specific adapter.
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        return hibernateJpaVendorAdapter;
    }
 
    /*
     * Here you can specify any provider specific properties.
     */
    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("datasource.aristoweb.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("datasource.aristoweb.hibernate.hbm2ddl.method"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("datasource.aristoweb.hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("datasource.aristoweb.hibernate.format_sql"));
        if(StringUtils.isNotEmpty(environment.getRequiredProperty("datasource.aristoweb.defaultSchema"))){
            properties.put("hibernate.default_schema", environment.getRequiredProperty("datasource.aristoweb.defaultSchema"));
        }
        return properties;
    }
 
    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }
 
}