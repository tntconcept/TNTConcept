package com.autentia.tnt.test.utils;

import org.acegisecurity.providers.encoding.ShaPasswordEncoder;

public class DummyPasswordEncoder extends ShaPasswordEncoder {

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		return true;
	}

}
