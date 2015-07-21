package com.cloudboy.study.springProperty.lesson2;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 加载配置文件，此种做法可以，在类中取得需要的配置。
 */
public class PropertyPlaceholderConfigurer extends
		org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {
	private Properties properties;
	
	public Properties getProperties() {
		return properties;
	}
	
	public String getProperty(String key) {
		String value = properties.getProperty(key);
		return value;
	}
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory factory,
			Properties props) throws BeansException {
		properties = props;
		super.processProperties(factory, props);
	}
}
