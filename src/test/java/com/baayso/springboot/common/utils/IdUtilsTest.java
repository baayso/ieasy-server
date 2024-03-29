package com.baayso.springboot.common.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link IdUtils}.
 *
 * @author ChenFangjie (2020/1/7 11:38)
 * @since 3.1.0
 */
public class IdUtilsTest {

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test method for {@link IdUtils#uuid()}.
     *
     * @since 3.1.0
     */
    @Test
    public void testUuid() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            System.out.println(IdUtils.uuid());
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    /**
     * Test method for {@link IdUtils#objectId()}.
     *
     * @since 3.1.0
     */
    @Test
    public void testObjectId() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            System.out.println(IdUtils.objectId());
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

}
