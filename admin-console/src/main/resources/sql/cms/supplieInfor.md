queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from supplie_infor t
    where t.del = '0'  
    @if(!isEmpty(supplierId)){
        and  t.supplier_id =#supplierId#
    @}
    @if(!isEmpty(supplierName)){
        and  t.supplier_name =#supplierName#
    @}
    @if(!isEmpty(books)){
        and  t.books =#books#
    @}
    order by t.created_time desc
    
    
    
getById
===

    select
    *
    from supplie_infor t
    where t.del = '0'
    and t.id=#id#



batchDelSupplieInforByIds
===

* 批量逻辑删除

    update supplie_infor set del = 1 where supplier_id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update supplie_infor 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,supplier_id = #supplierId#
                ,supplier_name = #supplierName#
                ,books = #books#
                ,position = #position#
                ,address = #address#
                ,city = #city#
                ,postcode = #postcode#
                ,couty = #couty#
                ,tel = #tel#
                ,deposit_bank = #depositBank#
                ,bank_account = #bankAccount#
                ,email = #email#
                ,created_by = #createdBy#
                ,created_time = #createdTime#
                ,remarks = #remarks#
    where id  = #id#
    
    
    
findListByCustom
===

* 自定义查询


    select 
    t.*
    from supplie_infor t
    where t.del = '0'  
    @if(!isEmpty(supplierId)){
        and  t.supplier_id =#supplierId#
    @}
    @if(!isEmpty(supplierName)){
        and  t.supplier_name =#supplierName#
    @}
    @if(!isEmpty(books)){
        and  t.books =#books#
    @}
    @if(!isEmpty(position)){
        and  t.position =#position#
    @}
    @if(!isEmpty(address)){
        and  t.address =#address#
    @}
    @if(!isEmpty(city)){
        and  t.city =#city#
    @}
    @if(!isEmpty(postcode)){
        and  t.postcode =#postcode#
    @}
    @if(!isEmpty(couty)){
        and  t.couty =#couty#
    @}
    @if(!isEmpty(tel)){
        and  t.tel =#tel#
    @}
    @if(!isEmpty(depositBank)){
        and  t.deposit_bank =#depositBank#
    @}
    @if(!isEmpty(bankAccount)){
        and  t.bank_account =#bankAccount#
    @}
    @if(!isEmpty(email)){
        and  t.email =#email#
    @}
    @if(!isEmpty(remarks)){
        and  t.remarks =#remarks#
    @}
    order by t.created_time desc