package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yzh.bean.User;
import com.yzh.utils.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext-redis.xml")
public class week12092 {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	public void testweek() {
		List<User> userlist = new ArrayList<>();
		for (int i = 0; i < 50000; i++) {
			User user = new User();
			// id
			user.setId(i + 1);

			// 随即姓名
			String randomChinese = StringUtils.getRandomChinese(3);
			user.setName(randomChinese);

			// 随即性别
			Random sex = new Random();
			String sex1 = sex.nextBoolean() ? "男" : "女";
			user.setSex(sex1);

			// 随即电话以13开头
			String randomNumber = StringUtils.getRandomNumber(9);
			String phone = "13" + randomNumber;
			user.setPhone(phone);

			// 随即邮箱
			int num = (int) (Math.random() * 20);
			int len = num < 3 ? num + 3 : num;
			String randomStr = StringUtils.getRandomStr(len);
			String randomEmailSuffex = StringUtils.getRandomEmailSuffex();
			user.setEmil(randomStr + randomEmailSuffex);

			// 生日
			String randomBirthday = StringUtils.randomBirthday();
			user.setBirthday(randomBirthday);
			userlist.add(user);
		}
		// JDk
		// System.out.println("jdk");
		// long start = System.currentTimeMillis();
		// BoundListOperations<String,Object> boundListOps =
		// redisTemplate.boundListOps("jdk");
		// int num=0;
		// for (User user : userlist) {
		// boundListOps.rightPush(user);
		// num++;
		// }
		// long end = System.currentTimeMillis();
		// System.out.println("jdk消耗"+(end-start)+"毫秒"+"保存数量是"+num);

		// JSon
		// System.out.println("json");
		// long start = System.currentTimeMillis();
		// BoundListOperations<String,Object> boundListOps =
		// redisTemplate.boundListOps("json");
		// int num=0;
		// for (User user : userlist) {
		// boundListOps.rightPush(user);
		// num++;
		// }
		// long end = System.currentTimeMillis();
		// System.out.println("json消耗"+(end-start)+"毫秒"+"保存数量是"+num);

		// hash
		System.out.println("hash");
		long start = System.currentTimeMillis();
		BoundHashOperations<String,Object,Object> boundHashOps = redisTemplate.boundHashOps("hash");
		int num = 0;
		for (User user : userlist) {
			boundHashOps.put("hash", user);
			num++;
		}
		long end = System.currentTimeMillis();
		System.out.println("json消耗" + (end - start) + "毫秒" + "保存数量是" + num);
		
		
	}
}
