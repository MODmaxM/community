package com.ccy.community.controller;

import com.ccy.community.annotation.LoginRequired;
import com.ccy.community.entity.User;
import com.ccy.community.service.LikeService;
import com.ccy.community.service.UserService;
import com.ccy.community.util.CommuntiyUtil;
import com.ccy.community.util.HostHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Value("${community.path.upload}")
    private String uploadPath;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Resource
    private UserService userService;

    @Resource
    private LikeService likeService;

    @Resource
    private HostHolder hostHolder;

    @LoginRequired
    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public String getSettingPage() {
        return "/site/setting";
    }

    @LoginRequired
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String uploadHeader(MultipartFile headerImage, Model model) {
        if (headerImage == null) {
            model.addAttribute("error", "您还没有选择图片");
            return "/site/setting";
        }

        String fileName = headerImage.getOriginalFilename();
        if (fileName.lastIndexOf(".") == -1) {
            model.addAttribute("error", "文件格式不正确");
            return "/site/setting";
        }
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffix);
        if (!suffix.equals(".jpg") && !suffix.equals(".png") && !suffix.equals(".jpeg")) {
            model.addAttribute("error", "文件格式不正确");
            return "/site/setting";
        }

        fileName = CommuntiyUtil.generateUUID() + suffix;
        File dest = new File(uploadPath + "/" + fileName);
        try {
            headerImage.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException("上传文件失败，服务器发送异常", e);
        }

        // 更新当前用户的头像路径（web路径）
        // http://localhost:8080/community/user/header/xxx.png
        User user = hostHolder.getUser();
        String headUrl = domain + contextPath + "/user/header/" + fileName;
        userService.updateHeader(user.getId(), headUrl);

        return "redirect:/index";
    }

    @RequestMapping(path = "/header/{fileName}", method = RequestMethod.GET)
    public void getHeader(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        // 服务器存放路径
        fileName = uploadPath + "/" + fileName;
        // 文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 响应图片
        response.setContentType("image/suffix");
        try (ServletOutputStream os = response.getOutputStream();
             FileInputStream is = new FileInputStream(fileName);) {
            byte[] buffer = new byte[1024];
            int b = 0;
            while ((b = is.read(buffer)) != -1) {
                os.write(buffer, 0, b);
            }
        } catch (IOException e) {
            LOGGER.error("读取头像失败：" + e.getMessage());
        }
    }

    @LoginRequired
    @RequestMapping(path = "/updatePwd", method = RequestMethod.POST)
    public String updatePassword(String oldPassword, String newPassword) {
        System.out.println(oldPassword);
        System.out.println(newPassword);
        User user = hostHolder.getUser();
        if (user.getPassword().equals(CommuntiyUtil.md5(oldPassword + user.getSalt()))) {
            userService.updatePassword(user.getId(), CommuntiyUtil.md5(newPassword + user.getSalt()));
        }
        return "redirect:/index";
    }

    // 个人主页
    @RequestMapping(path = "/profile/{userId}", method = RequestMethod.GET)
    public String getProfilePage(@PathVariable("userId") int userId, Model model) {
        User user = userService.findUserById(userId);
        if (user == null) {
            throw new RuntimeException("该用户不存在");
        }

        model.addAttribute("user", user);
        int likeCount = likeService.findUserLikeCount(userId);
        model.addAttribute("likeCount", likeCount);
        return "/site/profile";
    }
}
