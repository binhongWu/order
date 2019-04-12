queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from sales_return t
    where t.del = '0'  
    @if(!isEmpty(returnId)){
        and  t.return_id =#returnId#
    @}
    @if(!isEmpty(salesId)){
        and  t.sales_id =#salesId#
    @}
    @if(!isEmpty(returnDate)){
        and  t.return_date =#returnDate#
    @}
    @if(!isEmpty(checkDate)){
        and  t.check_date =#checkDate#
    @}
    @if(!isEmpty(entryDate)){
        and  t.entry_date =#entryDate#
    @}
    order by t.created_time desc
    
    
    
getById
===

    select
    *
    from sales_return t
    where t.del = '0'
    and t.id=#id#



batchDelSalesReturnByIds
===

* 批量逻辑删除

    update sales_return set del = 1 where return_id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update sales_return 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,return_id = #returnId#
                ,sales_id = #salesId#
                ,return_date = #returnDate#
                ,code = #code#
                ,number = #number#
                ,price = #price#
                ,client_id = #clientId#
                ,payment_amount = #paymentAmount#
                ,sales_by = #salesBy#
                ,checkman_by = #checkmanBy#
                ,check_by = #checkBy#
                ,check_date = #checkDate#
                ,entry_date = #entryDate#
                ,created_by = #createdBy#
                ,created_time = #createdTime#
                ,remarks = #remarks#
    where id  = #id#
    
    
    
findListByCustom
===

* 自定义查询


    select 
    t.*
    from sales_return t
    where t.del = '0'  
    @if(!isEmpty(returnId)){
        and  t.return_id =#returnId#
    @}
    @if(!isEmpty(salesId)){
        and  t.sales_id =#salesId#
    @}
    @if(!isEmpty(returnDate)){
        and  t.return_date =#returnDate#
    @}
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(number)){
        and  t.number =#number#
    @}
    @if(!isEmpty(price)){
        and  t.price =#price#
    @}
    @if(!isEmpty(clientId)){
        and  t.client_id =#clientId#
    @}
    @if(!isEmpty(paymentAmount)){
        and  t.payment_amount =#paymentAmount#
    @}
    @if(!isEmpty(salesBy)){
        and  t.sales_by =#salesBy#
    @}
    @if(!isEmpty(checkmanBy)){
        and  t.checkman_by =#checkmanBy#
    @}
    @if(!isEmpty(checkBy)){
        and  t.check_by =#checkBy#
    @}
    @if(!isEmpty(checkDate)){
        and  t.check_date =#checkDate#
    @}
    @if(!isEmpty(entryDate)){
        and  t.entry_date =#entryDate#
    @}
    @if(!isEmpty(remarks)){
        and  t.remarks =#remarks#
    @}
    order by t.created_time desc