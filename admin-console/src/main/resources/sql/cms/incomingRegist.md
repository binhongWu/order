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
    @if(!isEmpty(inRegistDate)){
        and  t.in_regist_date =#inRegistDate#
    @}
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(registrateDate)){
        and  t.registrate_date =#registrateDate#
    @}
    @if(!isEmpty(checkDate)){
        and  t.check_date =#checkDate#
    @}
    @if(!isEmpty(checkStatus)){
        and  t.check_status =#checkStatus#
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
                ,in_regist_date = #inRegistDate#
                ,code = #code#
                ,supplier_id = #supplierId#
                ,registrate_by = #registrateBy#
                ,registrate_date = #registrateDate#
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
    @if(!isEmpty(inRegistDate)){
        and  t.in_regist_date =#inRegistDate#
    @}
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(supplierId)){
        and  t.supplier_id =#supplierId#
    @}
    @if(!isEmpty(registrateBy)){
        and  t.registrate_by =#registrateBy#
    @}
    @if(!isEmpty(registrateDate)){
        and  t.registrate_date =#registrateDate#
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