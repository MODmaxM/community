package com.ccy.community.controller;

import com.ccy.community.entity.DiscussPost;
import com.ccy.community.entity.Page;
import com.ccy.community.entity.User;
import com.ccy.community.service.DiscussPostService;
import com.ccy.community.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class HomeController {

    @Resource
    private DiscussPostService discussPostService;

    @Resource
    private UserService userService;


    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndex(Model model, Page page) { //Page封装传进来的分页条件
        // springMVC自动实例化model和page，并将page注入model。
        // 所以thymeleaf可以直接访问page中的数据，不需要手动添加到model中
        // 由dispatcherServlet初始化和注入
        page.setRows(discussPostService.findDiscussPostsRows(0));
        page.setPath("/index");
        
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        return "index";
    }

}
