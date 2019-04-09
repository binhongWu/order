queryByCondition
===


    select 
    \@pageTag(){
    t.*
    \@}
    from ${entity.tableName} t
    where t.del = '0'  
    @for(attr in entity.list){
    		@if(attr.showInQuery){
    		    @if(isEmpty(attr.quertType)){
    \@if(!isEmpty(${attr.name})){
        and  t.${attr.colName} =#${attr.name}#
    \@}
                @}else{
    \@if(!isEmpty(${attr.name}Min)){
        and  t.${attr.colName}>= #${attr.name}Min#
    \@}
    \@if(!isEmpty(${attr.name}Max)){
        and  t.${attr.colName}< #nextDay(${attr.name}Max)#
    \@}
                @}
    		@}
    @}
    order by t.created_time desc
    
    
    
getById
===

    select
    *
    from ${entity.tableName} t
    where t.del = '0'
    and t.id=#id#



batchDel${entity.name}ByIds
===

* 批量逻辑删除

    update ${entity.tableName} set del = 1 where ${entity.idAttribute.colName}  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update ${entity.tableName} 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
        @for(attr in entity.list){
            @if(attr.colName != "id" && attr.colName != "updated_time" && attr.colName != "updated_by" && attr.colName != "del"){
                ,${attr.colName} = #${attr.name}#
            @}
        @}
    where id  = #id#
    
    
    
findListByCustom
===

* 自定义查询


    select 
    t.*
    from ${entity.tableName} t
    where t.del = '0'  
    @for(attr in entity.list){
    		@if(attr.colName != "id" && attr.colName != "updated_time" && attr.colName != "updated_by" && attr.colName != "del" && attr.colName != "created_time" && attr.colName != "created_by"){
    \@if(!isEmpty(${attr.name})){
        and  t.${attr.colName} =#${attr.name}#
    \@}
            @}
    @}
    order by t.created_time desc