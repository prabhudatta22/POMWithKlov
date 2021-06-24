/**
 * 
 */
package com.framework.testutils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Prabhudatta.C
 *
 */
public class Test {

	List<String> header = null;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {}

	public static String[] cleanArray(String[] array) {
	    return Arrays.stream(array).filter(x -> !StringUtils.isBlank(x)).toArray(String[]::new);
	}
	
}
