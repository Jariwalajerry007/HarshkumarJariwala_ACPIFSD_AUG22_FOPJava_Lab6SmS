package com.studentms.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.studentms.app.entity.SmsEntity;
import com.studentms.app.service.SmsServiceImpl;

@Controller
public class SmsController {

	@Autowired
	private SmsServiceImpl service;

	@GetMapping("/")
	public String home(Model m, @Param("keyword") String keyword) {
		List<SmsEntity> student = service.getAllStudent(keyword);
		m.addAttribute("student", student);
		m.addAttribute("keyword", keyword);
		return "index";
	} 
	@GetMapping("/addstudent")
	public String addStudentForm() {
		return "add";
	}

	@PostMapping("/register")
	public String studentRegister(@ModelAttribute SmsEntity s, HttpSession session) {
		System.out.println(s);

		service.addStudent(s);

		session.setAttribute("msg", "Student Added Successfull...");
		return "redirect:/";
	}

	@GetMapping("/editdetails/{id}")
	public String editStudent(@PathVariable Long id, Model m) {
		SmsEntity s = service.getStudentById(id);
		m.addAttribute("student", s);

		return "edit";
	}

	@PostMapping("/update")
	public String studentUpdate(@ModelAttribute SmsEntity s, HttpSession session) {

		service.addStudent(s);

		session.setAttribute("msg", "Student details Updated Successfull...");
		return "redirect:/";
	}

	@GetMapping("/viewdetails/{id}")
	public String viewStudent(@PathVariable Long id, Model m) {
		SmsEntity s = service.getStudentById(id);
		m.addAttribute("student", s);

		return "view";
	}

	@GetMapping("/deletedetails/{id}")
	public String deleteStudent(@PathVariable Long id, HttpSession session) {
		service.deleteStudent(id);
		session.setAttribute("msg", "Student Deleted...");

		return "redirect:/";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication==null || authentication instanceof AnonymousAuthenticationToken) {
			return "/login";
		}
		return "redirect:/";
	}
	
}
