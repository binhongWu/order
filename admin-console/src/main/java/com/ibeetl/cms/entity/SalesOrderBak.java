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
 * @date 2019-05-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SalesOrderBak extends BaseEntity{

	/**
	 * 订单单号
	 */
	@NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
	@JsonSerialize(using=ToStringSerializer.class)
	/*@SeqID(name = ORACLE_CORE_SEQ_NAME)*/
	@AutoID
	/*@AssignID("uuid")*/
	private Long salesId ;

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
	 * 客户编码
	 */
	private String clientId ;

	/**
	 * 销售日期
	 */
	private Date salesDate ;

	/**
	 * 应付款金额
	 */
	private String paymentAmount ;

	/**
	 * 销售人
	 */
	private String salesBy ;

	/**
	 * 付款方式（0：支付宝 1：微信 2：银行卡）
	 */
	@Dict(type="sales_order_paymentmethod")
	private String paymentMethod ;

	/**
	 * 交货地点
	 */
	private String tradeLocations ;

	/**
	 * 0:直销 1：代销
	 */
	@Dict(type="sales_order_orderfor")
	private String orderFor ;

	/**
	 * 完成状态（0：完成 1：失败）
	 */
	@Dict(type="sales_order_finishedstatus")
	private String finishedStatus ;

	/**
	 * 审核人
	 */
	private Long checkBy ;

	/**
	 * 实付款金额
	 */
	private String checkStatus ;

	/**
	 * 审核日期
	 */
	private Date checkDate ;

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

	/**
	 * 关联销售订单号
	 */
	private Long salesOrderId;


}
