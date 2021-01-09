package com.xzs.vhr.utils;

import com.xzs.vhr.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

public class HrUtils {

    public static Hr getCurrentHr(){
        return (Hr)SecurityContextHolder.getContext().getAuthentication().getPrincipal();//返回当前用户
    }
}
