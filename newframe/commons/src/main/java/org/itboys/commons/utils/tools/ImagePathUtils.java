package org.itboys.commons.utils.tools;

import org.apache.commons.lang3.StringUtils;

public class ImagePathUtils {
	public static String add(String head, String body) {
		if (body.startsWith("http")||body.startsWith(head)) {
			return body;
		} else if (!StringUtils.isEmpty(body) ) {
			body = head + body;
		} else {
			body = "";
		}
		return body;
	}
}
