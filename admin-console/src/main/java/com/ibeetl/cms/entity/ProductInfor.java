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
 * @date 2019-04-30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductInfor extends BaseEntity{

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
	 * 绘本编码isbn
	 */
    private String code ;

	/**
	 * 绘本名称（奖项+名称）
	 */
    private String name ;

	/**
	 * 作者
	 */
    private String author ;

	/**
	 * 语种
	 */
    @Dict(type="product_infor_language")
    private String language ;

	/**
	 * 读者对象
	 */
    @Dict(type="product_infor_kinds")
    private String kinds ;

	/**
	 * 出版社
	 */
    private String publishHouse ;

	/**
	 * 出版日期
	 */
    private Date publishDate ;

	/**
	 * 品牌
	 */
    private String brand ;

	/**
	 * 是否是套装
	 */
    @Dict(type="product_infor_score")
    private String score ;

    private String productNum;

	/**
	 * 热销商品排名
	 */
    private String rank ;

	/**
	 * 仓库系统编码
	 */
    private String wareId ;

	/**
	 * 供应商编码
	 */
    private String supplierId ;

	/**
	 * 入库单价
	 */
    private String inPrice ;

	/**
	 * 出库单价
	 */
    private String outPrice ;

	/**
	 * 现有库存
	 */
    private String existStocks ;

	/**
	 * 最小库存
	 */
    private String minStocks ;

	/**
	 * 最大库存
	 */
    private String maxStocks ;

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
