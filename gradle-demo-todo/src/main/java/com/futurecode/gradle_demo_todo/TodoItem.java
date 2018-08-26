package com.futurecode.gradle_demo_todo;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 23:19 18.5.3
 * @modify by :
 */
public class TodoItem {

    /**
     * 待办事项名称
     */
    private String todoName;

    /**
     * 是否完成的标志
     */
    private Boolean hasDone;

    public TodoItem(String todoName, Boolean hasDone) {
        this.todoName = todoName;
        this.hasDone = hasDone;
    }

    public TodoItem() {
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public Boolean getHasDone() {
        return hasDone;
    }

    public void setHasDone(Boolean hasDone) {
        this.hasDone = hasDone;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "todoName='" + todoName + '\'' +
                ", hasDone=" + hasDone +
                '}';
    }
}
