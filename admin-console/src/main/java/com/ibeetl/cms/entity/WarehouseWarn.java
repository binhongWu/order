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
public class WarehouseWarn extends BaseEntity{

	/**
	 * 预警单号
	 */
    @NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
	@JsonSerialize(using=ToStringSerializer.class)
    /*@SeqID(name = ORACLE_CORE_SEQ_NAME)*/
    @AutoID
	/*@AssignID("uuid")*/
    private Long warningId ;

	/**
	 * 预警时间
	 */
    private Date alarmt ;

	/**
	 * 下一次预警时间
	 */
    private Date nextAlarmt ;

	/**
	 * 绘本编码
	 */
    private String code ;

	/**
	 * 现有库存
	 */
    private String stock ;

	/**
	 * 0:解决 1：未解决
	 */
    @Dict(type="warehouse_warn_check")
    private String check ;

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
