queryByCondition
===


    select 
    @pageTag(){
    t.*,t1.name
    @}
    from purchase_order t
    left join product_infor t1 on t1.code = t.code
    where t.del = '0'  
    @if(!isEmpty(orderId)){
        and  t.order_id =#orderId#
    @}
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(orderDateStart)){
        and  t.order_date >=#orderDateStart#
    @}
    @if(!isEmpty(orderDateEnd)){
        and  t.order_date < #nextDay(orderDateEnd)#
    @}
    @if(!isEmpty(paymentMethod)){
        and  t.payment_method =#paymentMethod#
    @}
    @if(!isEmpty(finishCondition)){
        and  t.finish_condition =#finishCondition#
    @}
    order by t.created_time desc
    
findByFinishCondition
===

* 查找状态为完成的数据

    select
    *
    from purchase_order t
    where t.del = '0'
    and t.finish_condition =#status#

queryOrderId
===

* 查找满足订单单号的数据

       select
       *
       from purchase_order t
       where t.del = '0'
       and t.order_id =#orderId#
    
getById
===

    select
    *
    from purchase_order t
    where t.del = '0'
    and t.id=#id#



batchDelPurchaseOrderByIds
===

* 批量逻辑删除

    update purchase_order set del = 1 where order_id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update purchase_order 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,order_id = #orderId#
                ,code = #code#
                ,number = #number#
                ,price = #price#
                ,supplier_id = #supplierId#
                ,order_date = #orderDate#
                ,deliver_date = #deliverDate#
                ,payment_method = #paymentMethod#
                ,payment_amount = #paymentAmount#
                ,trade_local = #tradeLocal#
                ,check_by = #checkBy#
                ,check_date = #checkDate#
                ,chenk_status = #chenkStatus#
                ,finish_condition = #finishCondition#
                ,created_by = #createdBy#
                ,created_time = #createdTime#
                ,remarks = #remarks#
    where id  = #id#
    
    
    
findListByCustom
===

* 按条件查找全部数据


    select 
    t.*
    from purchase_order t
    where t.del = '0'  
    @if(!isEmpty(orderId)){
        and  t.order_id =#orderId#
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
    @if(!isEmpty(supplierId)){
        and  t.supplier_id =#supplierId#
    @}
    @if(!isEmpty(orderDate)){
        and  t.order_date =#orderDate#
    @}
    @if(!isEmpty(deliverDate)){
        and  t.deliver_date =#deliverDate#
    @}
    @if(!isEmpty(paymentMethod)){
        and  t.payment_method =#paymentMethod#
    @}
    @if(!isEmpty(paymentAmount)){
        and  t.payment_amount =#paymentAmount#
    @}
    @if(!isEmpty(tradeLocal)){
        and  t.trade_local =#tradeLocal#
    @}
    @if(!isEmpty(checkBy)){
        and  t.check_by =#checkBy#
    @}
    @if(!isEmpty(checkDate)){
        and  t.check_date =#checkDate#
    @}
    @if(!isEmpty(chenkStatus)){
        and  t.chenk_status =#chenkStatus#
    @}
    @if(!isEmpty(finishCondition)){
        and  t.finish_condition =#finishCondition#
    @}
    @if(!isEmpty(remarks)){
        and  t.remarks =#remarks#
    @}
    order by t.created_time desc