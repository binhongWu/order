package com.ibeetl.cms.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;

import com.ibeetl.admin.core.util.ValidateConfig;

import org.beetl.sql.core.TailBean;
import java.math.*;

import com.ibeetl.admin.core.annotation.Dict;
import com.ibeetl.admin.core.entity.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

/**
 * 
 * @author admin
 * @date 2019-04-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SupplieInfor extends BaseEntity{

	/**
	 * 供货商编号
	 */
    @NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
	@JsonSerialize(using=ToStringSerializer.class)
    /*@SeqID(name = ORACLE_CORE_SEQ_NAME)*/
    @AutoID
	/*@AssignID("uuid")*/
    private String supplierId ;

	/**
	 * 供货商名称
	 */
    private String supplierName ;

	/**
	 * 供应图书种类
	 */
    private String books ;

	/**
	 * 职务
	 */
    private String position ;

	/**
	 * 地址
	 */
    private String address ;

	/**
	 * 城市
	 */
    private String city ;

	/**
	 * 邮编
	 */
    private String postcode ;

	/**
	 * 国家
	 */
    private String couty ;

	/**
	 * 电话
	 */
    private String tel ;

	/**
	 * 开户银行
	 */
    private String depositBank ;

	/**
	 * 银行账号
	 */
    private String bankAccount ;

	/**
	 * 邮箱
	 */
    private String email ;

	/**
	 * 创建人
	 */
    private Long createdBy ;

	/**
	 * 创建时间
	 */
    private Date createdTime ;

	/**
	 * 更新人
	 */
    private Long updatedBy ;

	/**
	 * 更新时间
	 */
    private Date updatedTime ;

	/**
	 * 删除标记{0:正常,1:已删除}
	 */
    private Integer del ;

	/**
	 * 备注
	 */
    private String remarks ;


}
