package ${package};

import java.util.List;
import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import ${basePackage}.dao.${entity.name}Dao;
import ${basePackage}.entity.${entity.name};
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * ${entity.displayName} Service
 */

\@Service
\@Transactional
public class ${entity.name}Service extends BaseService<${entity.name}>{

    \@Autowired private ${entity.name}Dao ${entity.code}Dao;
    \@Autowired private CorePlatformService platformService;

    public PageQuery<${entity.name}>queryByCondition(PageQuery query){
        PageQuery ret =  ${entity.code}Dao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDel${entity.name}(List<String> ids){
        try {
            ${entity.code}Dao.batchDel${entity.name}ByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除${entity.displayName}失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    \@Override
    public boolean updateTemplate(${entity.name} model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    \@Override
    public boolean update(${entity.name} model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(${entity.name} model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return ${entity.code}Dao.updateCustom(model) > 0;
    }

    \@Override
    public boolean save(${entity.name} model) {
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public ${entity.name} getById(Object id){
        return ${entity.code}Dao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<${entity.name}> findListByCustom(${entity.name} model) {
        return ${entity.code}Dao.findListByCustom(model);
    }
}