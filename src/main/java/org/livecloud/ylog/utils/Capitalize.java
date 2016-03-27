package org.livecloud.ylog.utils;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Capitalize extends SimpleTagSupport {
	StringWriter sw = new StringWriter();

	@Override
	public void doTag() throws JspException, IOException {
		getJspBody().invoke(sw);
		getJspContext().getOut().println(CapitalizeString(sw.toString()));
	}
	
	public String CapitalizeString(String str) {
		int strLen;
	    if (str == null || (strLen = str.length()) == 0) {
	        return str;
	    }
	    return new StringBuffer(strLen)
	        .append(Character.toTitleCase(str.charAt(0)))
	        .append(str.substring(1))
	        .toString();
	}
}
