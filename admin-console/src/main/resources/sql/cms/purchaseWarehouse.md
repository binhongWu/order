queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from purchase_warehouse t
    where t.del = '0'  
    @if(!isEmpty(enterId)){
        and  t.enter_id =#enterId#
    @}
    @if(!isEmpty(orderId)){
        and  t.order_id =#orderId#
    @}
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(purchaseDate)){
        and  t.purchase_date =#purchaseDate#
    @}
    order by t.created_time desc
    
    
    
getById
===

    select
    *
    from purchase_warehouse t
    where t.del = '0'
    and t.id=#id#



batchDelPurchaseWarehouseByIds
===

* 批量逻辑删除

    update purchase_warehouse set del = 1 where enter_id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update purchase_warehouse 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,enter_id = #enterId#
                ,order_id = #orderId#
                ,code = #code#
                ,number = #number#
                ,price = #price#
                ,supplier_id = #supplierId#
                ,payment_amount = #paymentAmount#
                ,purchase_date = #purchaseDate#
                ,buyer_by = #buyerBy#
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
    from purchase_warehouse t
    where t.del = '0'  
    @if(!isEmpty(enterId)){
        and  t.enter_id =#enterId#
    @}
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
    @if(!isEmpty(paymentAmount)){
        and  t.payment_amount =#paymentAmount#
    @}
    @if(!isEmpty(purchaseDate)){
        and  t.purchase_date =#purchaseDate#
    @}
    @if(!isEmpty(buyerBy)){
        and  t.buyer_by =#buyerBy#
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