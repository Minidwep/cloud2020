package com.minidwep.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.minidwep.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handleException(BlockException exception){
        return new CommonResult(444,"自定义错误返回");
    }
    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(444,"222自定义错误返回");
    }

}
