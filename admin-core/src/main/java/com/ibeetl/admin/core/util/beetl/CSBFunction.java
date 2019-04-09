package com.ibeetl.admin.core.util.beetl;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.C
 * @date 2018/10/18
 */
public interface CSBFunction {

    String getName(String key);

    Object queryJson(String key);

    List<Map<String,Object>> all();
}
