package cn.edu.njtech.seckill.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class LogTest {
    @GetMapping("/{name}/{age}")
    public void getName(@PathVariable String name, @PathVariable String age) {
        System.out.println(name + age);
    }
}
