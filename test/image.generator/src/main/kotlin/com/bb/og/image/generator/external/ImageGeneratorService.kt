package com.bb.og.image.generator.external

import com.bb.og.image.generator.external.dto.ImageGeneratorRequest
import com.bb.og.image.generator.external.dto.ImageGeneratorResponse
import org.springframework.http.MediaType
import org.springframework.web.client.RestClient

class ImageGeneratorService(
	private val imageGeneratorClient: RestClient
) {

	fun generateOgImage(
		title: String,
		color: String,
		subTitle: String?,
		imageUrl: String?): String {

		val request = ImageGeneratorRequest(
			title = title,
			color = color,
			subTitle = subTitle,
			image = imageUrl
		)

		val response = imageGeneratorClient.post()
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(request)
			.retrieve()
			.body(ImageGeneratorResponse::class.java)

		return response!!.location
	}

}
