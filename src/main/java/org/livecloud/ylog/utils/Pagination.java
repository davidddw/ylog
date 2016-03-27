package org.livecloud.ylog.utils;

public class Pagination {
	private int totalRecord;    // 总记录数  
    private int currentPage;    // 当前页码,第几页  
    private int pageSize;       // 每页显示的记录数,每页显示多少条数据  
    private int edgeEntries;	// edge number
    private int pageNumber;
	
	private int displayEntries;	// 当前显示分页按钮的数目 

	private String prevText;
	private String nextText;
	private String ellipseText;	// 默认是...
    private boolean prevShow;
    private boolean nextShow;
    
    public Pagination() { }
    
    public Pagination(int currentPage, int totalRecord, int pageSize) {
    	this.totalRecord = totalRecord;
    	this.pageSize = pageSize;
    	
    	this.ellipseText = "...";
    	this.edgeEntries = 0;
    	this.displayEntries = 10;
    	this.prevText = "Prev";
    	this.nextText = "Next";
    	this.prevShow = false;
    	this.nextShow = false;
    	
    	this.pageNumber = getNumPages();
    	
    	this.currentPage = currentPage<0 ? 0 : (currentPage<pageNumber?currentPage:pageNumber-1);
    }
    
    private int getNumPages() {
		return (int) Math.ceil((double)totalRecord/pageSize);
	}
    
    public int getStart() {
    	int ne_half = (int) Math.ceil((double)displayEntries /2);
		int upper_limit = pageNumber - displayEntries;
		int first = 0;
		if(currentPage > ne_half) {
			first = Math.max(Math.min(currentPage-ne_half, upper_limit), 0);
		}
    	return first;
    }
    
    public int getEnd() {
    	int ne_half = (int) Math.ceil((double)displayEntries /2);
		int last = 0;
		if(currentPage>ne_half) {
			last = Math.min(currentPage+ne_half, pageNumber);
		} else {
			last = Math.min(displayEntries, pageNumber);
		}
		return last;
    }
    
    
	public int getPageNumber() {
		return pageNumber;
	}

	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getEdgeEntries() {
		return edgeEntries;
	}
	public void setEdgeEntries(int edgeEntries) {
		this.edgeEntries = edgeEntries;
	}
	public int getDisplayEntries() {
		return displayEntries;
	}
	public void setDisplayEntries(int displayEntries) {
		this.displayEntries = displayEntries;
	}
	public String getPrevText() {
		return prevText;
	}
	public void setPrevText(String prevText) {
		this.prevText = prevText;
	}
	public String getNextText() {
		return nextText;
	}
	public void setNextText(String nextText) {
		this.nextText = nextText;
	}
	public String getEllipseText() {
		return ellipseText;
	}
	public void setEllipseText(String ellipseText) {
		this.ellipseText = ellipseText;
	}
	public boolean isPrevShow() {
		return prevShow;
	}
	public void setPrevShow(boolean prevShow) {
		this.prevShow = prevShow;
	}
	public boolean isNextShow() {
		return nextShow;
	}
	public void setNextShow(boolean nextShow) {
		this.nextShow = nextShow;
	}
    
    

}
