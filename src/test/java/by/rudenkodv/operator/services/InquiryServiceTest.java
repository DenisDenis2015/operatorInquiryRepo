package by.rudenkodv.operator.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.rudenkodv.operator.model.AttributeOfInquiry;
import by.rudenkodv.operator.model.Inquiry;
import by.rudenkodv.operator.model.Topic;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-test.xml"})
public class InquiryServiceTest extends AbstractServiceTest {
	
	
	@Inject
	private InquiryService inquiryService;

	@Inject
	private TopicService topicService;
	
	@Inject
	private AttributeOfInquiryService attributeOfInquiryService;
	
	@Before
	public void cleanUpData() {
		clearData();
	}

    @After
	public void cleanUpDataAfterTest() {
		clearData();
	}

	@Test
	public void basicTestInjection() {
		Assert.assertNotNull(inquiryService);
		Assert.assertNotNull(topicService);
		Assert.assertNotNull(attributeOfInquiryService);
	}
	
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

	
	@Test
	public void basicCRUDTest() {
		Topic topic = prepareTopic();				
		AttributeOfInquiry attributeOfInquiry = prepareAttributeOfInquiry();
		Inquiry inquiry = prepareInquiryEntity(attributeOfInquiry, topic);
		
		Inquiry inquiryFromDB = inquiryService.get(inquiry.getId());
		Assert.assertNotNull(inquiryFromDB);
		
		saveTest(inquiry, inquiryFromDB);
		
		updateTest(inquiry, inquiryFromDB);
				
		Topic topicFromDB = topicService.get(topic.getId());
		saveUpdateTopicTest(topic, topicFromDB);
	}
	
	private void saveUpdateTopicTest(Topic topic, Topic topicFromDB) {
		Assert.assertNotNull(topicFromDB);
		Assert.assertEquals(topic.getName(), topicFromDB.getName());
		
		// update test AttributeOfInquiry
		topic.setName("UserUseravich");
		topicService.saveOrUpdate(topic);
		Topic topicFromDBUpdate = topicService.get(topic.getId());
		Assert.assertNotEquals(topicFromDB.getName(), topicFromDBUpdate.getName());
	}

	private void updateTest(Inquiry inquiry, Inquiry inquiryFromDB) {
		inquiry.setCustomerName("newCustomerName");
		inquiryService.saveOrUpdate(inquiry);
		Inquiry inquiryFromDBUpdate = inquiryService.get(inquiry.getId());
		Assert.assertNotEquals(inquiryFromDB.getCustomerName(), inquiryFromDBUpdate.getCustomerName());
		Assert.assertEquals(inquiryFromDB.getDescription(), inquiryFromDBUpdate.getDescription());
	}

	private void saveTest(Inquiry inquiry, Inquiry inquiryFromDB) {
		Assert.assertEquals(inquiry.getCustomerName(), inquiryFromDB.getCustomerName());
		Assert.assertEquals(inquiry.getDescription(), inquiryFromDB.getDescription());				
		Assert.assertEquals(inquiry.getTopic().getId(), inquiryFromDB.getTopic().getId());
	}
	
	@Test
	public void singleUserInquiry(){
		Topic topic = prepareTopic();				
		AttributeOfInquiry attributeOfInquiry = prepareAttributeOfInquiry();
		Inquiry inquiry = prepareInquiryEntity(attributeOfInquiry, topic);
		
		Inquiry singleInquiry = inquiryService.singleUserInquiry(inquiry.getCustomerName(), inquiry.getId());
		
		Assert.assertEquals(singleInquiry.getCustomerName(), inquiry.getCustomerName());
		
	}
	
	@Test
	public void listUserInquiry(){
		Topic topic = prepareTopic();				
		AttributeOfInquiry attributeOfInquiry = prepareAttributeOfInquiry();
		Inquiry inquiry = prepareInquiryEntity(attributeOfInquiry, topic);
		Inquiry inquiryNew = prepareInquiryEntity(attributeOfInquiry, topic);
		inquiryNew.setCustomerName(inquiry.getCustomerName());
		inquiryService.saveOrUpdate(inquiryNew);	
		List <Inquiry> singleInquiry = inquiryService.listUserInquiry(inquiry.getCustomerName());	
		Assert.assertEquals(2, singleInquiry.size());
		
	}
	
	@Test
	public void searchByString(){
		cleanUpData();
		Topic topic = prepareTopic();	
		AttributeOfInquiry attributeOfInquiry = prepareAttributeOfInquiry();
		Inquiry inquiry = prepareInquiryEntity(attributeOfInquiry, topic);
		String str = randomString();
		inquiry.setCustomerName(str);
		inquiryService.saveOrUpdate(inquiry);		
		List<Inquiry> listInquiry = inquiryService.searchByString(str);
		Assert.assertEquals(listInquiry.get(0).getCustomerName(), inquiry.getCustomerName());
		Assert.assertTrue(listInquiry.size()==1);		
	}

	private Topic prepareTopic() {
		Topic topic = new Topic();
		topic.setName(randomString());
		return topic;
	}

	private AttributeOfInquiry prepareAttributeOfInquiry() {
		AttributeOfInquiry attributeOfInquiry = new AttributeOfInquiry();
		attributeOfInquiry.setName(randomString());
		attributeOfInquiry.setValue(randomString());
		return attributeOfInquiry;
	}

	private Inquiry prepareInquiryEntity(AttributeOfInquiry attributeOfInquiry, Topic topic) {
		Inquiry inquiry = new Inquiry();
		inquiry.setTopic(topic);
		inquiry.setCreateDate(randomTimestamp());
		inquiry.setCustomerName(randomString());
		inquiry.setDescription(randomString());
		attributeOfInquiry.setInquiry(inquiry);
		saveEntityInDB(attributeOfInquiry, topic, inquiry);			
		return inquiry;		
	}

	private void saveEntityInDB(AttributeOfInquiry attributeOfInquiry,
		Topic topic, Inquiry inquiry) {
		topicService.saveOrUpdate(topic);
		inquiryService.saveOrUpdate(inquiry);
		attributeOfInquiryService.saveOrUpdate(attributeOfInquiry);
		
	}
	
	private void clearData() {
		attributeOfInquiryService.deleteAll();
		inquiryService.deleteAll();
		topicService.deleteAll();
	}
}
