package com.huel.xgms.base.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据对象
 * @author wsq
 *
 */
public class PageData implements Serializable {

	private static final long serialVersionUID = -8712832365647808087L;

	public static int DEFAULT_PAGE_SIZE = 20;

	public static int DEFAULT_PAGE_NO = 1;
	
	private List<?> data;

	private int pageNo;

	private int pageSize = DEFAULT_PAGE_SIZE;

	private int recordCount;
	
	public PageData() {
		this(DEFAULT_PAGE_NO);
	}
	public PageData(int pageNo){
	    this(pageNo, DEFAULT_PAGE_SIZE);
    }
	public PageData(int pageNo, int pageSize) {
		setPageNo(pageNo);
	    setPageSize(pageSize);
	}
	
	public PageData(int pageNo, int pageSize, int recordCount) {
		this(pageNo, pageSize);
		setRecordCount(recordCount);
	}
	
	public PageData(int pageNo, int pageSize, int recordCount, List<?> data) {
		this(pageNo, pageSize, recordCount);
		this.data = data;
	}

	public int getPageCount() {
		int pagecount = recordCount / pageSize;
		if(pagecount == 0 || recordCount % pageSize != 0) {
			pagecount ++;
		}
		return pagecount;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public PageData setRecordCount(int recordCount) {
		if (recordCount < 0) {
			this.recordCount = 0;
		} else {
			this.recordCount = recordCount;
		}
		return null;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public PageData setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.pageSize = DEFAULT_PAGE_SIZE;
		} else {
			this.pageSize = pageSize;
		}
		return this;
	}

	public boolean isFirstPage() {
		return pageNo <= 1;
	}

	public boolean isLastPage() {
		return pageNo >= getPageCount();
	}

	public int getNextPageNo() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}

	public int getPrePageNo() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}

	public PageData setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
        } else {
			int pageCount = getPageCount();
			if (pageNo > pageCount) {
				this.pageNo = pageCount;
			} else {
				this.pageNo = pageNo;
			}
		}
		return this;
	}
	
	public int getOffset() {
		return pageSize * (pageNo - 1);
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

    /**
     * @description 该方法 为了接受第三方传过来起始页
     * @author wsq
     * @date 2017-11-30
     * @param pageSize
     */
	public void setPage(Integer page){
	    this.pageNo = page;
    }

    /**
     * @description 该方法 为了接受第三方传过来的每页数据数
     * @author wsq
     * @date 2017-11-30
     * @param count
     */
    public void setCount(Integer count){
	    this.pageSize = count;
    }
	
}
