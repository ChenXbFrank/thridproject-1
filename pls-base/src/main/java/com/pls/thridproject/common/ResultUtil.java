package com.pls.thridproject.common;

import com.pls.thridproject.model.ExceptionEnum;
import com.pls.thridproject.model.ResultVO;

/**
 * Created by zhengping.zhu
 * on 2017/5/7.
 * 统一返回结果集
 */
public class ResultUtil {

    /**
     * 返回成功，传入返回体具体出參
     * @param object
     * @return
     */
    public static ResultVO success(Object object){
        ResultVO result = new ResultVO();
        result.setCode(200);
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    /**
     * 提供给部分不需要出參的接口
     * @return
     */
    public static ResultVO success(){
        return success(null);
    }

    /**
     * 自定义错误信息
     * @param code
     * @param msg
     * @return
     */
    public static ResultVO error(Integer code,String msg){
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 返回异常信息，在已知的范围内
     * @param exceptionEnum
     * @return
     */
    public static ResultVO error(ExceptionEnum exceptionEnum){
        ResultVO result = new ResultVO();
        result.setCode(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMsg());
        result.setData(null);
        return result;
    }
}
