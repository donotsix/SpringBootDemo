package org.com.test.abstractTest;

public abstract class Animal {
    public String shap;

    public String getShap() {
        return shap;
    }

    public void setShap(String shap) {
        this.shap = shap;
    }

    public abstract void eat();

    public abstract void yep();
}
