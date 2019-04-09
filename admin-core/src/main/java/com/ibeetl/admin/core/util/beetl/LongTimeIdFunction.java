package com.ibeetl.admin.core.util.beetl;

import com.ibeetl.admin.core.util.IdGenerator;
import org.beetl.core.Context;
import org.beetl.core.Function;

public class LongTimeIdFunction implements Function {

    @Override
    public Long call(Object[] arg0, Context arg1) {
        return IdGenerator.nextId();
    }

}
