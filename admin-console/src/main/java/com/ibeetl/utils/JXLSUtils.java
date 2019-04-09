package com.ibeetl.utils;

import org.jxls.reader.XLSReadMessage;

import java.util.List;

/**
 * @Auther: xiaohang
 * @Date: 2018-10-30 14:30
 * @Description:
 */
public class JXLSUtils {

    /*xlsReader 设计有问题，还需要通过解析msg知道错误位置在哪里*/
    public static String parseXLSReadMessage(XLSReadMessage msg) {
//        String message = "Can't read cell " + getCellName(mapping, rowShift) + " on " + cursor.getSheetName() + " spreadsheet";
        String str = msg.getMessage();
        int start = "Can't read cell ".length();
        int end = str.indexOf("on");
        return str.substring(start,end);
    }

    /**
     * 中投打款导出省市格式转换
     * 去除省\自治区\市后缀
     * @param cityCode
     * @return
     */
    public static String ProvinceNameRemoveSuffix(String provinceName){
        if (StringUtils.isNotBlank(provinceName)) {
            if (provinceName.lastIndexOf("省") != -1 || provinceName.lastIndexOf("自治区") != -1 || provinceName.lastIndexOf("市") != -1) {
                if (provinceName.lastIndexOf("省") != -1) {
                    provinceName = provinceName.substring(0, provinceName.lastIndexOf("省"));
                } else if (provinceName.lastIndexOf("自治区") != -1) {
                    provinceName = provinceName.substring(0, provinceName.lastIndexOf("自治区"));
                } else if (provinceName.lastIndexOf("市") != -1) {
                    provinceName = provinceName.substring(0, provinceName.lastIndexOf("市"));
                }
            }
        }
        return provinceName;
    }

    /**
     * 中投打款导出省市格式转换
     * 去除市后缀
     * @param cityCode
     * @return
     */
    public static String CityNameRemoveSuffix(String cityName){
        if (StringUtils.isNotBlank(cityName)) {
            if (cityName.lastIndexOf("市") != -1) {
                cityName = cityName.substring(0, cityName.lastIndexOf("市"));
            }
        }
        return cityName;
    }
}
