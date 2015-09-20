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
		//  од который будет выполнен после создание€ бина
		// this method will be called by Spring after bean instantiation. Can be
		// used for any initialization process.
		LOGGER.info("Instance of Responses is created. Class is: {}",
				getClass().getName());
	}

	@Override
	public Inquiry get(Long id) {
		Inquiry entity = daoInquiry.getById(id);
		return entity;
	}

	@Override
	public void saveOrUpdate(Inquiry inquiry) {
		if (inquiry.getId() == null) {
			LOGGER.debug("Save new: {inquiry}", inquiry);
			//daoTopic.update(inquiry.getTopic());
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
			LOGGER.debug("Update: {inquiry}", inquiry);
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
	}

	@Override
	public void delete(Inquiry inquiry) {
		LOGGER.debug("Remove: {inquiry}", inquiry);
		daoInquiry.delete(inquiry.getId());
		if (inquiry.getAttributeOfInquiry() != null){
			attrOfInqService.delete(inquiry.getAttributeOfInquiry());
		}
	}

	@Override
	public void deleteAll() {
		LOGGER.debug("Remove all inquiry");
		daoInquiry.deleteAll();
	}

	@Override
	public List<Inquiry> getAllInquiry() {
		return daoInquiry.getAll();
	}

	@Override
	public Inquiry singleUserInquiry(String customerName, Long customerId) {
		return daoInquiry.singleUserInquiry(customerName, customerId);
	}

	@Override
	public List<Inquiry> listUserInquiry(String customerName) {
		return daoInquiry.listUserInquiry(customerName);
	}

	@Override
	public List<Inquiry> searchByString(String str) {
		return daoInquiry.searchByString(str);
	}


}
