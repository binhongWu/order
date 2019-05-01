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
public class IncomingRegist extends BaseEntity{

	/**
	 * 入库单号
	 */
    @NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
	@JsonSerialize(using=ToStringSerializer.class)
    /*@SeqID(name = ORACLE_CORE_SEQ_NAME)*/
    @AutoID
	/*@AssignID("uuid")*/
    private Long inRegistId ;

	/**
	 * 关联订单号
	 */
    private String orderId ;

	/**
	 * 入库日期
	 */
    private Date inRegistDate ;

	/**
	 * 绘本编码
	 */
    private String code ;

	/**
	 * 供应商编码
	 */
    private String supplierId ;

	/**
	 * 审核人
	 */
    private String checkBy ;

	/**
	 * 审核时间
	 */
    private Date checkDate ;

	/**
	 * 审核状态
	 */
    private String checkStatus ;

	/**
	 * 绘本单价
	 */
    private String price ;

	/**
	 * 绘本数量
	 */
    private String number ;

	/**
	 * 总额
	 */
    private String total ;

	/**
	 * 状态位（0:采购入库 1：销售退货入库2：其他）
	 */
    @Dict(type="incoming_regist_status")
    private String status ;

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
