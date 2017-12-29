package com.xzchaoo.archaius2.ext;

import com.netflix.archaius.api.Config;
import com.netflix.archaius.config.DefaultCompositeConfig;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xzchaoo
 * @date 2017/12/29
 */
public class ConfigUtils {
	public static Map<String, String> getConfigMap(Config config) {
		Map<String, String> map = new LinkedHashMap<>();
		config.getKeys().forEachRemaining(key -> map.put(key, config.getString(key)));
		return map;
	}

	public static Map<String, Map<String, String>> getCompositeConfigMap(DefaultCompositeConfig dcc) {
		Map<String, Map<String, String>> map = new LinkedHashMap<>();
		for (String name : dcc.getConfigNames()) {
			map.put(name, getConfigMap(dcc.getConfig(name)));
		}
		return map;
	}
}
