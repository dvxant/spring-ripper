package com.volnitsky.springripper;

import com.volnitsky.springripper.BeanFactoryPostProcessor.DeprecatedClass;
import com.volnitsky.springripper.BeanPostProcessor.InjectRandomInt;
import com.volnitsky.springripper.BeanPostProcessor.Profiling;
import com.volnitsky.springripper.ApplicationListener.PostProxy;

import javax.annotation.PostConstruct;

/**
 * Created by volnitsky on 24.04.17.
 */
@DeprecatedClass(newImpl = T1000.class)
@Profiling
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 3, max =8 )
    private int random;

    private String message;

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TerminatorQuoter() {
        System.out.println("PHASE 1");
    }

    @PostConstruct
    public void init(){
        System.out.println("PHASE 2");
    }

    @PostProxy
    public void sayQuote() {
        System.out.println("PHASE 3");
        for (int i=0; i<random; i++)
            System.out.println(getMessage());
}
}
