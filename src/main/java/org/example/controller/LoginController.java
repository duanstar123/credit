/*
 *中北大学软件学院
 *2413040403 段璎芮
 */
package org.example.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录控制器
 * @author 段璎芮
 * @version 1.0
 */
@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String creditLoginPage() {
        return "login";
    }
}