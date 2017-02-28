package com.baayso.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baayso.springboot.common.service.AbstractCommonService;
import com.baayso.springboot.demo.domain.DemoUserDO;

/**
 * 测试业务。
 *
 * @author ChenFangjie (2016/4/1 16:27)
 * @since 1.0.0
 */
@Service
public class DemoUserService extends AbstractCommonService<DemoUserDO, Long> {

    @Transactional
    public boolean saveTestUser() {
        DemoUserDO user1 = new DemoUserDO();
        user1.setName("code-1");

        super.save(user1);

        DemoUserDO user2 = new DemoUserDO();
        user2.setName("code-2");

        super.save(user2);

        int min = 0;
        int max = 1;
        int random = (int) (Math.random() * (max - min + 1)) + min;

        int i = 1 / random;

        return true;
    }

    public boolean saveTestUsers() {
        DemoUserDO user3 = new DemoUserDO();
        // user3.setName("code-3");

        DemoUserDO user4 = new DemoUserDO();
        user4.setName("code-4");

        List<DemoUserDO> list = new ArrayList<>();
        list.add(user3);
        list.add(user4);

        super.saves(list);

        return true;
    }

}
