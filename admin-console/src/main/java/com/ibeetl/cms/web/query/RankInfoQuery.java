package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.Tool;
import com.ibeetl.admin.core.web.query.PageParam;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 *中英文绘本信息搜索条件
 */
public class RankInfoQuery extends PageParam {
    @Query(name = "销售时间范围", display = true,type=Query.TYPE_DATE_BETWEEN)
    private String createdTimeRange;
    private Date outRegistDateMin;
    private Date outRegistDateMax;


    public String getCreatedTimeRange() {
        return createdTimeRange;
    }

    public void setCreatedTimeRange(String createdTimeRange) {
        this.createdTimeRange = createdTimeRange;
        if(StringUtils.isEmpty(createdTimeRange)) {
            return ;
        }
        Date[] ds = Tool.parseDataRange(createdTimeRange);
        this.outRegistDateMin=ds[0];
        this.outRegistDateMax  =ds[1];
    }

    public Date getOutRegistDateMin() {
        return outRegistDateMin;
    }

    public void setOutRegistDateMin(Date outRegistDateMin) {
        this.outRegistDateMin = outRegistDateMin;
    }

    public Date getOutRegistDateMax() {
        return outRegistDateMax;
    }

    public void setOutRegistDateMax(Date outRegistDateMax) {
        this.outRegistDateMax = outRegistDateMax;
    }
}
