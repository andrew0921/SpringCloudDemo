package com.andrew;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/*  因為JSON內沒有ID的欄位有點不好處理，所以特別宣告   */

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Book.class);
	}

}
