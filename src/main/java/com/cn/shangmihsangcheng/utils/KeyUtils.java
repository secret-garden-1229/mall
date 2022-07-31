package com.cn.shangmihsangcheng.utils;

import java.util.Random;
import java.util.UUID;

public class KeyUtils {

       // 生成唯一的主键 格式: 时间+随机数
        public static synchronized String getUniqueKey() {
            Random random = new Random();
            Integer number = random.nextInt(900000) + 100000;
            return   System.currentTimeMillis()+String.valueOf(number);
        }

        /**
         *
         * @Description: 生成唯一的主键 格式: 19位
         * @Param:
         * @return: java.lang.String
         */
        public static synchronized String getUUIDKey(String str) {
            String uuid= System.currentTimeMillis()+ UUID.randomUUID().toString().replaceAll("-","").substring(0,6);
            return str + uuid;
        }



}
