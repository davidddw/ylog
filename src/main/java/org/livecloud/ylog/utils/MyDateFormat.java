package org.livecloud.ylog.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.time.DateUtils;

public class MyDateFormat extends SimpleTagSupport{
	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException {
		getJspBody().invoke(sw);
		getJspContext().getOut().println(getOffsetTime(sw.toString()));
	}
	
	public String getOffsetTime(String newTime){
		try {
			Date lastPost = DateUtils.parseDate(newTime, new String[] { "yyyy-MM-dd HH:mm:ss.SSS" });
			Date now = new Date();
			long duration = (now.getTime() - lastPost.getTime())/1000;
			if(duration < 60) {
				return duration+"秒前";
			}else if(duration<3600){
				return duration/60+ "分钟前";
			}else if(duration<86400){
				return duration/3600+"小时前";
			}else {
				Date zeroTime = DateUtils.truncate(now, Calendar.DATE);
				long offset_day = (zeroTime.getTime() - lastPost.getTime())/1000/3600/24;
				if (offset_day<1) {
					return "昨天";
				}else if(offset_day<2) {
					return "前天";
				}else if(offset_day<365){
					return offset_day+"天前";
				}else {
					return offset_day/365+"年前";
				}
			}	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
}