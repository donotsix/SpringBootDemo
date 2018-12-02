package com.cainiao.bn.file;

import java.io.Serializable;
import java.util.Date;

public class BaseQuery implements Serializable {
	private static final long serialVersionUID = -2811165611111664768L;
    
	//哪一页
    public Integer pageNo;
    
    //页大小
    public Integer pageSize = 50;
    
    //绝对偏移
    public Integer offset = 0;
    
    public Boolean isNeedPagination=true;
    
    
    public Boolean getIsNeedPagination() {
		return isNeedPagination;
	}

	public void setIsNeedPagination(Boolean isNeedPagination) {
		this.isNeedPagination = isNeedPagination;
	}
    
	public Integer getQueryFrom() {
		return offset;
	}
	public void setQueryFrom(Integer offset) {
		this.offset = offset;
	}
    //排序字段，默认最新修改时间
    public String orderBy = "gmt_create";
    
    //排序方向，默认降序
    public Boolean ascendingOrder = false;

	//默认不显示已经删除的
    protected Integer deleted = 0;
    
    //创建时间
    private Date gmtCreate;

    //更新时间
    private Date gmtModified;
    
    public Boolean getAscendingOrder() {
		return ascendingOrder;
	}

	public void setAscendingOrder(Boolean ascendingOrder) {
		this.ascendingOrder = ascendingOrder;
	}
	
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    
    public String getOrderBy() {
    	if(ascendingOrder) {
    		return orderBy + " asc";
    	} else {
    		return orderBy + " desc";
    	}
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getPageNo() {
		if (this.offset ==null) {
			return null;
		}
		 Integer pg= ((this.offset + this.pageSize - 1) / this.pageSize) + 1;
		 return  pg;
    }
/**
 * 使用setQueryFrom或offset 赋值后，不能再设置pageNo
 * @param pageNo
 */
    public void setPageNo(Integer pageNo) {
        if (pageNo != null && pageNo <= 0) {
        	pageNo = 1;
        }
        this.pageNo = pageNo;
        this.offset = getPageSize() * (pageNo - 1);
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize < 0) {
            pageSize = 10;
        }
        if (pageSize > 1000) {
            pageSize = 1000;
        }
        this.pageSize = pageSize;
    }

    public Integer getQueryLimit() {
    	return pageSize;
    }

    public void setQueryLimit(Integer pageSize){
    	setPageSize(pageSize);
    }
    
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
