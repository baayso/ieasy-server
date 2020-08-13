package com.baayso.springboot.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baayso.commons.tool.BasicResponseStatus;
import com.baayso.springboot.common.exception.ApiServiceException;
import com.baayso.springboot.common.service.AbstractBaseService;
import com.baayso.springboot.demo.dao.DemoUserDAO;
import com.baayso.springboot.demo.domain.DemoUserDO;
import com.baayso.springboot.demo.domain.enums.OrderStatus;


/**
 * 测试业务。
 *
 * @author ChenFangjie (2016/4/1 16:27)
 * @since 1.0.0
 */
@Service
public class DemoUserService extends AbstractBaseService<DemoUserDAO, DemoUserDO> {

    @Transactional
    public boolean save() {
        DemoUserDO user1 = new DemoUserDO();
        user1.setName("code-1-" + this.nowTimeStr());
        user1.setIntro("");
        user1.setAge(18);
        // user1.initBeforeAdd();

        super.save(user1);

        DemoUserDO user2 = new DemoUserDO();
        user2.setName("code-2-" + this.nowTimeStr());
        user2.setIntro(null);
        user2.setAge(18);
        user2.setStatus(OrderStatus.SUCCESS);
        // user2.initBeforeAdd();

        super.save(user2);

        int min = 0;
        int max = 9;
        int random = (int) (Math.random() * (max - min + 1)) + min;

        if (random <= 2) {
            throw new ApiServiceException(BasicResponseStatus.SERVICE_UNAVAILABLE);
        }

        // 模拟异常以测试事务是否回滚
        int i = 1 / (random > 5 ? 1 : 0);

        return true;
    }

    @Transactional
    public boolean saves(DemoUserDO user) {

        super.save(user);

        DemoUserDO user2 = new DemoUserDO();
        user2.setName("he-he-" + this.nowTimeStr());
        user2.setIntro(null);
        user2.setAge(18);
        user2.setStatus(OrderStatus.SUCCESS);
        // user2.initBeforeAdd();

        super.save(user2);

        int min = 0;
        int max = 9;
        int random = (int) (Math.random() * (max - min + 1)) + min;

        // 模拟异常以测试事务是否回滚
        int i = 1 / (random >= 5 ? 1 : 0);

        return true;
    }

    public boolean saves() {
        DemoUserDO user3 = new DemoUserDO();
        user3.setName("code-555-" + this.nowTimeStr());
        user3.setIntro("");
        user3.setAge(19);
        // user3.initBeforeAdd();

        DemoUserDO user4 = new DemoUserDO();
        user4.setName("code-666-" + this.nowTimeStr());
        user4.setIntro(null);
        user4.setAge(19);
        user4.setStatus(OrderStatus.CLOSE);
        // user4.initBeforeAdd();

        List<DemoUserDO> list = new ArrayList<>();
        list.add(user3);
        list.add(user4);

        super.saveBatch(list);

        return true;
    }

    @Async
    public Future<Boolean> asyncSaves() throws InterruptedException {

        TimeUnit.SECONDS.sleep(3L);

        return AsyncResult.forValue(this.save());
    }

    public boolean deletes(Long id) {
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        ids.add(8L);

        return super.removeByIds(ids);
    }

    public boolean update(Long id) {
        DemoUserDO user5 = DemoUserDO.builder() //
                .id(id) //
                .version(2) //
                .intro("测试乐观锁") //
                .status(OrderStatus.RETURN_SUCCESS) //
                // .updateBy("测试乐观锁") //
                // .updateTime(LocalDateTime.now()) //
                .build();

        return super.updateById(user5);
    }

    @Transactional
    public List<DemoUserDO> test() {
        int inserts = super.baseMapper.inserts();
        int insertIntoSelect = super.baseMapper.insertIntoSelect();

        List<DemoUserDO> listInnerJoin = super.baseMapper.listInnerJoin();
        List<DemoUserDO> listLeftJoin = super.baseMapper.listLeftJoin();
        List<DemoUserDO> listRightJoin = super.baseMapper.listRightJoin();

        // TODO 请注意：租户SQL解析器(schema 级)未支持在WHERE条件中使用子查询，此处查询会报错
        List<DemoUserDO> listSubQuery = super.baseMapper.listSubQuery();

        return super.baseMapper.listUnion();
    }

    private String nowTimeStr() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

}
