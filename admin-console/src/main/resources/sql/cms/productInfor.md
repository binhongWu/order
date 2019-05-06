queryByCondition
===


    select 
    @pageTag(){
    t.*,a.path picture_url
    @}
    from product_infor t
    left join core_file a on a.BIZ_ID = t.picture
    where t.del = '0'  
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(name)){
        and  t.name =#name#
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
    @if(!isEmpty(score)){
        and  t.score =#score#
    @}
    order by t.updated_time desc
  

findByCode
===
    select
    *
    from product_infor t
    where t.del = '0'
    and t.code =#code#
 
queryWarehouseWarn
===

    select 
    * 
    from product_infor t
    where t.del = '0'
    and (t.exist_stocks-t.min_stocks) < 0 
    
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

    update product_infor set del = 1 where id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update product_infor 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,code = #code#
                ,name = #name#
                ,picture = #picture#
                ,author = #author#
                ,language = #language#
                ,kinds = #kinds#
                ,book_kind = #bookKind#
                ,publish_house = #publishHouse#
                ,publish_date = #publishDate#
                ,introduction = #introduction#
                ,brand = #brand#
                ,score = #score#
                ,product_num = #productNum#
                ,rank = #rank#
                ,ware_id = #wareId#
                ,supplier_id = #supplierId#
                ,in_price = #inPrice#
                ,out_price = #outPrice#
                ,exist_stocks = #existStocks#
                ,min_stocks = #minStocks#
                ,max_stocks = #maxStocks#
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
    @if(!isEmpty(picture)){
        and  t.picture =#picture#
    @}
    @if(!isEmpty(author)){
        and  t.author =#author#
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
    @if(!isEmpty(publishHouse)){
        and  t.publish_house =#publishHouse#
    @}
    @if(!isEmpty(publishDate)){
        and  t.publish_date =#publishDate#
    @}
    @if(!isEmpty(introduction)){
        and  t.introduction =#introduction#
    @}
    @if(!isEmpty(brand)){
        and  t.brand =#brand#
    @}
    @if(!isEmpty(score)){
        and  t.score =#score#
    @}
    @if(!isEmpty(productNum)){
        and  t.product_num =#productNum#
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
    @if(!isEmpty(remarks)){
        and  t.remarks =#remarks#
    @}
    order by t.created_time desc
    
findAll
===

    select
    *
    from product_infor t
    where t.del = '0'
    
statistics
===
*每个仓库的绘本信息 --> 盘点
    
    select
    a.ware_id,t2.`NAME` kinds,a.exist_stocks,t1.`NAME`supplier_id,a.updated_time
    from
    (select
    t.ware_id,t.kinds,sum(t.exist_stocks)exist_stocks,max(t.updated_by)updated_by,max(t.updated_time)updated_time
    from product_infor t
    where t.del = '0'
    group by t.ware_id,t.kinds)a
    left join core_user t1 on t1.ID = a.updated_by
    left join core_dict t2 on t2.`VALUE` = a.kinds
    where t2.TYPE = 'product_infor_kinds'
    order by a.exist_stocks desc