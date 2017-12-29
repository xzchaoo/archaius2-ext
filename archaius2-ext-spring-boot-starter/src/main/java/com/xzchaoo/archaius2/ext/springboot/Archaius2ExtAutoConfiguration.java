package com.xzchaoo.archaius2.ext.springboot;

import com.netflix.archaius.DefaultConfigLoader;
import com.netflix.archaius.api.config.CompositeConfig;
import com.netflix.archaius.api.exceptions.ConfigException;
import com.netflix.archaius.config.DefaultCompositeConfig;
import com.xzchaoo.archaius2.ext.ConfigExt;
import com.xzchaoo.archaius2.ext.ConfigExtImpl;
import com.xzchaoo.archaius2.ext.spring.Archaius2ConfigConfigurer;
import com.xzchaoo.archaius2.ext.spring.Archaius2Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xzchaoo
 * @date 2017/12/29
 */
@Configuration
@ConditionalOnProperty(prefix = "archaius2.ext", name = "enabled", havingValue = "true", matchIfMissing = true)
public class Archaius2ExtAutoConfiguration {

	@Autowired(required = false)
	private Archaius2ConfigConfigurer configurer;

	@Bean("archaius2CompositeConfig")
	public CompositeConfig archaius2CompositeConfig() throws ConfigException {
		DefaultCompositeConfig.Builder b = DefaultCompositeConfig.builder();
		if (configurer != null) {
			configurer.config(b);
		}
		CompositeConfig cc = b.build();
		if (cc.getConfigNames().isEmpty()) {
			cc.addConfig("archaius2-default", DefaultConfigLoader.builder().build().newLoader().load("default-config.properties"));
		}
		return cc;
	}

	@Bean
	public ConfigExt config() throws ConfigException {
		return new ConfigExtImpl(archaius2CompositeConfig());
	}

	@Bean
	@ConditionalOnProperty(prefix = "archaius2.ext.controller", name = "enabled", havingValue = "true", matchIfMissing = true)
	public Archaius2Controller archaius2Controller() {
		return new Archaius2Controller();
	}
}
