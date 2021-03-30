package com.oraclejava.Tworld;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentsGenerator")
	@SequenceGenerator(name="commentsGenerator", sequenceName = "COMMENTS_SEQ", allocationSize=1)
	private int id;
	private String comments;
	
	//Phone에서 객체 가져오기
	@ManyToOne
	@JoinColumn(name="phone_id")
	private Phone phone;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	
	
}
