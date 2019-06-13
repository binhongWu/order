queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from core_dict t
    where del_flag=0  

    @if(!isEmpty(value)){
        and  t.VALUE like #"%"+value+"%"#
    @}
    @if(!isEmpty(name)){
        and  t.NAME like #"%"+name+"%"#
    @}
    @if(!isEmpty(typeName)){
        and  t.TYPE_NAME like #"%"+typeName+"%"#
    @}
    @if(!isEmpty(type)){
        and  t.TYPE like #"%"+type+"%"#
    @}
    @if(!isEmpty(parent)){
        and  t.PARENT like #"%"+parent+"%"#
    @}
	@pageIgnoreTag(){
		order by id  desc
	@}
	    
    
    

batchDelCoreDictByIds
===

* 批量逻辑删除

    update core_dict set del_flag = 1 where id in( #join(ids)#)
    
    
findDictList
===

    select a.* 
    from  core_dict a where 1=1 
     @if(isNotEmpty(type)){
        and a.type = #type#
     @}
     @if(isNotEmpty(name)){
        and a.vaule = #name#
     @}

findEmail
===
* 查找预警信息接收人员的邮箱

    select
    t.value
    from core_dict t
    where t.type='warehouse_warn_email'
    