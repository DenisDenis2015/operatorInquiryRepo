package by.rudenkodv.operator.services;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.rudenkodv.operator.model.AttributeOfInquiry;
import by.rudenkodv.operator.model.Inquiry;
import by.rudenkodv.operator.model.Topic;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-test.xml"})
public class InquiryServiceTest extends AbstractServiceTest {
	
	@Test
	public void testEqualsTrue1(){
		Topic topic1 = new Topic(0l,"Topic");
		Topic topic2 = new Topic(0l,"Topic");
		Assert.assertEquals("Inquiry" +topic1 + "!=" + topic2, topic1,topic2);	
	}
	
	@Test
	public void testEqualsTrue2(){
		Topic topic1 = new Topic(randomLong(), randomString());
		Assert.assertEquals("Topic" +topic1 + "!=" + topic1, topic1,topic1);	
	}
	
	@Test
	public void testEqualsFalse1(){
		Topic topic1 = new Topic(randomLong(), randomString("topic-"));
		Topic topic2 = new Topic(randomLong(), randomString());
		Assert.assertNotEquals("Topic" +topic1 + "==" + topic2, topic1,topic2);	
	}	
	
	@Test
	public void testEqualsFalse2(){
		Topic topic1 = new Topic(randomLong(), randomString("topic-"));
		Assert.assertNotEquals("Topic " +topic1 + "==" + null, topic1,null);	
	}	
	
	@Test
	public void testHashCode(){
		int topic1HashCode = new Topic(0l,"Topic").hashCode();
		int topic2HashCode = new Topic(0l,"Topic").hashCode();
		Assert.assertEquals("HashCode" +topic1HashCode + "!=" + topic2HashCode, topic1HashCode, topic2HashCode);	
	}
}
