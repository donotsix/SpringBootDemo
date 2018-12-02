package org.com.test.spring;

public class ActionBean {
    
    PersonAction personAction;


    public void setPersonAction(PersonAction personAction) {
        this.personAction = personAction;
    }


    public void say() {
        personAction.sayHello();
    }
}
