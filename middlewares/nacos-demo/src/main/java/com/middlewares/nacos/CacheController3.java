package com.middlewares.nacos;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * 替换application.yaml配置
 */
@RestController
@RefreshScope
public class CacheController3 {

	@Value(value = "${useLocalCache:false}")
	private boolean useLocalCache;

	private static final String template = "useLocalCache is %s!";

	@GetMapping("/cache3")
	public String cache() {
		return String.format(template, useLocalCache);
	}
}