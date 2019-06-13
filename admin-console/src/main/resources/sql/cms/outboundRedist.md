queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from outbound_redist t
    where t.del = '0'  
    @if(!isEmpty(outRegistId)){
        and  t.out_regist_id =#outRegistId#
    @}
    @if(!isEmpty(outorderId)){
        and  t.outOrder_id =#outorderId#
    @}
    @if(!isEmpty(orderDateStart)){
        and  t.out_regist_date >=#orderDateStart#
    @}
    @if(!isEmpty(orderDateEnd)){
        and  t.out_regist_date < #nextDay(orderDateEnd)#
    @}
    order by t.created_time desc
    
getByOutId
===
      select
      *
      from outbound_redist t
      where t.del = '0'
      and t.outOrder_id =#outorderId#
    
getById
===

    select
    *
    from outbound_redist t
    where t.del = '0'
    and t.id=#id#



batchDelOutboundRedistByIds
===

* 批量逻辑删除

    update outbound_redist set del = 1 where out_regist_id  in( #join(ids)#)
    


updateCustom
===

* 自定义更新

    update outbound_redist 
    set 
        updated_time = #updatedTime#
        ,updated_by = #updatedBy#
                ,out_regist_id = #outRegistId#
                ,outOrder_id = #outorderId#
                ,out_regist_date = #outRegistDate#
                ,code = #code#
                ,supplier_id = #supplierId#
                ,check_by = #checkBy#
                ,check_date = #checkDate#
                ,check_status = #checkStatus#
                ,price = #price#
                ,number = #number#
                ,total = #total#
                ,status = #status#
                ,created_by = #createdBy#
                ,created_time = #createdTime#
                ,remarks = #remarks#
    where id  = #id#
    
    
    
findListByCustom
===

* 自定义查询


    select 
    t.*
    from outbound_redist t
    where t.del = '0'  
    @if(!isEmpty(outRegistId)){
        and  t.out_regist_id =#outRegistId#
    @}
    @if(!isEmpty(outorderId)){
        and  t.outOrder_id =#outorderId#
    @}
    @if(!isEmpty(outRegistDate)){
        and  t.out_regist_date =#outRegistDate#
    @}
    @if(!isEmpty(code)){
        and  t.code =#code#
    @}
    @if(!isEmpty(supplierId)){
        and  t.supplier_id =#supplierId#
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
    @if(!isEmpty(price)){
        and  t.price =#price#
    @}
    @if(!isEmpty(number)){
        and  t.number =#number#
    @}
    @if(!isEmpty(total)){
        and  t.total =#total#
    @}
    @if(!isEmpty(status)){
        and  t.status =#status#
    @}
    @if(!isEmpty(remarks)){
        and  t.remarks =#remarks#
    @}
    order by t.created_time desc
  
rankInfoList
===
* 热销排行


    select
    @pageTag(){
        aa.*
    @}
    from
    (SELECT
    	t.CODE,
    	max( t1.NAME )NAME,
    	max( a.path ) picture_url,
    	max( t1.language) language,
    	max(t1.kinds)kinds,
    	sum(t.number) countNumber
    FROM
    	outbound_redist t 
    	LEFT JOIN product_infor t1 ON t.CODE = t1.code
    	LEFT JOIN core_file a ON a.BIZ_ID = t1.picture
    WHERE
    	t.del = '0' and t1.del ='0' and t.status='0'
        @if(!isEmpty(outRegistDateMin)){
            and  t.out_regist_date >= #outRegistDateMin#
        @}
        @if(!isEmpty(outRegistDateMax)){
            and  t.out_regist_date <#nextDay(outRegistDateMax)#
        @}
    GROUP BY
    	t.CODE)aa
    order by aa.countNumber desc