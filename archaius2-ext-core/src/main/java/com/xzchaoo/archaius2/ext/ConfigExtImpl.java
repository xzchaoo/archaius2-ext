package com.xzchaoo.archaius2.ext;

import com.netflix.archaius.DefaultPropertyFactory;
import com.netflix.archaius.api.Config;
import com.netflix.archaius.api.Property;

import java.util.function.Consumer;

/**
 * @author xzchaoo
 * @date 2017/12/29
 */
public class ConfigExtImpl extends ConfigWrapper implements ConfigExt {
	private DefaultPropertyFactory propertyFactory;

	public ConfigExtImpl() {
		propertyFactory = DefaultPropertyFactory.from(this);
	}

	public ConfigExtImpl(String name) {
		super(name);
		propertyFactory = DefaultPropertyFactory.from(this);
	}

	public ConfigExtImpl(String name, Config delegate) {
		super(name, delegate);
		propertyFactory = DefaultPropertyFactory.from(this);
	}

	public ConfigExtImpl(Config delegate) {
		super(delegate);
		propertyFactory = DefaultPropertyFactory.from(this);
	}

	@Override
	public <T> Property<T> get(String key, Class<T> clazz) {
		return propertyFactory.get(key, clazz);
	}

	@Override
	public <T> Property.Subscription bind(String key, Class<T> clazz, Consumer<T> consumer) {
		return bind(key, clazz, null, consumer);
	}

	@Override
	public <T> Property.Subscription bind(String key, Class<T> clazz, T defaultValue, Consumer<T> consumer) {
		Property<T> p = get(key, clazz).orElse(defaultValue);
		consumer.accept(p.get());
		return p.subscribe(consumer);
	}
}
