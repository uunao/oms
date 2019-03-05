package com.nh.oms.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author wcc
 * @version V1.0
 * @date 2018/5/30
 */
@Configuration
@MapperScan(basePackages ="com.nh.oms.dao.erp",sqlSessionFactoryRef ="ERPSqlSessionFactory")
public class ErpDatabaseConfiguration {

    @Value("${spring.datasource.erp.url}")
    String url;

    @Value("${spring.datasource.erp.username}")
    String username;

    @Value("${spring.datasource.erp.password}")
    String password;

    @Value("${spring.datasource.erp.driver-class-name}")
    String driverClassName;


    @Value("${spring.datasource.druid.validationQuery}")
    String validateQuery;

    @Bean(name="dataSourceERP")
    public DataSource druidDataSourceERP() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setPassword(password);
        druidDataSource.setUsername(username);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setValidationQuery(validateQuery);
        return druidDataSource;
    }

    @Bean(name = "ERPTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(druidDataSourceERP());
    }

    @Bean(name = "ERPSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceERP") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/mybatis/erp/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}
