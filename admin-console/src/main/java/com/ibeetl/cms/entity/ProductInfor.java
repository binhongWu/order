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
 * @date 2019-04-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductInfor extends BaseEntity{

	/**
	 * 绘本编码
	 */
    @NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
	@JsonSerialize(using=ToStringSerializer.class)
    /*@SeqID(name = ORACLE_CORE_SEQ_NAME)*/
    @AutoID
	/*@AssignID("uuid")*/
    private String code ;

	/**
	 * 绘本名称（奖项+名称）
	 */
    private String name ;

	/**
	 * 外文名字
	 */
    private String foreignName ;

	/**
	 * 读者对象
	 */
    private String kinds ;

	/**
	 * 开本
	 */
    private String format ;

	/**
	 * 页数
	 */
	private String pages ;

	/**
	 * 商品尺寸
	 */
    private String size ;

	/**
	 * 商品重量
	 */
    private String weight ;

	/**
	 * 品牌
	 */
    private String brand ;

	/**
	 * 用户评分
	 */
    private String score ;

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
	 * 出版社
	 */
    private String publishHouse ;

	/**
	 * 创建人
	 */
    private String createdBy ;

	/**
	 * 创建时间
	 */
    private Date createdTime ;

	/**
	 * 修改人
	 */
    private String updatedBy ;

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
