package com.ibeetl.admin.core.util;

/**
 * Description:ID生成器
 *
 * @author guocp
 * @date 2018/9/29
 */
public class IdGenerator {

  /**
   * Twitter_Snowflake ID生成器
   */
  private static SnowflakeId snowflakeId = new SnowflakeId();

  /**
   * @Description: 生成ID
   * @return
   * @author guocp
   * @date 2018年4月13日
   */
  public static String nextStrId() {
    return String.valueOf(snowflakeId.nextId());
  }

  public static long nextId() {
    return snowflakeId.nextId();
  }
}
