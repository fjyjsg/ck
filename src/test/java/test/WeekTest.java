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
@ContextConfiguration(value="classpath:applicationContext-redis.xml")
public class WeekTest {
	@Resource
	private RedisTemplate<String, Object> redisTemplate ;
	
	@Test
	public void weektwo(){
		List<User> userlist=new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			User user=new User();
			user.setId(i+1);
			//随即名字
			String randomChinese = StringUtils.getRandomChinese(3);
			user.setName(randomChinese);
			//随即性别
			Random random=new Random();
			String sex = random.nextBoolean()?"男":"女";
			user.setSex(sex);
			//随即手机号
			String randomStrAndNumber = "13"+StringUtils.getRandomStrAndNumber(9);
			user.setPhone(randomStrAndNumber);
			
			//随即的邮箱
			int random2=(int)(Math.random()*20);
			int len=random2<3?random2+3:random2;
			String randomStr = StringUtils.getRandomStr(len);
			String randomEmailSuffex = StringUtils.getRandomEmailSuffex();
			user.setEmail(randomStr+randomEmailSuffex);
			
			//随即的生日
			String randomBirthday = StringUtils.randomBirthday();
			user.setBirthday(randomBirthday);
			userlist.add(user);
		}
		//JDK的序列化
		//System.out.println("JDK的序列化方式");
		//系统开始时间
		/*long start = System.currentTimeMillis();
		BoundListOperations<String,Object> boundListOps = redisTemplate.boundListOps("jdk");
		boundListOps.rightPush(userlist);
		long end = System.currentTimeMillis();
		System.out.println("JDK耗时"+(end-start)+"毫秒");*/
		
		//JSON的序列化方式
		System.out.println("JSON的序列化方式");
		//系统开始时间
		/*long start1 = System.currentTimeMillis();
		BoundListOperations<String,Object> boundListOps1 = redisTemplate.boundListOps("json");
		boundListOps1.rightPush(userlist);
		long end1 = System.currentTimeMillis();
		System.out.println("JSON耗时"+(end1-start1)+"毫秒");*/
		
		//hash
		System.out.println("HASH的序列化方式");
		//系统开始时间
		long start2 = System.currentTimeMillis();
		BoundHashOperations<String,Object,Object> boundHashOps = redisTemplate.boundHashOps("hash");
		boundHashOps.put("hash", userlist);
		long end2 = System.currentTimeMillis();
		System.out.println("HASH耗时"+(end2-start2)+"毫秒");
	}
}
