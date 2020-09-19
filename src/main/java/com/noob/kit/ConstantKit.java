package com.noob.kit;

public class ConstantKit {
    // 设置删除标记为真
    public static final Integer DEL_FLAG_TRUE = 1;

    // 设置删除标志为假
    public static final Integer DEL_FLAG_FALSE = 0;

    // redis 存储 token 设置的过期时间
    public static final Integer TOKEN_EXPIRE_TIME = 60000 * 2;

    // 设置可以重置 token 过期时间的时间界限
    public static final Integer TOKEN_RESET_TIME = 10000 * 10000;
}
