package com.oraclejava.Tworld;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

@Entity
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phoneGenerator")
	@SequenceGenerator(name="phoneGenerator", sequenceName = "PHONE_SEQ", allocationSize=1)
	private int id;
	private String name;
	private int price;
	
	@OneToMany(mappedBy = "phone", cascade = CascadeType.ALL)
	@OrderBy("id desc")
	private Set<Comments> comments;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Set<Comments> getComments() {
		return comments;
	}
	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}
	
	
	
}
