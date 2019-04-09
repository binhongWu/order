package ${package};

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
 * ${comment}
 * \@author admin
 * \@date ${date(),"yyyy-MM-dd"}
 */
\@Data
\@Builder
\@NoArgsConstructor
\@AllArgsConstructor
\@EqualsAndHashCode(callSuper = true)
public class ${className} extends BaseEntity{

    @for(attr in attrs){
	@if(!isEmpty(attr.comment)){
	/**
	 * ${attr.comment}
	 */
	@}
	@if(attr.isId) {
    \@NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
	\@JsonSerialize(using=ToStringSerializer.class)
    /*\@SeqID(name = ORACLE_CORE_SEQ_NAME)*/
    \@AutoID
	/*\@AssignID("uuid")*/
	@}
	@if(isNotEmpty(attr.dictType)) {
    \@Dict(type="${attr.dictType}")
	@}
    private ${attr.type} ${attr.name} ;

	@}

}
