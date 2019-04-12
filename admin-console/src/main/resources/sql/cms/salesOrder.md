queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from sales_order t
    where t.del = '0'  
    @if(!isEmpty(salesId)){
        and  t.sales_id =#salesId#
    @}
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(orderDate)){
        and  t.order_date =#orderDate#
    @}
    @if(!isEmpty(salesDate)){
        and  t.sales_date =#salesDate#
    @}
    order by t.created_time desc
    
    
    
getById
===

    select
    *
    from sales_order t
    where t.del = '0'
    and t.id=#id#



batchDelSalesOrderByIds
===

* 批量逻辑删除

    update sales_order set del = 1 where sales_id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update sales_order 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,sales_id = #salesId#
                ,code = #code#
                ,number = #number#
                ,price = #price#
                ,order_date = #orderDate#
                ,client_id = #clientId#
                ,sales_date = #salesDate#
                ,payment_amount = #paymentAmount#
                ,sales_by = #salesBy#
                ,payment_method = #paymentMethod#
                ,trade_locations = #tradeLocations#
                ,finished_status = #finishedStatus#
                ,check_by = #checkBy#
                ,check_status = #checkStatus#
                ,check_date = #checkDate#
                ,created_by = #createdBy#
                ,created_time = #createdTime#
                ,remarks = #remarks#
    where id  = #id#
    
    
    
findListByCustom
===

* 自定义查询


    select 
    t.*
    from sales_order t
    where t.del = '0'  
    @if(!isEmpty(salesId)){
        and  t.sales_id =#salesId#
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
    @if(!isEmpty(orderDate)){
        and  t.order_date =#orderDate#
    @}
    @if(!isEmpty(clientId)){
        and  t.client_id =#clientId#
    @}
    @if(!isEmpty(salesDate)){
        and  t.sales_date =#salesDate#
    @}
    @if(!isEmpty(paymentAmount)){
        and  t.payment_amount =#paymentAmount#
    @}
    @if(!isEmpty(salesBy)){
        and  t.sales_by =#salesBy#
    @}
    @if(!isEmpty(paymentMethod)){
        and  t.payment_method =#paymentMethod#
    @}
    @if(!isEmpty(tradeLocations)){
        and  t.trade_locations =#tradeLocations#
    @}
    @if(!isEmpty(finishedStatus)){
        and  t.finished_status =#finishedStatus#
    @}
    @if(!isEmpty(checkBy)){
        and  t.check_by =#checkBy#
    @}
    @if(!isEmpty(checkStatus)){
        and  t.check_status =#checkStatus#
    @}
    @if(!isEmpty(checkDate)){
        and  t.check_date =#checkDate#
    @}
    @if(!isEmpty(remarks)){
        and  t.remarks =#remarks#
    @}
    order by t.created_time desc