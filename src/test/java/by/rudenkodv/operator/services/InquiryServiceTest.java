package by.rudenkodv.operator.services;

import java.util.List;

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
	private static final Logger LOGGER = LoggerFactory
			.getLogger(InquiryServiceTest.class);

	@Inject
	private InquiryService inquiryService;

	@Inject
	private TopicService topicService;
	
	@Inject
	private AttributeOfInquiryService attributeOfInquiryService;

//	@Before
	public void cleanUpData() {
		clearData();
	}

//	@After
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
	public void basicTest() {
		Topic topic = prepareTopic();				
		AttributeOfInquiry attributeOfInquiry = prepareAttributeOfInquiry();
		Inquiry inquiry = prepareInquiryEntity(attributeOfInquiry, topic);
	//	List<Inquiry> list = inquiryService.getAllInquiry();

	}
	
	@Ignore
	@Test
	public void basicCRUDTest() {
		Topic topic = prepareTopic();				
		AttributeOfInquiry attributeOfInquiry = prepareAttributeOfInquiry();
		Inquiry inquiry = prepareInquiryEntity(attributeOfInquiry, topic);
		
		Inquiry inquiryFromDB = inquiryService.get(inquiry.getId());
		Assert.assertNotNull(inquiryFromDB);
		
		saveTest(inquiry, inquiryFromDB);
		
		updateTest(inquiry, inquiryFromDB);
		
		checkUpdateTest(attributeOfInquiry);
		
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

	private void checkUpdateTest(AttributeOfInquiry attributeOfInquiry) {
		AttributeOfInquiry attributeOfInquiryFromDB = attributeOfInquiryService.get(attributeOfInquiry.getId());
		Assert.assertNotNull(attributeOfInquiryFromDB);
		Assert.assertEquals(attributeOfInquiry.getAdress(), attributeOfInquiryFromDB.getAdress());
		Assert.assertEquals(attributeOfInquiry.getCity(), attributeOfInquiryFromDB.getCity());
		Assert.assertEquals(attributeOfInquiry.getModel(), attributeOfInquiryFromDB.getModel());
		
		// update test AttributeOfInquiry
		attributeOfInquiry.setAdress("Grodno street - 123");
		attributeOfInquiryService.saveOrUpdate(attributeOfInquiry);
		AttributeOfInquiry attributeOfInquiryFromDBUpdate = attributeOfInquiryService.get(attributeOfInquiry.getId());
		Assert.assertNotEquals(attributeOfInquiryFromDBUpdate.getAdress(), attributeOfInquiryFromDB.getAdress());
		Assert.assertEquals(attributeOfInquiryFromDBUpdate.getModel(), attributeOfInquiryFromDB.getModel());
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
		Assert.assertEquals(inquiry.getAttributeOfInquiry().getId(), inquiryFromDB.getAttributeOfInquiry().getId());
		Assert.assertEquals(inquiry.getTopic().getId(), inquiryFromDB.getTopic().getId());
	}
	
	@Ignore
	@Test
	public void singleUserInquiry(){
		Topic topic = prepareTopic();				
		AttributeOfInquiry attributeOfInquiry = prepareAttributeOfInquiry();
		Inquiry inquiry = prepareInquiryEntity(attributeOfInquiry, topic);
		
		Inquiry singleInquiry = inquiryService.singleUserInquiry(inquiry.getCustomerName(), inquiry.getId());
		
		Assert.assertEquals(singleInquiry.getCustomerName(), inquiry.getCustomerName());
		
	}
	
	@Ignore	
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
	
	@Ignore
	@Test
	public void searchByString(){
		cleanUpData();
		Topic topic = prepareTopic();	
		AttributeOfInquiry attributeOfInquiry = prepareAttributeOfInquiry();
		Inquiry inquiry = prepareInquiryEntity(attributeOfInquiry, topic);
		String str = randomString();
		inquiry.setCustomerName(randomString(str));
		inquiryService.saveOrUpdate(inquiry);		
		List<Inquiry> listInquiry = inquiryService.searchByString(str.substring(0, randInt(1, 5)));
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
		inquiry.setCreateDate(randomDate());
		inquiry.setCustomerName(randomString());
		inquiry.setDescription(randomString());
	//	inquiry.setAttributeOfInquiry(attributeOfInquiry);		
		saveEntityInDB(attributeOfInquiry, topic, inquiry);			
		return inquiry;		
	}

	private void saveEntityInDB(AttributeOfInquiry attributeOfInquiry,
		Topic topic, Inquiry inquiry) {
		topicService.saveOrUpdate(topic);
		attributeOfInquiryService.saveOrUpdate(attributeOfInquiry);
		inquiryService.saveOrUpdate(inquiry);
	}

	private void clearData() {
		inquiryService.deleteAll();
		topicService.deleteAll();
		attributeOfInquiryService.deleteAll();

	}

}
