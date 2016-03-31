package com.example.web;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.web.sqlmappers")
@EnableTransactionManagement
@EnableOAuth2Client
public class BootSocialApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BootSocialApplication.class, args);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFatory(DataSource datasource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(datasource);
		sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		sqlSessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:META-INF/sqlmaps/**/*.xml"));
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
		
	@Value("${kakao.restApiKey}")
	private String kakaoRestApiKey;

	@Value("${kakao.accessTokenUri}")
	private String kakaoAccessTokenUri;

	@Value("${kakao.userAuthorizationUri}")
	private String kakaoUserAuthorizationUri;

	@Value("${kakao.redirectUri}")
	private String kakaoRedirectUri;

	@Bean
	public OAuth2ProtectedResourceDetails kakao() {
		AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
		details.setId("kakao");
		details.setClientId(kakaoRestApiKey);
		details.setAccessTokenUri(kakaoAccessTokenUri);
		details.setUserAuthorizationUri(kakaoUserAuthorizationUri);
		details.setPreEstablishedRedirectUri(kakaoRedirectUri);
		details.setTokenName("access_token");
		return details;
	}
	
	@Bean
	public OAuth2RestTemplate kakaoRestTemplate(OAuth2ClientContext clientContext) {
		return new OAuth2RestTemplate(kakao(), clientContext);
	}
}
