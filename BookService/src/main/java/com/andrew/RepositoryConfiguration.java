package com.andrew;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/*  �]��JSON���S��ID����즳�I���n�B�z�A�ҥH�S�O�ŧi   */

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Book.class);
	}

}
