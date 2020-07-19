package com.github.desyncr.fmsweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@Controller
public class FmswebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FmswebApplication.class, args);
	}

	@GetMapping("/")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("standardDate", new Date());
		model.addAttribute("text", "EXample test");
		model.addAttribute("count", "55");
		return "greeting";
	}
}
