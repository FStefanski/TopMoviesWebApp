package com.stefanski.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Frederik Stefanski
 *
 */
public class IOUtil {

	/**
	 * Read from web page. Insert read lines in String.
	 * 
	 * @param InputStream
	 *            in
	 * @return String text
	 */
	public static String read(InputStream in) {

		StringBuilder text = new StringBuilder(); // for concatenation of Web page content

		try (BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
			String line;
			while ((line = br.readLine()) != null) { // terminates when end-of-file found
				text.append(line).append("\n");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text.toString();
	}
}
