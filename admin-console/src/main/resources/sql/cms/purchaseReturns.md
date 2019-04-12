queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from purchase_returns t
    where t.del = '0'  
    @if(!isEmpty(returnedId)){
        and  t.returned_id =#returnedId#
    @}
    @if(!isEmpty(orderId)){
        and  t.order_id =#orderId#
    @}
    @if(!isEmpty(returnedDate)){
        and  t.returned_date =#returnedDate#
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
    from purchase_returns t
    where t.del = '0'
    and t.id=#id#



batchDelPurchaseReturnsByIds
===

* 批量逻辑删除

    update purchase_returns set del = 1 where returned_id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update purchase_returns 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,returned_id = #returnedId#
                ,code = #code#
                ,number = #number#
                ,price = #price#
                ,supplier_id = #supplierId#
                ,order_id = #orderId#
                ,refund_method = #refundMethod#
                ,refund_amount = #refundAmount#
                ,bill_id = #billId#
                ,returned_date = #returnedDate#
                ,sender = #sender#
                ,entry_date = #entryDate#
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
    from purchase_returns t
    where t.del = '0'  
    @if(!isEmpty(returnedId)){
        and  t.returned_id =#returnedId#
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
    @if(!isEmpty(orderId)){
        and  t.order_id =#orderId#
    @}
    @if(!isEmpty(refundMethod)){
        and  t.refund_method =#refundMethod#
    @}
    @if(!isEmpty(refundAmount)){
        and  t.refund_amount =#refundAmount#
    @}
    @if(!isEmpty(billId)){
        and  t.bill_id =#billId#
    @}
    @if(!isEmpty(returnedDate)){
        and  t.returned_date =#returnedDate#
    @}
    @if(!isEmpty(sender)){
        and  t.sender =#sender#
    @}
    @if(!isEmpty(entryDate)){
        and  t.entry_date =#entryDate#
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