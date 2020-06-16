package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Member;
import com.example.repository.MemberRepository;

@Controller
@RequestMapping("/ex05")
public class Exam05Controller {

	@Autowired
	private MemberRepository repository;
	
	@RequestMapping("")
	public String index() {
		return "ex05/exam05";
	}
	@RequestMapping("/toresult")
	public String toResult(String name, Model model) {
		List<Member> memberList=repository.findLike(name);
		memberList.stream().forEach(i->System.out.println(i.getName()));
		model.addAttribute("memberList", memberList);
		return"ex05/exam05-result";
	}
}
