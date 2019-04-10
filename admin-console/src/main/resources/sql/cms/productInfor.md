queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from product_infor t
    where t.del = '0'  
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(name)){
        and  t.name =#name#
    @}
    @if(!isEmpty(rank)){
        and  t.rank =#rank#
    @}
    order by t.created_time desc
    
    
    
getById
===

    select
    *
    from product_infor t
    where t.del = '0'
    and t.id=#id#



batchDelProductInforByIds
===

* 批量逻辑删除

    update product_infor set del = 1 where code  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update product_infor 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,code = #code#
                ,name = #name#
                ,foreign_name = #foreignName#
                ,kinds = #kinds#
                ,format = #format#
                ,pages = #pages#
                ,size = #size#
                ,weight = #weight#
                ,brand = #brand#
                ,score = #score#
                ,rank = #rank#
                ,ware_id = #wareId#
                ,supplier_id = #supplierId#
                ,in_price = #inPrice#
                ,out_price = #outPrice#
                ,exist_stocks = #existStocks#
                ,min_stocks = #minStocks#
                ,max_stocks = #maxStocks#
                ,publish_house = #publishHouse#
                ,created_by = #createdBy#
                ,created_time = #createdTime#
                ,remarks = #remarks#
    where id  = #id#
    
    
    
findListByCustom
===

* 自定义查询


    select 
    t.*
    from product_infor t
    where t.del = '0'  
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(name)){
        and  t.name =#name#
    @}
    @if(!isEmpty(foreignName)){
        and  t.foreign_name =#foreignName#
    @}
    @if(!isEmpty(kinds)){
        and  t.kinds =#kinds#
    @}
    @if(!isEmpty(format)){
        and  t.format =#format#
    @}
    @if(!isEmpty(pages)){
        and  t.pages =#pages#
    @}
    @if(!isEmpty(size)){
        and  t.size =#size#
    @}
    @if(!isEmpty(weight)){
        and  t.weight =#weight#
    @}
    @if(!isEmpty(brand)){
        and  t.brand =#brand#
    @}
    @if(!isEmpty(score)){
        and  t.score =#score#
    @}
    @if(!isEmpty(rank)){
        and  t.rank =#rank#
    @}
    @if(!isEmpty(wareId)){
        and  t.ware_id =#wareId#
    @}
    @if(!isEmpty(supplierId)){
        and  t.supplier_id =#supplierId#
    @}
    @if(!isEmpty(inPrice)){
        and  t.in_price =#inPrice#
    @}
    @if(!isEmpty(outPrice)){
        and  t.out_price =#outPrice#
    @}
    @if(!isEmpty(existStocks)){
        and  t.exist_stocks =#existStocks#
    @}
    @if(!isEmpty(minStocks)){
        and  t.min_stocks =#minStocks#
    @}
    @if(!isEmpty(maxStocks)){
        and  t.max_stocks =#maxStocks#
    @}
    @if(!isEmpty(publishHouse)){
        and  t.publish_house =#publishHouse#
    @}
    @if(!isEmpty(remarks)){
        and  t.remarks =#remarks#
    @}
    order by t.created_time desc