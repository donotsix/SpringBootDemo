package org.com.lamuda;

import java.util.function.Predicate;

public class PredicateImpl implements Predicate<String>{

    @Override
    public boolean test(String t) {
        
        return null==t;
    }
    
    public boolean test(Integer t) {
        
        return null==t;
    }

}
