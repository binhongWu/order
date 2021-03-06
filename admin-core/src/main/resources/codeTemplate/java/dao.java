package ${package};

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  ${basePackage}.entity.*;

/**
 * ${entity.displayName} Dao
 */
\@SqlResource("${entity.system}.${entity.code}")
public interface ${entity.name}Dao extends BaseMapper<${entity.name}>{
    public PageQuery<${entity.name}> queryByCondition(PageQuery query);
    public void batchDel${entity.name}ByIds( List<String> ids);
    public ${entity.name} getById(Object id);
    public Integer updateCustom(${entity.name} model);
    public List<${entity.name}> findListByCustom(${entity.name} model);
}