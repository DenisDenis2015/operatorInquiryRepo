package by.rudenkodv.operator.services;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.rudenkodv.operator.model.Inquiry;
import by.rudenkodv.operator.model.Topic;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-test.xml"})
public class TopicServiceTest extends AbstractServiceTest {

	@Test
	public void testEqualsTrue1(){
		Inquiry inq1 = new Inquiry(0l, new Topic(0l,"Topic"),"descr",new Timestamp(2323424),"CustomerName");
		Inquiry inq2 = new Inquiry(0l, new Topic(0l,"Topic"),"descr",new Timestamp(2323424),"CustomerName");
		Assert.assertEquals("Inquiry" +inq1 + "!=" + inq2, inq1,inq2);	
	}
	
	@Test
	public void testEqualsTrue2(){
		Inquiry inq1 = new Inquiry(randomLong(), new Topic(randomLong(), randomString()),randomString(),randomTimestamp(),randomString("userName:"));
		Assert.assertEquals("Inquiry" +inq1 + "!=" + inq1, inq1,inq1);	
	}
	
	@Test
	public void testEqualsFalse1(){
		Inquiry inq1 = new Inquiry(randomLong(), new Topic(randomLong(), randomString()),randomString(),randomTimestamp(),randomString("userName:"));
		Inquiry inq2 = new Inquiry(randomLong(), new Topic(randomLong(), randomString()),randomString(),randomTimestamp(),randomString("Name:"));
		Assert.assertNotEquals("Inquiry" +inq1 + "==" + inq2, inq1,inq2);	
	}	
	
	@Test
	public void testEqualsFalse2(){
		Inquiry inq1 = new Inquiry(randomLong(), new Topic(randomLong(), randomString()),randomString(),randomTimestamp(),randomString("userName:"));
		Assert.assertNotEquals("Inquiry" +inq1 + "==" + null, inq1,null);	
	}	
	
	@Test
	public void testHashCode(){
		int inq1HashCode = new Inquiry(0l, new Topic(0l,"Topic"),"descr",new Timestamp(2323424),"CustomerName").hashCode();
		int inq2HashCode = new Inquiry(0l, new Topic(0l,"Topic"),"descr",new Timestamp(2323424),"CustomerName").hashCode();
		Assert.assertEquals("HashCode" +inq1HashCode + "!=" + inq2HashCode, inq1HashCode, inq2HashCode);	
	}
}
