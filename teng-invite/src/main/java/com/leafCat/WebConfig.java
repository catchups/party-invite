package com.leafCat;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

@Configuration
@MapperScan(value={"com.**.mapper"})
public class WebConfig extends WebMvcConfigurerAdapter{
	
	/*
	 * lucy-xss-filter
	 *  
	 * */
	@Bean
	public FilterRegistrationBean getFilterRegistrationBean(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new XssEscapeServletFilter());
		registrationBean.setOrder(1);
		registrationBean.addUrlPatterns("*.do", "*.go");	//filter를 거칠 url patterns
		return registrationBean;
	}
	
	/*
	 * mybatis mapper 설정
	 *  
	 * */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		Resource[] arrResource = new PathMatchingResourcePatternResolver()
				.getResources("classpath:mapper/**/*Mapper.xml");
		sqlSessionFactoryBean.setMapperLocations(arrResource);
		sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
	    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
	    return dataSourceTransactionManager;
	}
	
	@Bean
	MappingJackson2JsonView jsonView(){
		MappingJackson2JsonView jv = new MappingJackson2JsonView();
		jv.setContentType("application/json;charset=UTF-8");
		return new MappingJackson2JsonView();
	}
	
	
	/*
	 * 로그인 세션체크 인터셉터
	 *  
	 * */
//	@Autowired
//	CertificationInterceptor certificationInterceptor;
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//	    registry.addInterceptor(certificationInterceptor)
//	    		.addPathPatterns("/**/*.do");
//	}

}
