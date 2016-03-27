package org.livecloud.ylog.utils;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PageTag extends SimpleTagSupport{
	
	private Pagination pagination;
	
	private String url;
	private String suffix;
	
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Pagination getPagination() {  
        return pagination;  
    }  
  
    public void setPagination(Pagination pagination) {  
        this.pagination = pagination;  
    }  

	public void doTag() throws JspException, IOException {  
		int currentPage = pagination.getCurrentPage();
		int start = pagination.getStart();
		int end = pagination.getEnd();
		int pageNumber = pagination.getPageNumber();
		int edgeEntries = pagination.getEdgeEntries();
		
		//init
		StringBuffer strBuf = new StringBuffer(); 
		strBuf.append("<div id=\"pages\"><ul>");
		
		// Generate "Previous"-Link
		if((pagination.getPrevText()!=null)&&(currentPage > 0 || pagination.isPrevShow())){
			strBuf.append("<li class=\"prev\">").append("<a href=\"").append(url);
			strBuf.append("?page=").append(currentPage-1);
			if(suffix!=null){
				strBuf.append(suffix);
			}
			strBuf.append("\">").append(pagination.getPrevText()).append("</a></li>");
		}
		
		// Generate starting points
		if (start > 0 && edgeEntries > 0) {
			int last = Math.min(edgeEntries, start);
			for(int i=0; i<last; i++) {
				strBuf.append("<li>").append("<a href=\"").append(url);
				strBuf.append("?page=").append(i);
				if(suffix!=null){
					strBuf.append(suffix);
				}
				strBuf.append("\">").append(i).append("</a></li>");
			}
			if((edgeEntries < start)&&(pagination.getEllipseText() != null)){
				strBuf.append("<span>").append(pagination.getEllipseText()).append("</span>");
			}
		}
		
		// Generate interval links
		for(int i=start; i<end; i++) {
			if(i==currentPage) {
				strBuf.append("<li class=\"current\"><a>").append(i+1).append("</a></li>");
			} else {
				strBuf.append("<li>").append("<a href=\"").append(url);
				strBuf.append("?page=").append(i);
				if(suffix!=null){
					strBuf.append(suffix);
				}
				strBuf.append("\">").append(i+1).append("</a></li>");
			}
		}
		
		// Generate ending points
		if((end < pageNumber)&&(edgeEntries>0)){
			if(((pageNumber-edgeEntries)> end)&&(pagination.getEllipseText() != null)){
				strBuf.append("<span>").append(pagination.getEllipseText()).append("</span>");
			}
			int first = Math.max(pageNumber-edgeEntries, end);
			for(int i=first; i<pageNumber; i++) {
				strBuf.append("<li>").append("<a href=\"").append(url);
				strBuf.append("?page=").append(i);
				if(suffix!=null){
					strBuf.append(suffix);
				}
				strBuf.append("\">").append(i).append("</a></li>");
			}
		}
		
		// Generate "Next"-Link
		if((pagination.getNextText()!=null)&&(currentPage <(pageNumber-1) || pagination.isNextShow())) {
			strBuf.append("<li class=\"next\">").append("<a href=\"").append(url);
			strBuf.append("?page=").append(currentPage+1);
			if(suffix!=null){
				strBuf.append(suffix);
			}
			strBuf.append("\">").append(pagination.getNextText()).append("</a></li>");
		}
		
        strBuf.append("</ul></div>");  
        //System.out.println(strBuf.toString());  
        JspContext ctx = getJspContext();  
        JspWriter out = ctx.getOut();  
        if(pageNumber==1){
        	out.print("");  
        } else{
        	out.print(strBuf.toString());  
        }
	}

}
