package com.yzh.bean;

import java.io.Serializable;

public class User implements Serializable{
	
	//id
	private int id;
	//姓名
	private String name;
	//性别
	private String sex;
	//电话
	private String phone;
	//邮箱
	private String emil;
	//生日
	private String birthday;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String name, String sex, String phone, String emil, String birthday) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
		this.emil = emil;
		this.birthday = birthday;
	}
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmil() {
		return emil;
	}
	public void setEmil(String emil) {
		this.emil = emil;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", phone=" + phone + ", emil=" + emil
				+ ", birthday=" + birthday + "]";
	}
	
}
