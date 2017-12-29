package com.xzchaoo.archaius2.ext;

import com.netflix.archaius.config.DefaultCompositeConfig;
import com.netflix.archaius.config.DefaultSettableConfig;
import com.netflix.archaius.config.MapConfig;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xzchaoo
 * @date 2017/12/29
 */
public class ConfigTest {
	@Test
	public void test() throws Exception {
		DefaultCompositeConfig dlc = new DefaultCompositeConfig();
		ConfigExtImpl c = new ConfigExtImpl(dlc);

		DefaultSettableConfig dsc = new DefaultSettableConfig();
		SwitchableConfig sc = new SwitchableConfig(dsc);

		dsc.setProperty("d", "d");

		dlc.addConfig("config1", MapConfig.builder().put("a", 1).build());
		dlc.addConfig("config2", MapConfig.builder().put("a", 2).put("b", "3").build());
		dlc.addConfig("config3", sc);
		assertEquals(1, c.getInteger("a").intValue());
		assertEquals(3, c.getInteger("b").intValue());
		assertEquals("cc", c.get("c", String.class).orElse("cc").get());

		int[] di = new int[]{0};
		String[] dv = new String[]{"d", "dd", null};

		//bind will trigger callback right now
		c.bind("d", String.class, d -> {
			assertEquals(dv[di[0]++], d);
		});
		dsc.setProperty("d", "dd");
		sc.setEnabled(false);
		Thread.sleep(10);
	}
}
