package by.rudenkodv.operator.services.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.rudenkodv.operator.dao.AttributeOfInquiryDao;
import by.rudenkodv.operator.model.AttributeOfInquiry;
import by.rudenkodv.operator.services.AttributeOfInquiryService;

@Service
public class AttributeOfInquiryServiceImpl implements AttributeOfInquiryService{
	private static final Logger LOGGER = LoggerFactory.getLogger(AttributeOfInquiry.class);
	
	@Inject
	private AttributeOfInquiryDao dao;

	@PostConstruct
	private void init() {
		//  од который будет выполнен после создание€ бина
		// this method will be called by Spring after bean instantiation. Can be
		// used for any initialization process.
		LOGGER.info("Instance of Responses is created. Class is: {}",
				getClass().getName());
	}

	@Override
	public AttributeOfInquiry get(Long id) {
		AttributeOfInquiry entity = dao.getById(id);
		return entity;
	}

	@Override
	public void saveOrUpdate(AttributeOfInquiry attributeOfInquiry) {
		if (attributeOfInquiry.getId() == null) {
			LOGGER.debug("Save new: {attributeOfInquiry}", attributeOfInquiry);
			dao.insert(attributeOfInquiry);
		} else {
			LOGGER.debug("Update: {attributeOfInquiry}", attributeOfInquiry);
			dao.update(attributeOfInquiry);
		}
	}

	@Override
	public void delete(AttributeOfInquiry attributeOfInquiry) {
		LOGGER.debug("Remove: {attributeOfInquiry}", attributeOfInquiry);
		dao.delete(attributeOfInquiry.getId());

	}

	@Override
	public void deleteAll() {
		LOGGER.debug("Remove all attributeOfInquiry");
		dao.deleteAll();
	}

	@Override
	public List<AttributeOfInquiry> getAllAttributeOfInquiry() {
		return dao.getAll();
	}


}
