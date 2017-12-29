package com.xzchaoo.archaius2.ext;

import com.netflix.archaius.api.Config;
import com.netflix.archaius.api.Property;
import com.netflix.archaius.api.PropertyRepository;

import java.util.function.Consumer;

/**
 * @author xzchaoo
 * @date 2017/12/29
 */
public interface ConfigExt extends Config, PropertyRepository {
	<T> Property.Subscription bind(String key, Class<T> clazz, Consumer<T> consumer);

	<T> Property.Subscription bind(String key, Class<T> clazz, T defaultValue, Consumer<T> consumer);

//	void addUpdateListener(Consumer<ConfigExt> var1);
//
//	void addUpdateListenerAndTriggerNow(Consumer<ConfigExt> var1);
}
