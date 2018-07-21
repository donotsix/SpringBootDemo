package elegent_bean;

import org.springframework.validation.BindingResult;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 12:36 2018/7/21
 * @modify by :
 */
public class AbstractBeanController {

    protected void checkBindResult(BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RuntimeException("参数异常");
        }

    }

}
