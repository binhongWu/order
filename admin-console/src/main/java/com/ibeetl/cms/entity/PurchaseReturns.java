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
 * @date 2019-05-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PurchaseReturns extends BaseEntity{

	/**
	 * 退回单号
	 */
    @NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
	@JsonSerialize(using=ToStringSerializer.class)
    /*@SeqID(name = ORACLE_CORE_SEQ_NAME)*/
    @AutoID
	/*@AssignID("uuid")*/
    private Long returnedId ;

	/**
	 * 绘本编码
	 */
    private String code ;

	/**
	 * 绘本数量
	 */
    private String number ;

	/**
	 * 绘本单价
	 */
    private String price ;

	/**
	 * 供货商编号
	 */
    private String supplierId ;

	/**
	 * 采购订单单号
	 */
    private String orderId ;

	/**
	 * 退款方式（0：支付宝 1：微信 2：银行卡）
	 */
    @Dict(type="purchase_returns_refundmethod")
    private String refundMethod ;

	/**
	 * 退款金额
	 */
    private String refundAmount ;

	/**
	 * 退货日期
	 */
    private Date returnedDate ;

	/**
	 * 发货人
	 */
    private String sender ;

	/**
	 * 审查人
	 */
    private String checkBy ;

	/**
	 * 审查日期
	 */
    private Date checkDate ;

	/**
	 * 审核状态（0：待审核 1：通过 2：拒绝）
	 */
    @Dict(type="purchase_returns_checkstatus")
    private String checkStatus ;

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
    private String del ;

	/**
	 * 备注
	 */
    private String remarks ;


}
