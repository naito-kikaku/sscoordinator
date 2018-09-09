package naitokikaku.sscoordinator.presentation.controller.advice;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class DirectFieldAccessAdvice {
    static

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
        binder.setAllowedFields("to be specified");
        binder.registerCustomEditor(Object.class, new StringTrimmerEditor(true));
    }
}
