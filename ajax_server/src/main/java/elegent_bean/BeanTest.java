package elegent_bean;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 12:05 2018/7/21
 * @modify by :
 */
public class BeanTest {

    public static void main(String[] args) {

        // PersonDTO personDTO = new PersonDTO("吕伟豪", "篮球", 23);
        // bean的属性设置非常麻烦 , 如果能作链式编程就会省去很多代码
        /*PersonDTO personDTO = new PersonDTO();
        personDTO.setName("吕伟豪");
        personDTO.setHobby("篮球");
        personDTO.setAge(23);*/

        // 使用lombok的注解实现链式编程 , 相当于将set方法添加返回值 , 返回this
        // 链式编程要优雅一些
        // PersonDTO personDTO = new PersonDTO().setName("吕伟豪").setHobby("篮球").setAge(23);
        // 如果Person对象复杂一些 , 这是就不适合使用new 关键字进行创建 ,
        // PersonDTO对象可能存在默认值 , 我们可以在builder中赋予默认值 , 创建对象
        // 在创建完成后再设置自己需要的属性
        // PersonDTO personDTO = PersonDTO.build().setName("吕伟豪").setHobby("篮球").setAge(23);

        // lombok注解
        // PersonDTO personDTO = PersonDTO.of("吕伟豪", "篮球").setAge(23);

        // 新builder
        PersonDTO personDTO = PersonDTO.builder().name("吕伟豪").hobby("篮球").age(23).build();


        // PersonDO personDO = new PersonDO();
        // 赋值不够elegant
        /*personDO.setName(personDTO.getName());
        personDO.setHobby(personDTO.getHobby());
        personDO.setAge(personDTO.getAge());*/

        // spring提供beanutils , 浅拷贝方法 , 适合单标操作
        // 这行代码语义不清 , 且与上面的代码不属于一个层级
        // BeanUtils.copyProperties(personDTO , personDO);
        // 抽离方法 , 语义清晰
        // 但是此种方式使得对bean的操作依赖外部 , 不推荐
        // forward(personDTO , personDO);
        // 应在dto内部做这件事

        // getDO(personDTO , personDO);
        System.out.println(personDTO);
        System.out.println(personDTO.forward());

    }

    /*private static void getDO(@Valid PersonDTO personDTO, PersonDO personDO , BindingResult bindingResult) {

    }*/

    private static void forward(PersonDTO personDTO, PersonDO personDO) {
        BeanUtils.copyProperties(personDTO , personDO);
    }

}
