package elegent_bean;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.validation.Valid;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 12:31 2018/7/21
 * @modify by :
 */
@Controller
@RequestMapping("bean")
public class BeanController extends AbstractBeanController{

    @RequestMapping("addUser")
    public PersonDTO addUser(@Valid PersonDTO personDTO, BindingResult bindingResult){

        checkBindResult(bindingResult);

        return personDTO;
    }



}
