package elegent_bean;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import javax.validation.constraints.NotNull;

/**
 * @author :lvweihao@outlook.com
 * @description: 一个语义较为清晰的标准化的DTO对象的示例 , 设计lombok类库的使用
 * @date :create in 12:01 2018/7/21
 * @modify by :
 */

// getter setter toString hashcode equals
@Data
// 无参构造
@NoArgsConstructor
// 全参构造
@AllArgsConstructor
// 使set方法的返回值为bean本身 , 即可支持set方法设置的属性的链式调用
@Accessors(chain = true)
// lombok注解 , 创建一个静态的方法,要求传入所有被nonnull注解修饰的字段值
// RequiredArgsConstructor注解会生成一个非空参数的构造方法 , 如果全部字段非空会与全参构造的注解冲突
@RequiredArgsConstructor(staticName = "of")
// 简单builder模式
@Builder
public class PersonDTO {

    /**
     * 名字
     */
    // @NotNull // java校验注解 非空
    @NonNull // lombok非空字段
    private String name;

    /**
     * 兴趣爱好
     */
    // @NotNull
    @NonNull
    private String hobby;

    /**
     * 年龄
     */
    @NotNull
    private int age;

    /**
       * @author : lvweihao@outlook.com
       * @description : 由DTO转化为DO的方法
       * @date : create in 15:30 2018/7/21
       * @param :
       * @modify by :
       */
    public PersonDO forward() {
        /* PersonDO personDO = new PersonDO();
        // 语义层级不同
        BeanUtils.copyProperties(this , personDO);*/
        // return personDO;

        return PersonConverter.forward(this);

    }

    /**
       * @author : lvweihao@outlook.com
       * @description : 由DO转化为DTO的方法
       * @date : create in 15:30 2018/7/21
       * @param :
       * @modify by :
       */
    public PersonDTO backward(PersonDO personDO) {
        return PersonConverter.backward(personDO);
    }

    /**
       * @author : lvweihao@outlook.com
       * @description : DTO转化为对外数据返回
       * @date : create in 15:32 2018/7/21
       * @param :
       * @modify by :
       */
    public PersonOutputDTO output() {
        return PersonConverter.output(this);
    }

    /**
       * @author : lvweihao@outlook.com
       * @description : 将入参转化为DTO
       * @date : create in 15:33 2018/7/21
       * @param :
       * @modify by :
       */
    public PersonDTO input(PersonInputDTO personInputDTO) {
        return PersonConverter.input(personInputDTO);
    }

    // 内部转化器类
    private static class PersonConverter {

        public static PersonDO forward(PersonDTO personDTO) {
            PersonDO personDO = new PersonDO();
            BeanUtils.copyProperties(personDTO , personDO);
            return personDO;
        }

        public static PersonDTO backward(PersonDO personDO) {
            PersonDTO personDTO = new PersonDTO();
            BeanUtils.copyProperties(personDO , personDTO);
            return personDTO;
        }


        // 如果对外返回值需要隐藏属性可以在converter中添加方法 , 生成output对象
        public static PersonOutputDTO output(PersonDTO personDTO) {
            PersonOutputDTO personOutputDTO = new PersonOutputDTO();
            BeanUtils.copyProperties(personDTO , personOutputDTO);

            // 特殊数据需要处理 , 比如时间格式的转换 , 枚举值和描述的转换

            return personOutputDTO;
        }

        // 如果对参数需要进行处理 , 可以在converter中添加方法 , 将input对象转换成合法的dto对象
        public static PersonDTO input(PersonInputDTO personInputDTO) {
            PersonDTO personDTO = new PersonDTO();
            BeanUtils.copyProperties(personInputDTO , personDTO);

            // 特殊入参需要处理

            return personDTO;
        }
    }

    /*// bulid方法
    public static PersonDTO build() {
        return Builder.bulid();
    }

    // 构造器内部类
    private static class Builder {

        // 可能涉及到默认值字段

        public static PersonDTO bulid() {
            // 可能涉及到默认值的填充
            return new PersonDTO();
        }

    }*/

    // builder可以更加优雅一些
    /*public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;

        private String hobby;

        private int age;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder hobby(String hobby) {
            this.hobby = hobby;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public PersonDTO build() {
            return new PersonDTO().setName(name).setHobby(hobby).setAge(age);
        }

    }*/

    // 事实上lombok为我们简化了更多
    // 只需要在类声明上添加@Builder注解 , 就可以自动实现这种简单的bulider模式
    // 当然复杂的builder,模式还需要自己书写

}
