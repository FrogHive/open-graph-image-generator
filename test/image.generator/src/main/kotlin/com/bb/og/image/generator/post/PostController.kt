package com.bb.og.image.generator.post

import com.bb.og.image.generator.external.ImageGeneratorService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/post")
class PostController(private val imageGeneratorService: ImageGeneratorService) {

	@GetMapping
	fun post(
		@RequestParam(required = false) title: String = "title",
		@RequestParam(required = false) category: PostCategory = PostCategory.NORMAL,
		@RequestParam(required = false) subTitle: String?,
		@RequestParam(required = false) mainImageUrl: String?,
		request: HttpServletRequest,
		model: Model
	): String {
		val ogImage = imageGeneratorService.generateOgImage(
			title = title,
			color = category.color,
			subTitle = subTitle,
			imageUrl = mainImageUrl
		)

		model.addAllAttributes(
			mapOf(
				"title" to title,
				"subTitle" to subTitle,
				"category" to category,
				"ogImage" to ogImage,
				"currentUrl" to "${request.requestURL}?${request.queryString}",
			)
		)

		return "post"
	}

}
