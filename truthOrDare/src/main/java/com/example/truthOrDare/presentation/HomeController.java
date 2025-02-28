package com.example.truthOrDare.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.truthOrDare.service.DareService;
import com.example.truthOrDare.service.QuestionService;

@Controller
public class HomeController {

	@Autowired
	private DareService dareService;
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/login")
	public String loginPage() {

		return "login";
	}
	
	@PostMapping("/logout")
	public String logoutPage() {
		return "logout";
	}
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/getDare")
	@ResponseBody
	public String getRandomDare() {
		return dareService.getDare();
	}
	
	@GetMapping("/getQuestion")
	@ResponseBody
	public String getRandomQuestion() {
		return questionService.getQuestion();
	}
	
	@GetMapping("/resetData")
	public String resetData() {
		dareService.resetAllDares();
		questionService.resetAllQuestions();
		return "redirect:/";
	}
	
}
