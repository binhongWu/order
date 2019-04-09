package ${package};

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *${entity.displayName}查询
 */
public class ${entity.name}Query extends PageParam {
    @for(attr in attrs) {
        @if(isNotEmpty(attr.dictType)) {
    \@Query(name = "${attr.displayName}", display = true,type=Query.TYPE_DICT,dict="${attr.dictType}")
        @}else if(isEmpty(attr.quertType)){
    \@Query(name = "${attr.displayName}", display = true)        
        @}else {
    \@Query(name = "${attr.displayName}", display = true,type=Query.${attr.quertType})
        @}
        @if(isEmpty(attr.quertType)) {
    private ${attr.javaType} ${attr.name};
        @}else{
    private String ${attr.name}Range;
    private Date ${attr.name}Min;
    private Date ${attr.name}Max;
        @}
    @}
    @for(attr in attrs) {
        @if(isEmpty(attr.quertType)) {
    public ${attr.javaType} get${upperFirst(attr.name)}(){
        return  ${attr.name};
    }
    public void set${upperFirst(attr.name)}(${attr.javaType} ${attr.name} ){
        this.${attr.name} = ${attr.name};
    }
        @}else{
    public void set${upperFirst(attr.name)}Range(String ${attr.name}Range) {
        this.${attr.name}Range = ${attr.name}Range;
        if(StringUtils.isEmpty(${attr.name}Range)) {
        return ;
     }
        Date[] ds = Tool.parseDataRange(${attr.name}Range);
        this.${attr.name}Min=ds[0];
        this.${attr.name}Max =ds[1];
     }
    public Date get${upperFirst(attr.name)}Min() {
        return ${attr.name}Min;
    }
    public void set${upperFirst(attr.name)}Min(Date ${attr.name}Min) {
        this.${attr.name}Min = ${attr.name}Min;
    }
    public Date get${upperFirst(attr.name)}Max() {
        return ${attr.name}Max;
    }
    public void set${upperFirst(attr.name)}Max(Date ${attr.name}Max) {
        this.${attr.name}Max = ${attr.name}Max;
    }
        @}
@}
 
}
