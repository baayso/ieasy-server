package com.baayso.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baayso.springboot.common.CommonService;
import com.baayso.springboot.entity.TestUser;

/**
 * 测试业务。
 *
 * @author ChenFangjie (2016/4/1 16:27)
 * @since 1.0.0
 */
@Service
public class TestUserService extends CommonService<TestUser, Long> {

    @Transactional
    public boolean create() {
        TestUser testUser1 = new TestUser();
        testUser1.setName("code-1");

        super.save(testUser1);

        TestUser testUser2 = new TestUser();
        testUser2.setName("code-2");

        super.save(testUser2);

        return true;
    }

    public boolean creates() {
        TestUser testUser3 = new TestUser();
        // testUser3.setName("code-3");

        TestUser testUser4 = new TestUser();
        // testUser4.setName("code-4");

        List<TestUser> list = new ArrayList<>();
        list.add(testUser3);
        list.add(testUser4);

        super.saves(list);

        return true;
    }

}
