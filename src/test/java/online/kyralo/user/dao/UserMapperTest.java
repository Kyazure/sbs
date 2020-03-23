package online.kyralo.user.dao;

import online.kyralo.user.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void queryByMail() {

        User user = mapper.queryByMail("15270640536@163.com");
        System.out.println(user);
        Assert.assertNotNull(user);
    }

    @Test
    void queryById() {
        User user = mapper.queryById("accd999b39dc453d89a5078e4f2041b9");
        Assert.assertNotNull(user);
    }

    @Test
    void insert() {
        User user = new User();
        user.setId(UUID.randomUUID().toString().replace("-",""));
        user.setName("wangchen");
        user.setPassword("123456");
        user.setMail("2337736075@qq.com");
        user.setSign("莫问,就是干!");

        int i = mapper.insert(user);
        Assert.assertEquals(1, i);

    }

    @Test
    void update() {
        User user = new User();
        user.setId("accd999b39dc453d89a5078e4f2041b9");
        user.setPassword("666666");

        int i = mapper.update(user);

        Assert.assertEquals(1, i);
    }

}