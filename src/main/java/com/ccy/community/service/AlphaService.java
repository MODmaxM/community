package com.ccy.community.service;

import com.ccy.community.dao.DiscussPostMapper;
import com.ccy.community.dao.UserMapper;
import com.ccy.community.dao.test.AlphaDao;
import com.ccy.community.entity.DiscussPost;
import com.ccy.community.entity.User;
import com.ccy.community.util.CommuntiyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Date;

@Service
//@Scope("prototype") //默认单例模式，可以手动修改为多例模式
public class AlphaService {

    @Resource
    private AlphaDao alphaDao;

    @Resource
    private UserMapper userMapper;

    @Resource
    private DiscussPostMapper discussPostMapper;

    @Resource
    private TransactionTemplate transactionTemplate;

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

    // REQUIRED:支持当前事务（外部事物），如果不存在就创建新事务，如果A没有事务，就创建B
    // REQUIRES_NEW：创建一个新事务，并且暂停当前事务，A调B，A暂停
    // NESTED：如果当前已有事务，A调B，则嵌套在该事务中执行（独立的提交和回滚），如果不存在外部事务就和REQUIRED一样
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Object save1() {
        // 新增用户
        User user = new User();
        user.setUsername("alpha");
        user.setSalt(CommuntiyUtil.generateUUID().substring(0, 5));
        user.setPassword(CommuntiyUtil.md5("123" + user.getSalt()));
        user.setEmail("alp@11.com");
        user.setHeaderUrl("http://www.123.png");
        user.setCreateTime(new Date());
        userMapper.insertUser(user);

        // 新增帖子
        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle("新人发帖");
        post.setContent("Hello");
        post.setCreateTime(new Date());
        post.setType(0);
        post.setStatus(0);
        post.setCreateTime(new Date());
        discussPostMapper.insertDiscussPost(post);

        Integer.valueOf("abc");

        return "ok";
    }

    // 可以只管理其中的某一部分
    public Object save2() {
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        return transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                // 新增用户
                User user = new User();
                user.setUsername("beta");
                user.setSalt(CommuntiyUtil.generateUUID().substring(0, 5));
                user.setPassword(CommuntiyUtil.md5("123" + user.getSalt()));
                user.setEmail("alp@11.com");
                user.setHeaderUrl("http://www.123.png");
                user.setCreateTime(new Date());
                userMapper.insertUser(user);

                // 新增帖子
                DiscussPost post = new DiscussPost();
                post.setUserId(user.getId());
                post.setTitle("新人发帖");
                post.setContent("Hello");
                post.setCreateTime(new Date());
                post.setType(0);
                post.setStatus(0);
                post.setCreateTime(new Date());
                discussPostMapper.insertDiscussPost(post);

                Integer.valueOf("abc");

                return "ok";
            }
        });
    }
}
