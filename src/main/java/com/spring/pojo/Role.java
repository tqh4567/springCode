package com.spring.pojo;

import java.util.HashSet;
import java.util.Set;

public class Role {
	private long role_id;
	
	private String role_name;
	
	private String role_memo;
	
	//一个角色对应多个用户
	private Set<User> users=new HashSet<User>();
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public long getRole_id() {
		return role_id;
	}
	
	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}
	
	public String getRole_name() {
		return role_name;
	}
	
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	public String getRole_memo() {
		return role_memo;
	}
	
	public void setRole_memo(String role_memo) {
		this.role_memo = role_memo;
	}
	
	public Role(long role_id, String role_name, String role_memo) {
		super();
		this.role_id = role_id;
		this.role_name = role_name;
		this.role_memo = role_memo;
	}
	
	public Role() {
		super();
	}
	
}
