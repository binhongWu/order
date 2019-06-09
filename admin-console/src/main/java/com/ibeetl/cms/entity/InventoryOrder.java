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
 * @date 2019-06-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InventoryOrder extends BaseEntity{

	/**
	 * 系统编号
	 */
    @NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
	@JsonSerialize(using=ToStringSerializer.class)
    /*@SeqID(name = ORACLE_CORE_SEQ_NAME)*/
    @AutoID
	/*@AssignID("uuid")*/
    private Long id ;

	/**
	 * 盘点单号
	 */
    private Long inventoryId ;

	/**
	 * 绘本编码
	 */
    private String code ;

	/**
	 * 绘本名称
	 */
	private String name ;

	/**
	 * 语种
	 */
    private String language ;

	/**
	 * 读者对象
	 */
    private String kinds ;

	/**
	 * 图书分类
	 */
    private String bookKind ;

	/**
	 * 现有库存
	 */
    private String existStocks ;

	/**
	 * 盘点库存
	 */
    private String inventoryStocks ;

	/**
	 * 创建人
	 */
    private Long createdBy ;

	/**
	 * 创建时间
	 */
    private Date createdTime ;

	/**
	 * 修改人
	 */
    private Long updatedBy ;

	/**
	 * 修改时间
	 */
    private Date updatedTime ;

	/**
	 * 删除标志位（0：存在 1：删除）
	 */
    private String del ;

	/**
	 * 备注
	 */
    private String remarks ;


}
