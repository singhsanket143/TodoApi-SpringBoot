package com.example.todoapispring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("fakeTodoService")
public class FakeTodoService implements TodoService{

    @TimeMonitor
    public String doSomething() {
        for(int i = 0; i < 1000000000; i++) {}
        return "Something";
    }

}
