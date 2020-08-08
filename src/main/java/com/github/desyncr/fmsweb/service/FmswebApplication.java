package com.github.desyncr.fmsweb.service;

import com.github.desyncr.fmsweb.models.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
@Controller
public class FmswebApplication {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(FmswebApplication.class, args);
	}

	@GetMapping("/")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name, Model model) {
		// Add a bunch of threads
		// Model:
		// Thread:
		//	- forum
		//	- author
		// 	- title
		//  - text outline
		//	- date created
		//	- comments/replies

		List<Thread> threads = getThreads();

		model.addAttribute("threads", threads);

		return "index";
	}

	private List<Thread> getThreads() {
		List<Thread> threads = jdbcTemplate.query(
				"select * from message m where m.subject not like \"RE: %\" order by date desc limit 10",
				(resultSet, rowNum) -> {
					try {
						return new Thread(
								resultSet.getString("subject"),
								resultSet.getString("body"),
								new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("date"))
						);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return null;
				}
		);

		return threads;
	}
}
