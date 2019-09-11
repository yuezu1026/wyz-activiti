package com.wyz.activiti7.utils;

import java.io.InputStream;

public class StreamUtils {

	public static void closeInputStream(InputStream imageStream) {
		try {
			if(imageStream != null) {
				imageStream.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
