package web.mvc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionAdive {
	 @ExceptionHandler(MyErrorException.class)
	    public ModelAndView handleMyError(MyErrorException e) {
	        log.error("MyErrorException ¹ß»ý: status={}, msg={}",
	                  e.getErrorCode().getStatus(), e.getErrorCode().getMsg());

	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("errorpage");                          // WEB-INF/views/errorpage.jsp
	        mv.addObject("status", e.getErrorCode().getStatus()); 
	        mv.addObject("msg", e.getErrorCode().getMsg());       
	        return mv;
	    }

}
