package com.equality.springbootdemo2.entity.two;

import java.io.Serializable;

import com.equality.springbootdemo2.entity.BaseEntity;

public class UserInfo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1419512796512003682L;

	private String name;	
	
	private String password;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public UserInfo(Integer id,String name, String password) {
		super.setId(id);
		this.name = name;
		this.password = password;
	}

	public UserInfo() {
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", password=" + password + ", id=" + getId() + "]";
	}
	
	
	
	
}
