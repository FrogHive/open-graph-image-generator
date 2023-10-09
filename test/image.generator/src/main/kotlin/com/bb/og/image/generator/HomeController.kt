package com.bb.og.image.generator

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

	@GetMapping
	fun home(): String {
		return "home"
	}

}
