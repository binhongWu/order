queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from customer_infor t
    where t.del = '0'  
    @if(!isEmpty(clientCode)){
        and  t.client_code =#clientCode#
    @}
    @if(!isEmpty(name)){
        and  t.name =#name#
    @}
    @if(!isEmpty(level)){
        and  t.level =#level#
    @}
    order by t.created_time desc
    
findByCode
===

    select
    *
    from customer_infor t
    where t.del = '0'
    and t.client_code =#clientId#
    
getById
===

    select
    *
    from customer_infor t
    where t.del = '0'
    and t.id=#id#



batchDelCustomerInforByIds
===

* 批量逻辑删除

    update customer_infor set del = 1 where client_id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update customer_infor 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,client_id = #clientId#
                ,client_code = #clientCode#
                ,name = #name#
                ,sex = #sex#
                ,tel = #tel#
                ,email = #email#
                ,intergral = #intergral#
                ,level = #level#
                ,created_by = #createdBy#
                ,created_time = #createdTime#
                ,remarks = #remarks#
    where id  = #id#
    
    
    
findListByCustom
===

* 自定义查询


    select 
    t.*
    from customer_infor t
    where t.del = '0'  
    @if(!isEmpty(clientId)){
        and  t.client_id =#clientId#
    @}
    @if(!isEmpty(clientCode)){
        and  t.client_code =#clientCode#
    @}
    @if(!isEmpty(name)){
        and  t.name =#name#
    @}
    @if(!isEmpty(sex)){
        and  t.sex =#sex#
    @}
    @if(!isEmpty(tel)){
        and  t.tel =#tel#
    @}
    @if(!isEmpty(email)){
        and  t.email =#email#
    @}
    @if(!isEmpty(intergral)){
        and  t.intergral =#intergral#
    @}
    @if(!isEmpty(level)){
        and  t.level =#level#
    @}
    @if(!isEmpty(remarks)){
        and  t.remarks =#remarks#
    @}
    order by t.created_time desc