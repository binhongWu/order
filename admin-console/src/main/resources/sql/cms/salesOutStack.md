queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from sales_out_stack t
    where t.del = '0'  
    @if(!isEmpty(salesOutStackId)){
        and  t.sales_out_stack_id =#salesOutStackId#
    @}
    @if(!isEmpty(salesId)){
        and  t.sales_id =#salesId#
    @}
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(salesDate)){
        and  t.sales_date =#salesDate#
    @}
    order by t.created_time desc
    
    
    
getById
===

    select
    *
    from sales_out_stack t
    where t.del = '0'
    and t.id=#id#



batchDelSalesOutStackByIds
===

* 批量逻辑删除

    update sales_out_stack set del = 1 where sales_out_stack_id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update sales_out_stack 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,sales_out_stack_id = #salesOutStackId#
                ,sales_id = #salesId#
                ,code = #code#
                ,number = #number#
                ,price = #price#
                ,sales_date = #salesDate#
                ,client_id = #clientId#
                ,payment_amount = #paymentAmount#
                ,payment_method = #paymentMethod#
                ,sales_by = #salesBy#
                ,ship_by = #shipBy#
                ,delivery_address = #deliveryAddress#
                ,check_by = #checkBy#
                ,check_date = #checkDate#
                ,check_status = #checkStatus#
                ,created_by = #createdBy#
                ,created_time = #createdTime#
                ,remarks = #remarks#
    where id  = #id#
    
    
    
findListByCustom
===

* 自定义查询


    select 
    t.*
    from sales_out_stack t
    where t.del = '0'  
    @if(!isEmpty(salesOutStackId)){
        and  t.sales_out_stack_id =#salesOutStackId#
    @}
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
    @if(!isEmpty(salesDate)){
        and  t.sales_date =#salesDate#
    @}
    @if(!isEmpty(clientId)){
        and  t.client_id =#clientId#
    @}
    @if(!isEmpty(paymentAmount)){
        and  t.payment_amount =#paymentAmount#
    @}
    @if(!isEmpty(paymentMethod)){
        and  t.payment_method =#paymentMethod#
    @}
    @if(!isEmpty(salesBy)){
        and  t.sales_by =#salesBy#
    @}
    @if(!isEmpty(shipBy)){
        and  t.ship_by =#shipBy#
    @}
    @if(!isEmpty(deliveryAddress)){
        and  t.delivery_address =#deliveryAddress#
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
    @if(!isEmpty(remarks)){
        and  t.remarks =#remarks#
    @}
    order by t.created_time desc