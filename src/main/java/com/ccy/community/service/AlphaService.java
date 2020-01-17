package com.ccy.community.service;

import com.ccy.community.dao.test.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype") //默认单例模式，可以手动修改为多例模式
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    public String find() {
        return alphaDao.select();
    }

    public AlphaService() {
        System.out.println("实例化AlphaService");
    }

    @PostConstruct //在构造器之后调用
    public void init() {
        System.out.println("初始化AlphaService");
    }

    @PreDestroy //在销毁对象之前调用
    public void destroy() {
        System.out.println("销毁AlphaService");
    }

}
