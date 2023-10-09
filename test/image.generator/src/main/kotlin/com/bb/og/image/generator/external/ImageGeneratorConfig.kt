package com.bb.og.image.generator.external

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
@EnableConfigurationProperties(ImageGeneratorProperty::class)
class ImageGeneratorConfig {

	@Bean
	fun imageGeneratorService(imageGeneratorClient: RestClient): ImageGeneratorService {
		return ImageGeneratorService(imageGeneratorClient)
	}

	@Bean
	fun imageGeneratorClient(imageGeneratorProperty: ImageGeneratorProperty): RestClient {
		return RestClient.create(imageGeneratorProperty.address)
	}

}

@ConfigurationProperties(prefix = "og.image-generator")
data class ImageGeneratorProperty(
	val address: String
)
