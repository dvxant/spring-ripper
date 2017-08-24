package com.volnitsky.springripper;

/**
 * Created by volnitsky on 24.04.17.
 */
public class T1000 extends TerminatorQuoter implements Quoter {
    @Override
    public void sayQuote() {
        System.out.println("in T1000");
    }
}
