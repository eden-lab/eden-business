/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ylzl.eden.qcloud.vod.spring.boot.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.qcloud.vod.spring.boot.env.QCloudVODProperties;
import org.ylzl.eden.qcloud.vod.core.config.QCloudVODConfig;
import org.ylzl.eden.qcloud.vod.core.QCloudVODTemplate;

/**
 * 腾讯云VOD 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = "tencent.cloud.vod.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(QCloudVODProperties.class)
@Slf4j
@Configuration
public class QCloudVODAutoCofiguration {

	private final QCloudVODProperties qcloudVodProperties;

	public QCloudVODAutoCofiguration(QCloudVODProperties qcloudVodProperties) {
		this.qcloudVodProperties = qcloudVodProperties;
	}

	@Bean
	public QCloudVODTemplate vodTemplate() {
		log.info("Autowired VODTemplate");
		return new QCloudVODTemplate(
			QCloudVODConfig.builder()
				.region(qcloudVodProperties.getRegion())
				.secretId(qcloudVodProperties.getSecretId())
				.secretKey(qcloudVodProperties.getSecretKey())
				.shortignValidDuration(qcloudVodProperties.getShortignValidDuration()).build());
	}
}
