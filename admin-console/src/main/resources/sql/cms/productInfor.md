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

* 根据编码查找

    select
    *
    from product_infor t
    where t.del = '0'
    and t.code =#code#
 
queryWarehouseWarn
===

* 检索现有库存 < 最小库存

    select 
    * 
    from product_infor t
    where t.del = '0'
    and (t.exist_stocks-t.min_stocks) < 0 
    
getById
===

    select
    t.*,a.path picture_url
    from product_infor t
    left join core_file a on a.BIZ_ID = t.picture
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

* 按条件查找全部数据


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
* 每个仓库的绘本信息 --> 盘点
      
    select 
    @pageTag(){
    t.ware_id,t.`code`,t.`name`,t2.`NAME` kinds,t.book_kind,t.exist_stocks,t.max_stocks,t.min_stocks,t3.number rank,t4.number remarks
    @}
    from product_infor t 
    left join (select
            t1.code,sum(number)number
            from sales_order t1
            where t1.del='0' and t1.finished_status = '0'
            and t1.created_time >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
            group by t1.`code`)t3 on t3.`CODE` = t.`code`
    left join core_dict t2 on t2.`VALUE` = t.kinds
    left join (select
            code,sum(number)number
            from purchase_order
            where del='0' and finish_condition = '1'
            and order_date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
            group by `code`)t4 on t4.`code`= t.`code`
    where t.del = 0 and t2.TYPE = 'product_infor_kinds'
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(name)){
        and  t.name =#name#
    @}
    @if(!isEmpty(wareId)){
        and  t.ware_id =#wareId#
    @}
    @if(!isEmpty(kinds)){
        and  t.kinds =#kinds#
    @}
    @if(!isEmpty(bookKind)){
        and  t.book_kind =#bookKind#
    @}
    order by t.exist_stocks desc