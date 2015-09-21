package by.rudenkodv.operator.services.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.rudenkodv.operator.dao.AttributeOfInquiryDao;
import by.rudenkodv.operator.dao.InquiryDao;
import by.rudenkodv.operator.dao.TopicDao;
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
		try {
			if (inquiry.getId() == null) {
				if (inquiry.getAttributeOfInquiry().getAdress().isEmpty() &&
						inquiry.getAttributeOfInquiry().getCity().isEmpty() &&
						inquiry.getAttributeOfInquiry().getModel().isEmpty())
				{
					inquiry.setAttributeOfInquiry(null);
				} else {
					attrOfInqService.saveOrUpdate(inquiry.getAttributeOfInquiry());
				}

				daoInquiry.insert(inquiry);
			} else {
				if (inquiry.getAttributeOfInquiry().getAdress().isEmpty() &&
						inquiry.getAttributeOfInquiry().getCity().isEmpty() &&
						inquiry.getAttributeOfInquiry().getModel().isEmpty())
				{
					inquiry.setAttributeOfInquiry(null);
				}else {
					attrOfInqService.saveOrUpdate(inquiry.getAttributeOfInquiry());
				}
				daoInquiry.update(inquiry);
			}
		} catch (Exception e) {
			throw new ServiceException("The Update method has throwen an exception fo id:" + inquiry.getId(), e);
		}
	}

	@Override
	public void delete(Inquiry inquiry) {
		try {
			daoInquiry.delete(inquiry.getId());
			if (inquiry.getAttributeOfInquiry() != null){
				attrOfInqService.delete(inquiry.getAttributeOfInquiry());
			}
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
