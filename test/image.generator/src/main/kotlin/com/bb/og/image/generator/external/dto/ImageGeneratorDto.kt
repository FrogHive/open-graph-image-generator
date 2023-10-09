package com.bb.og.image.generator.external.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ImageGeneratorRequest(
	val title: String,
	val color: String,
	val subTitle: String?,
	val image: String?,
)

data class ImageGeneratorResponse(
	@JsonProperty("ETag") val etag: String,
	@JsonProperty("ServerSideEncryption") val serverSideEncryption: String,
	@JsonProperty("Location") val location: String,
	@JsonProperty("Key") val key: String,
	@JsonProperty("Bucket") val bucket: String,
)
