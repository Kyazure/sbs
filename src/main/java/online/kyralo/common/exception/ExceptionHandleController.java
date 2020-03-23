package online.kyralo.common.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: guohezuzi
 * \* Date: 2019-01-22
 * \* Time: 下午3:43
 * \* Description:异常处理控制器
 * \
 */
@RestController
@ControllerAdvice
public class ExceptionHandleController {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelMap error(MissingServletRequestParameterException e) {
        ModelMap modelMap=new ModelMap();
        modelMap.put("message",e.getMessage());
        return modelMap;
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelMap daoExceptionHandle(DataAccessException e) {
        e.printStackTrace();
        ModelMap modelMap = new ModelMap();
        modelMap.put("message", "date access error!");
        return modelMap;
    }

}
