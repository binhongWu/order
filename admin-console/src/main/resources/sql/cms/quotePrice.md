queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from quote_price t
    where t.del = '0'  
    @if(!isEmpty(quoteId)){
        and  t.quote_id =#quoteId#
    @}
    @if(!isEmpty(quoteDate)){
        and  t.quote_date =#quoteDate#
    @}
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    order by t.created_time desc
    
    
    
getById
===

    select
    *
    from quote_price t
    where t.del = '0'
    and t.id=#id#



batchDelQuotePriceByIds
===

* 批量逻辑删除

    update quote_price set del = 1 where quote_id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update quote_price 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,quote_id = #quoteId#
                ,quote_date = #quoteDate#
                ,code = #code#
                ,price = #price#
                ,quote_by = #quoteBy#
                ,created_by = #createdBy#
                ,created_time = #createdTime#
                ,remarks = #remarks#
    where id  = #id#
    
    
    
findListByCustom
===

* 自定义查询


    select 
    t.*
    from quote_price t
    where t.del = '0'  
    @if(!isEmpty(quoteId)){
        and  t.quote_id =#quoteId#
    @}
    @if(!isEmpty(quoteDate)){
        and  t.quote_date =#quoteDate#
    @}
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(price)){
        and  t.price =#price#
    @}
    @if(!isEmpty(quoteBy)){
        and  t.quote_by =#quoteBy#
    @}
    @if(!isEmpty(remarks)){
        and  t.remarks =#remarks#
    @}
    order by t.created_time desc