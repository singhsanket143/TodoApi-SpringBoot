package com.example.todoapispring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("fakeTodoService")
public class FakeTodoService implements TodoService{

    @TimeMonitor
    @Override
    public String doSomething() {
        for(long i = 0; i < 10000000000L; i++) {}
        return "Something";
    } // join point

}
