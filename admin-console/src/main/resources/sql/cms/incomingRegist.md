queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from incoming_regist t
    where t.del = '0'  
    @if(!isEmpty(inRegistId)){
        and  t.in_regist_id =#inRegistId#
    @}
    @if(!isEmpty(orderId)){
        and  t.order_id =#orderId#
    @}
    @if(!isEmpty(inRegistDate)){
        and  t.in_regist_date =#inRegistDate#
    @}
    order by t.created_time desc
    
    
    
getById
===

    select
    *
    from incoming_regist t
    where t.del = '0'
    and t.id=#id#



batchDelIncomingRegistByIds
===

* 批量逻辑删除

    update incoming_regist set del = 1 where in_regist_id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update incoming_regist 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,in_regist_id = #inRegistId#
                ,order_id = #orderId#
                ,in_regist_date = #inRegistDate#
                ,code = #code#
                ,supplier_id = #supplierId#
                ,check_by = #checkBy#
                ,check_date = #checkDate#
                ,check_status = #checkStatus#
                ,price = #price#
                ,number = #number#
                ,total = #total#
                ,status = #status#
                ,created_by = #createdBy#
                ,created_time = #createdTime#
                ,remarks = #remarks#
    where id  = #id#
    
    
    
findListByCustom
===

* 自定义查询


    select 
    t.*
    from incoming_regist t
    where t.del = '0'  
    @if(!isEmpty(inRegistId)){
        and  t.in_regist_id =#inRegistId#
    @}
    @if(!isEmpty(orderId)){
        and  t.order_id =#orderId#
    @}
    @if(!isEmpty(inRegistDate)){
        and  t.in_regist_date =#inRegistDate#
    @}
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(supplierId)){
        and  t.supplier_id =#supplierId#
    @}
    @if(!isEmpty(checkBy)){
        and  t.check_by =#checkBy#
    @}
    @if(!isEmpty(checkDate)){
        and  t.check_date =#checkDate#
    @}
    @if(!isEmpty(checkStatus)){
        and  t.check_status =#checkStatus#
    @}
    @if(!isEmpty(price)){
        and  t.price =#price#
    @}
    @if(!isEmpty(number)){
        and  t.number =#number#
    @}
    @if(!isEmpty(total)){
        and  t.total =#total#
    @}
    @if(!isEmpty(status)){
        and  t.status =#status#
    @}
    @if(!isEmpty(remarks)){
        and  t.remarks =#remarks#
    @}
    order by t.created_time desc