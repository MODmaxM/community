package com.ccy.community.controller;

import com.ccy.community.service.AlphaService;
import com.ccy.community.util.CommuntiyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot.";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
//        //获取请求数据
//        System.out.println(request.getMethod());
//        System.out.println(request.getServletPath());
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String name = headerNames.nextElement();
//            String value = request.getHeader(name);
//            System.out.println(name + " : " + value);
//        }
//        System.out.println(request.getParameter("code"));
        try {
            response.sendRedirect("/community/alpha/emp");
            System.out.println("重定向");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GET请求
    //查询所有学生 /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }


    //查询一个学生 /students/123 在请求路径里
    @RequestMapping(path = "/students/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable(name = "id") int id) {
        System.out.println(id);
        return "a student";
    }

    // 保存post
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "age") int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 响应html格式
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "zhangsan");
        modelAndView.addObject("age", 12);
        modelAndView.setViewName("demo/view");
        return modelAndView;
    }

    // 响应html格式
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "南京大学");
        model.addAttribute("age", 123);
        return "demo/view";
    }

    // 响应JSON数据（异步请求）
    // Java对象 --> JSON字符串 --> JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmp() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "zhangsan");
        emp.put("age", 23);
        emp.put("salary", 8000);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "wangwu");
        emp.put("age", 32);
        emp.put("salary", 12000);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "lisi");
        emp.put("age", 16);
        emp.put("salary", 4000);
        list.add(emp);
        return list;
    }

    //cookie演示
    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody //返回json
    public String setCookie(HttpServletResponse response) {
        // 创建cookie
        Cookie cookie = new Cookie("code", CommuntiyUtil.generateUUID());
        // 设置cookie生效范围
        cookie.setPath("/community/alpha");
        // 设置cookie生存时间，单位是秒，默认关掉浏览器就结束
        cookie.setMaxAge(60 * 10);
        response.addCookie(cookie);
        return "cookie test";
    }

    // session
    @RequestMapping(path = "/session/set", method = RequestMethod.GET)
    @ResponseBody           // springmvc自动添加session
    public String setSession(HttpSession session) {
        session.setAttribute("id", 1);
        session.setAttribute("name", "ccy");
        return "session";
    }

    @RequestMapping(path = "/session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session) {
        System.out.println(session.getId());
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }

    // ajax示例
    @RequestMapping(path = "/ajax", method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return CommuntiyUtil.getJSONString(0, "操作成功");
    }

}
