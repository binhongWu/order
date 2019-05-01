queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from warehouse_warn t
    where t.del = '0'  
    @if(!isEmpty(warningId)){
        and  t.warning_id =#warningId#
    @}
    @if(!isEmpty(alarmt)){
        and  t.alarmt =#alarmt#
    @}
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    order by t.created_time desc
    
findByCode
===

    select
    * 
    from warehouse_warn t
    where t.del = '0'  
    and  t.code =#code#
    
getById
===

    select
    *
    from warehouse_warn t
    where t.del = '0'
    and t.id=#id#



batchDelWarehouseWarnByIds
===

* 批量逻辑删除

    update warehouse_warn set del = 1 where warning_id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update warehouse_warn 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,warning_id = #warningId#
                ,alarmt = #alarmt#
                ,next_alarmt = #nextAlarmt#
                ,code = #code#
                ,stock = #stock#
                ,check = #check#
                ,created_by = #createdBy#
                ,created_time = #createdTime#
                ,remarks = #remarks#
    where id  = #id#
    
    
    
findListByCustom
===

* 自定义查询


    select 
    t.*
    from warehouse_warn t
    where t.del = '0'  
    @if(!isEmpty(warningId)){
        and  t.warning_id =#warningId#
    @}
    @if(!isEmpty(alarmt)){
        and  t.alarmt =#alarmt#
    @}
    @if(!isEmpty(nextAlarmt)){
        and  t.next_alarmt =#nextAlarmt#
    @}
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(stock)){
        and  t.stock =#stock#
    @}
    @if(!isEmpty(check)){
        and  t.check =#check#
    @}
    @if(!isEmpty(remarks)){
        and  t.remarks =#remarks#
    @}
    order by t.created_time desc