package com.ibeetl.cms.web.dto;

import com.ibeetl.admin.core.annotation.Dict;
import com.ibeetl.cms.entity.CustomerInfor;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class CustomerInforData extends CustomerInfor {

    private String clientCode ;
    private String name ;
    @Dict(type="customer_infor_sex")
    private String sex ;
    private String tel ;
    private String email ;
    private String intergral ;
    @Dict(type="customer_infor_level")
    private String level ;
    private String remarks ;

}
