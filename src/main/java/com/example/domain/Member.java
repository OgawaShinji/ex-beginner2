package com.example.domain;

public class Member {

	private Integer id;
	private String name;
	@Override
	public String toString() {
		return "members [id=" + id + ", name=" + name +"]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
