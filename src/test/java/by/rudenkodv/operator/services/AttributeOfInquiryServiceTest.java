package by.rudenkodv.operator.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.rudenkodv.operator.model.AttributeOfInquiry;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-test.xml"})
public class AttributeOfInquiryServiceTest extends AbstractServiceTest {

	@Test
	public void testEqualsTrue1(){
		AttributeOfInquiry attrOfInq1 = new AttributeOfInquiry(0l, null , "name-param", "value - param");
		AttributeOfInquiry attrOfInq2 = new AttributeOfInquiry(0l, null , "name-param", "value - param");
		Assert.assertEquals("AttributeOfInquiry" +attrOfInq1 + "!=" + attrOfInq2, attrOfInq1,attrOfInq2);	
	}
	
	@Test
	public void testEqualsTrue2(){
		AttributeOfInquiry attrOfInq1 = new AttributeOfInquiry(randomLong(), null, randomString(), randomString());
		Assert.assertEquals("AttributeOfInquiry" +attrOfInq1 + "!=" + attrOfInq1, attrOfInq1,attrOfInq1);	
	}
	
	@Test
	public void testEqualsFalse1(){
		AttributeOfInquiry attrOfInq1 = new AttributeOfInquiry(randomLong(), null, randomString(), randomString());
		AttributeOfInquiry attrOfInq2 = new AttributeOfInquiry(randomLong(), null, randomString(), randomString());
		Assert.assertNotEquals("AttributeOfInquiry" +attrOfInq1 + "==" + attrOfInq2, attrOfInq1,attrOfInq2);	
	}	
	
	@Test
	public void testEqualsFalse2(){
		AttributeOfInquiry attrOfInq1 = new AttributeOfInquiry(randomLong(), null, randomString(), randomString());
		Assert.assertNotEquals("AttributeOfInquiry" +attrOfInq1 + "==" + null, attrOfInq1,null);	
	}	
	
	@Test
	public void testHashCode(){
		int attrOfInq1 = new AttributeOfInquiry(0l, null , "name-param", "value - param").hashCode();
		int attrOfInq2 = new AttributeOfInquiry(0l, null , "name-param", "value - param").hashCode();
		Assert.assertEquals("HashCode" +attrOfInq1 + "!=" + attrOfInq2, attrOfInq1, attrOfInq2);	
	}
}
