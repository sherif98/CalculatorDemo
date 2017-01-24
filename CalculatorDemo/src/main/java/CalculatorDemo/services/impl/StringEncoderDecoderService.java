package CalculatorDemo.services.impl;

import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class StringEncoderDecoderService {

	public static void encode() {

	}

	public String decode(String encodedString) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString.getBytes());
		return new String(decodedBytes);
	}
}
