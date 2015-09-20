package by.rudenkodv.operator.services;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import by.rudenkodv.operator.model.AttributeOfInquiry;
import by.rudenkodv.operator.model.Inquiry;
import by.rudenkodv.operator.model.Topic;

/*@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath:test-context.xml" })*/
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDB extends AbstractServiceTest{
	
	
	@Inject
	private InquiryService inquiryService;

	@Inject
	private TopicService topicService;
	
	@Inject
	private AttributeOfInquiryService attributeOfInquiryService;


	@Test
	public void basicTestInjection() {
		Assert.assertNotNull(inquiryService);
		Assert.assertNotNull(topicService);
		Assert.assertNotNull(attributeOfInquiryService);
	}

	@Test
	public void basicCRUDTest() {
		Topic topic = prepareTopic();				
		AttributeOfInquiry attributeOfInquiry = prepareAttributeOfInquiry();
		Inquiry inquiry = prepareInquiryEntity(attributeOfInquiry, topic);
		
		Inquiry inquiryFromDB = inquiryService.get(inquiry.getId());
		Assert.assertNotNull(inquiryFromDB);
		
		// save value test
		Assert.assertEquals(inquiry.getCustomerName(), inquiryFromDB.getCustomerName());
		Assert.assertEquals(inquiry.getDescription(), inquiryFromDB.getDescription());		
		//Assert.assertEquals(inquiry.getCreateDate(), inquiryFromDB.getCreateDate());		
		Assert.assertEquals(inquiry.getAttributeOfInquiry().getId(), inquiryFromDB.getAttributeOfInquiry().getId());
		Assert.assertEquals(inquiry.getTopic().getId(), inquiryFromDB.getTopic().getId());
		
		// update test Inquiry
		inquiry.setCustomerName("newCustomerName");
		inquiryService.saveOrUpdate(inquiry);
		Inquiry inquiryFromDBUpdate = inquiryService.get(inquiry.getId());
		Assert.assertNotEquals(inquiryFromDB.getCustomerName(), inquiryFromDBUpdate.getCustomerName());
		Assert.assertEquals(inquiryFromDB.getDescription(), inquiryFromDBUpdate.getDescription());
		
		// save value test
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
		
		// save value test
		Topic topicFromDB = topicService.get(topic.getId());
		Assert.assertNotNull(topicFromDB);
		Assert.assertEquals(topic.getName(), topicFromDB.getName());
		
		// update test AttributeOfInquiry
		topic.setName("UserUseravich");
		topicService.saveOrUpdate(topic);
		Topic topicFromDBUpdate = topicService.get(topic.getId());
		Assert.assertNotEquals(topicFromDB.getName(), topicFromDBUpdate.getName());
		
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
	
	

	private Topic prepareTopic() {
		Topic topic = new Topic();
		topic.setName(randomString());
		return topic;
	}

	private AttributeOfInquiry prepareAttributeOfInquiry() {
		AttributeOfInquiry attributeOfInquiry = new AttributeOfInquiry();
		attributeOfInquiry.setAdress(randomString());
		attributeOfInquiry.setCity(randomString());
		attributeOfInquiry.setModel(randomString());
		return attributeOfInquiry;
	}

	private Inquiry prepareInquiryEntity(AttributeOfInquiry attributeOfInquiry, Topic topic) {
		Inquiry inquiry = new Inquiry();
		
		inquiry.setTopic(topic);
		inquiry.setCreateDate(randomDate());
		inquiry.setCustomerName(randomString());
		inquiry.setDescription(randomString());
		inquiry.setAttributeOfInquiry(attributeOfInquiry);
		
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
	


