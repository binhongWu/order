queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from inventory_order t
    where t.del = '0'  
    @if(!isEmpty(id)){
        and  t.id =#id#
    @}
    @if(!isEmpty(inventoryId)){
        and  t.inventory_id =#inventoryId#
    @}
    order by t.created_time desc

isSynchronization
===
* 盘点初始信息
    
    
    select 
    t.*
    from inventory_order t
    where t.del = 0
    and t.created_time >= DATE_FORMAT(CURDATE(),'%Y-%m-%d %H:%i:%s');
    
    
getById
===

    select
    *
    from inventory_order t
    where t.del = '0'
    and t.id=#id#



batchDelInventoryOrderByIds
===

* 批量逻辑删除

    update inventory_order set del = 1 where id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update inventory_order 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,inventory_id = #inventoryId#
                ,code = #code#
                ,language = #language#
                ,kinds = #kinds#
                ,book_kind = #bookKind#
                ,exist_stocks = #existStocks#
                ,inventory_stocks = #inventoryStocks#
                ,created_by = #createdBy#
                ,created_time = #createdTime#
                ,remarks = #remarks#
    where id  = #id#
    
    
    
findListByCustom
===

* 自定义查询


    select 
    t.*
    from inventory_order t
    where t.del = '0'  
    @if(!isEmpty(inventoryId)){
        and  t.inventory_id =#inventoryId#
    @}
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(language)){
        and  t.language =#language#
    @}
    @if(!isEmpty(kinds)){
        and  t.kinds =#kinds#
    @}
    @if(!isEmpty(bookKind)){
        and  t.book_kind =#bookKind#
    @}
    @if(!isEmpty(existStocks)){
        and  t.exist_stocks =#existStocks#
    @}
    @if(!isEmpty(inventoryStocks)){
        and  t.inventory_stocks =#inventoryStocks#
    @}
    @if(!isEmpty(remarks)){
        and  t.remarks =#remarks#
    @}
    order by t.created_time desc