package com.middlewares.nacos;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class CacheController {

	@NacosValue(value = "${useLocalCache:false}")
	private boolean useLocalCache;

	private static final String template = "useLocalCache is %s!";

	@GetMapping("/cache")
	public String cache() {
		return String.format(template, useLocalCache);
	}
}