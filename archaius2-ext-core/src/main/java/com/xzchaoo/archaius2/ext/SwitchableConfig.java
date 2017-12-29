package com.xzchaoo.archaius2.ext;

import com.netflix.archaius.api.Config;
import com.netflix.archaius.config.EmptyConfig;

/**
 * A config that is switchable
 *
 * @author xzchaoo
 * @date 2017/12/29
 */
public class SwitchableConfig extends ConfigWrapper {
	private boolean enabled = true;
	private Config delegate;

	public SwitchableConfig() {
	}

	public SwitchableConfig(String name) {
		super(name);
	}

	public SwitchableConfig(String name, Config delegate) {
		super(name, delegate);
		this.delegate = delegate;
	}

	public SwitchableConfig(Config delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public synchronized void setEnabled(boolean enabled) {
		if (this.enabled != enabled) {
			this.enabled = enabled;
			if (enabled) {
				this.replaceConfig(delegate);
			} else {
				this.replaceConfig(EmptyConfig.INSTANCE);
			}
		}
	}
}
