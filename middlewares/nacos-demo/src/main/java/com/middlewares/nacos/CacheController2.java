package com.middlewares.nacos;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class CacheController2 {

	@Value(value = "${useLocalCache:false}")
	private boolean useLocalCache;

	private static final String template = "useLocalCache is %s!";

	@GetMapping("/cache2")
	public String cache() {
		return String.format(template, useLocalCache);
	}

//	@NacosConfigListener(dataId = "nacosDemo.yaml")
//	public void cacheListener(String cache) {
//		YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
//		yamlFactory.setResources(new ByteArrayResource(cache.getBytes()));
//		Properties commonsProperties = yamlFactory.getObject();
//		this.useLocalCache = Boolean.parseBoolean(commonsProperties.getProperty("useLocalCache"));
//	}
}