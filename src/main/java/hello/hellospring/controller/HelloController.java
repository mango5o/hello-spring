package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller // Spring MVC의 컨트롤러. 주로 View 를 반환하기 위해 사용.
public class HelloController {

    @GetMapping("hello") // 웹 어플리케이션에서 hello를 출하면 하단의 메서드를 호출해줌.
    public String hello (Model model) { // MVC 의 M = model.
        model.addAttribute("data","hello!");
        return "hello"; // Controller에서 리턴값으로 문자를 반환하면 View resolver에서 찾아 화면으로 송출함.
    }

    @GetMapping("hello-mvc")
    public String helloMVC (@RequestParam("name") String name, Model model)  {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http 의 body 부분에 내가 직접 값을 넣겠다. 라는 의미
    public String helloString (@RequestParam("name")String name) {
        return "hello " + name; // View없이 "hello spring" 문자 그대로 출력됨
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

