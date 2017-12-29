package com.xzchaoo.archaius2.ext;

import com.netflix.archaius.api.Config;
import com.netflix.archaius.api.ConfigListener;
import com.netflix.archaius.config.AbstractConfig;
import com.netflix.archaius.config.EmptyConfig;

import java.util.Iterator;

/**
 * @author xzchaoo
 * @date 2017/12/29
 */
public class ConfigWrapper extends AbstractConfig {
	private Config delegate = EmptyConfig.INSTANCE;

	public ConfigWrapper() {
		this(generateUniqueName("wrapper-"));
	}

	public ConfigWrapper(String name) {
		super(name);
	}

	public ConfigWrapper(String name, Config delegate) {
		super(name);
		replaceConfig(delegate);
	}

	public ConfigWrapper(Config delegate) {
		this(generateUniqueName("wrapper-"));
		if (delegate == null) {
			throw new IllegalArgumentException();
		}
		replaceConfig(delegate);
	}

	@Override
	public Object getRawProperty(String key) {
		return delegate.getRawProperty(key);
	}

	@Override
	public boolean containsKey(String key) {
		return delegate.containsKey(key);
	}

	@Override
	public Iterator<String> getKeys() {
		return delegate.getKeys();
	}

	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	public synchronized void replaceConfig(Config config) {
		if (config == null) {
			throw new IllegalArgumentException();
		}
		if (this.delegate == config) {
			return;
		}
		this.delegate.removeListener(listener);

		this.delegate = config;
		delegate.addListener(listener);
		this.notifyConfigUpdated(this);
	}

	private ConfigListener listener = new ConfigListener() {
		@Override
		public void onConfigAdded(Config config) {
			notifyConfigAdded(ConfigWrapper.this);
		}

		@Override
		public void onConfigRemoved(Config config) {
			notifyConfigRemoved(ConfigWrapper.this);
		}

		@Override
		public void onConfigUpdated(Config config) {
			notifyConfigUpdated(ConfigWrapper.this);
		}

		@Override
		public void onError(Throwable error, Config config) {
			notifyError(error, ConfigWrapper.this);
		}
	};

	public Config getDelegate() {
		return delegate;
	}
}
