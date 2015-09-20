package by.rudenkodv.operator.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import by.rudenkodv.operator.model.AttributeOfInquiry;
import by.rudenkodv.operator.model.Inquiry;

public interface AttributeOfInquiryService {
	
	@Transactional
	void saveOrUpdate(AttributeOfInquiry attributeOfInquiry);

	@Transactional
	void delete(AttributeOfInquiry attributeOfInquiry);

	@Transactional
	void deleteAll();
		
	AttributeOfInquiry get(Long id);
	
	//Long getCount();
	
	List<AttributeOfInquiry> getAllAttributeOfInquiry();


}
