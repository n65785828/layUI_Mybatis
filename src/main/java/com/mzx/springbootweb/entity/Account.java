package com.mzx.springbootweb.entity;



public class Account {
	

	private String id;
	private String name;//姓名
	private String code;//工号
	private String mobile;//手机
	private String phone;//电话
	private String pwd;//登录密码
	private String email;//电子邮箱
	private String address;//地址
	private String homeMenu;//首页
	private int rank;//排序
	private String department_id;//所在部门
		
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHomeMenu() {
		return homeMenu;
	}
	public void setHomeMenu(String homeMenu) {
		this.homeMenu = homeMenu;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(!(obj instanceof Account))return false;
		Account a=(Account)obj;
		if(a.getId()==null||"".equals(a.getId()))return false;
		return a.getId().equals(id);
	}
}
