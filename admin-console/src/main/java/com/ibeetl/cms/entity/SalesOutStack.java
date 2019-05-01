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
public class SalesOutStack extends BaseEntity{

	/**
	 * 出库单号
	 */
    @NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
	@JsonSerialize(using=ToStringSerializer.class)
    /*@SeqID(name = ORACLE_CORE_SEQ_NAME)*/
    @AutoID
	/*@AssignID("uuid")*/
    private Long salesOutStackId ;

	/**
	 * 销售单号
	 */
    private String salesId ;

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
	 * 销售日期
	 */
    private Date salesDate ;

	/**
	 * 客户信息
	 */
    private String clientId ;

	/**
	 * 付款金额
	 */
    private String paymentAmount ;

	/**
	 * 付款方式（0：支付宝 1：微信 2：银行卡）
	 */
    @Dict(type="sales_out_stack_paymentmethod")
    private String paymentMethod ;

	/**
	 * 销售人
	 */
    private String salesBy ;

	/**
	 * 发货人
	 */
    private String shipBy ;

	/**
	 * 送货地址
	 */
    private String deliveryAddress ;

	/**
	 * 审核人
	 */
    private String checkBy ;

	/**
	 * 审核时间
	 */
    private Date checkDate ;

	/**
	 * 审核状态 服用销售订单的审核状态字典类型
	 */
	@Dict(type="sales_order_checkstatus")
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
