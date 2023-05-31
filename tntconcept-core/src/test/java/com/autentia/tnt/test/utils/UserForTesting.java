package com.autentia.tnt.test.utils;

import com.autentia.tnt.businessobject.User;

public class UserForTesting extends User {

	private static final long serialVersionUID = 8409345495861453909L;

	@Override
	public void setId(Integer id){
		super.setId(id);
	}
}