package com.example.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;

@Controller
@RequestMapping("/ex06")
public class ShoppingCartController {

	@Autowired
	private ServletContext application;

	@Autowired
	private HttpSession session;

	@RequestMapping("")
	public String index(Model model) {
		Integer total=0;
		if(application.getAttribute("itemList")==null) {
			Item note = new Item();
			note.setName("手帳ノート");
			note.setPrice(1000);

			Item stationery = new Item();
			stationery.setName("文房具セット");
			stationery.setPrice(1500);

			Item file = new Item();
			file.setName("ファイル");
			file.setPrice(2000);

			List<Item> itemList = new LinkedList<>();
			itemList.add(note);
			itemList.add(stationery);
			itemList.add(file);

			application.setAttribute("itemList", itemList);
		}else {
			if(session.getAttribute("sessionList")==null) {
			}else {
				List<Item> sessionList=(LinkedList<Item>)session.getAttribute("sessionList");
				
				for(Item item:sessionList) {
					total+=item.getPrice();
				}
			}
		}
		model.addAttribute("total", total);
		return "ex06/item-and-cart";
	}

	@RequestMapping("/incart")
	public String inCart(Integer id,Model model) {
		List<Item> itemList = (List<Item>) application.getAttribute("itemList");
		Item item = itemList.get(id);

		if (session.getAttribute("sessionList") == null) {
			List<Item> sessionList = new LinkedList<Item>();
			sessionList.add(item);
			session.setAttribute("sessionList", sessionList);
		} else {
			List<Item> sessionList = (List<Item>) session.getAttribute("sessionList");
			sessionList.add(item);
		}

		return index(model);
	}
	
	@RequestMapping("/delete")
	public String delete(Integer id, Model model) {
		List<Item> sessionList=(LinkedList<Item>) session.getAttribute("sessionList");
		int intId=(int)id;
		System.out.println(sessionList.get(id).getName());
		sessionList.remove(intId);
		System.out.println(sessionList.get(id).getName());
		session.setAttribute("sessionList", sessionList);
		return index(model);
	}

}
