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
	private AttributeOfInquiryDao attributeOfInquiryDao;

	@PostConstruct
	private void init() {
		LOGGER.info("Instance of Responses is created. Class is: {}",
				getClass().getName());
	}

	@Override
	public AttributeOfInquiry get(Long id) {
		try {
			return attributeOfInquiryDao.getById(id);
		} catch (Exception e) {
			throw new ServiceException("The get method has throwen an exception for id:" + id, e);
		}
	}

	@Override
	public void saveOrUpdate(AttributeOfInquiry attributeOfInquiry) {
		try {
			if (attributeOfInquiry.getId() == null) {
				attributeOfInquiryDao.insert(attributeOfInquiry);
			} else {
				attributeOfInquiryDao.update(attributeOfInquiry);
			}
		} catch (Exception e) {
			throw new ServiceException("The saveOrUpdate method has throwen an exception for id:" + attributeOfInquiry.getId(), e);
		}
	}

	@Override
	public void delete(AttributeOfInquiry attributeOfInquiry) {
		try {
			attributeOfInquiryDao.delete(attributeOfInquiry.getId());
		} catch (Exception e) {
			throw new ServiceException("The delete method has throwen an exception for id:" + attributeOfInquiry.getId(), e);
		}
	}

	@Override
	public void deleteAll() {
		try {
			attributeOfInquiryDao.deleteAll();
		} catch (Exception e) {
			throw new ServiceException("The deleteAll method has throwen an exception", e);
		}
	}

	@Override
	public List<AttributeOfInquiry> getAllAttributeOfInquiry() {
		try {
			return attributeOfInquiryDao.getAll();
		} catch (Exception e) {
			throw new ServiceException("The getAllAttributeOfInquiry method has throwen an exception", e);
		}
	}


}
