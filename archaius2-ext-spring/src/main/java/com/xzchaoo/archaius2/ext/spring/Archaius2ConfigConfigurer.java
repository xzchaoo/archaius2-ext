package com.xzchaoo.archaius2.ext.spring;

import com.netflix.archaius.api.exceptions.ConfigException;
import com.netflix.archaius.config.DefaultCompositeConfig;

/**
 * @author xzchaoo
 * @date 2017/12/29
 */
@FunctionalInterface
public interface Archaius2ConfigConfigurer {
	void config(DefaultCompositeConfig.Builder b) throws ConfigException;
}
