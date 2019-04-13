queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from ware_house t
    where t.del = '0'  
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(name)){
        and  t.name =#name#
    @}
    order by t.created_time desc
    
    
    
getById
===

    select
    *
    from ware_house t
    where t.del = '0'
    and t.id=#id#



batchDelWareHouseByIds
===

* 批量逻辑删除

    update ware_house set del = 1 where id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update ware_house 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,code = #code#
                ,name = #name#
                ,created_by = #createdBy#
                ,created_time = #createdTime#
                ,remarks = #remarks#
    where id  = #id#
    
    
    
findListByCustom
===

* 自定义查询


    select 
    t.*
    from ware_house t
    where t.del = '0'  
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(name)){
        and  t.name =#name#
    @}
    @if(!isEmpty(remarks)){
        and  t.remarks =#remarks#
    @}
    order by t.created_time desc