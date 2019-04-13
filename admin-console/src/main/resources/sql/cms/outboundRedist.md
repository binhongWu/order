queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from outbound_redist t
    where t.del = '0'  
    @if(!isEmpty(outRegistId)){
        and  t.out_regist_id =#outRegistId#
    @}
    @if(!isEmpty(outRegistDate)){
        and  t.out_regist_date =#outRegistDate#
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
    from outbound_redist t
    where t.del = '0'
    and t.id=#id#



batchDelOutboundRedistByIds
===

* 批量逻辑删除

    update outbound_redist set del = 1 where out_regist_id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update outbound_redist 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,out_regist_id = #outRegistId#
                ,out_regist_date = #outRegistDate#
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
    from outbound_redist t
    where t.del = '0'  
    @if(!isEmpty(outRegistId)){
        and  t.out_regist_id =#outRegistId#
    @}
    @if(!isEmpty(outRegistDate)){
        and  t.out_regist_date =#outRegistDate#
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