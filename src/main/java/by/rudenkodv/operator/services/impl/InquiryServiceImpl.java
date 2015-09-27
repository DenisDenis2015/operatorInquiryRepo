package by.rudenkodv.operator.services.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.rudenkodv.operator.dao.InquiryDao;
import by.rudenkodv.operator.dao.TopicDao;
import by.rudenkodv.operator.model.AttributeOfInquiry;
import by.rudenkodv.operator.model.Inquiry;
import by.rudenkodv.operator.services.AttributeOfInquiryService;
import by.rudenkodv.operator.services.InquiryService;

@Service
public class InquiryServiceImpl implements InquiryService{
	private static final Logger LOGGER = LoggerFactory.getLogger(InquiryService.class);
	
	@Inject
	private InquiryDao daoInquiry;
	
	@Inject
	private AttributeOfInquiryService attrOfInqService;	
	
	@Inject
	private TopicDao daoTopic;	

	@PostConstruct
	private void init() {
		LOGGER.info("Instance of Responses is created. Class is: {}",
				getClass().getName());
	}

	@Override
	public Inquiry get(Long id) {
		try {
			Inquiry entity = daoInquiry.getById(id);
			return entity;
		} catch (Exception e) {
			throw new ServiceException("The get method has throwen an exception fo id:" + id, e);
		}
	}
	
	@Override
	public void saveOrUpdate(Inquiry inquiry) {
		if(inquiry.getId()!=null){
			Inquiry inq = daoInquiry.getById(inquiry.getId());
			deleteAttrOfInq(inq);		
		}
			if (inquiry.getId() == null) {
				daoInquiry.insert(inquiry);				
				if(inquiry.getAttributes()!=null){				
					 saveOrUpdateAttrInquiry(inquiry);					
				}											
			} else {							
				daoInquiry.update(inquiry);				
				if(inquiry.getAttributes()!=null){					
					 saveOrUpdateAttrInquiry(inquiry);					
				}
			}
	}

	private void deleteAttrOfInq(Inquiry inq) {
		for (Iterator<AttributeOfInquiry> it = inq.getAttributes().iterator(); it.hasNext(); ) {
			 AttributeOfInquiry attrOfInq = it.next();
			 attrOfInqService.delete(attrOfInq);
		 }
	}

	private void saveOrUpdateAttrInquiry(Inquiry inquiry) {
		for (Iterator<AttributeOfInquiry> it = inquiry.getAttributes().iterator(); it.hasNext(); ) {
			 AttributeOfInquiry attrOfInq = it.next();
			 attrOfInq.setInquiry(inquiry);
			 attrOfInqService.saveOrUpdate(attrOfInq);
		 }
	}

	@Override
	public void delete(Inquiry inquiry) {
		try {
			if(inquiry.getAttributes()!=null){
				deleteAttrOfInq(inquiry);
			}
			daoInquiry.delete(inquiry.getId());			
		} catch (Exception e) {
			throw new ServiceException("The delete method has throwen an exception fo id:" + inquiry.getId(), e);
		}
	}

	@Override
	public void deleteAll() {
		try {
			daoInquiry.deleteAll();
		} catch (Exception e) {
			throw new ServiceException("The deleteAll method has throwen an exception", e);
		}
	}

	@Override
	public List<Inquiry> getAllInquiry() {
		try {
			return daoInquiry.getAll();
		} catch (Exception e) {
			throw new ServiceException("The getAllInquiry method has throwen an exception", e);
		}
	}

	@Override
	public Inquiry singleUserInquiry(String customerName, Long customerId) {
		try {
			return daoInquiry.singleUserInquiry(customerName, customerId);
		} catch (Exception e) {
			throw new ServiceException("The singleUserInquiry method has throwen an exception for customerId" + customerId, e);
		}
	}

	@Override
	public List<Inquiry> listUserInquiry(String customerName) {
		try {
			return daoInquiry.listUserInquiry(customerName);
		} catch (Exception e) {
			throw new ServiceException("The listUserInquiry method has throwen an exception for customerName:" + customerName, e);
		}
	}

	@Override
	public List<Inquiry> searchByString(String str) {
		try {
			return daoInquiry.searchByString(str);
		} catch (Exception e) {
			throw new ServiceException("The searchByString method has throwen an exception for searchString:" + str, e);
		}
	}
}
