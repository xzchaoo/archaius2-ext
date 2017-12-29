package com.xzchaoo.archaius2.ext.spring;

import com.netflix.archaius.config.DefaultCompositeConfig;
import com.xzchaoo.archaius2.ext.ConfigExt;
import com.xzchaoo.archaius2.ext.ConfigUtils;
import com.xzchaoo.archaius2.ext.ConfigWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author xzchaoo
 * @date 2017/12/29
 */
@RestController
@RequestMapping("${archaius2.ext.web.path:/configs}")
public class Archaius2Controller {
	@Autowired
	private ConfigExt config;

	@GetMapping("/")
	public Map<String, Map<String, String>> get() {
		return ConfigUtils.getCompositeConfigMap((DefaultCompositeConfig) ((ConfigWrapper) config).getDelegate());
	}

	@GetMapping("/final")
	public Map<String, String> getFinal() {
		return ConfigUtils.getConfigMap(config);
	}
}
