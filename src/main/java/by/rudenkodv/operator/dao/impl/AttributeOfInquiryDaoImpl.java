package by.rudenkodv.operator.dao.impl;

import org.springframework.stereotype.Repository;

import by.rudenkodv.operator.dao.AttributeOfInquiryDao;
import by.rudenkodv.operator.dao.InquiryDao;
import by.rudenkodv.operator.model.AttributeOfInquiry;
import by.rudenkodv.operator.model.Inquiry;

@Repository
public class AttributeOfInquiryDaoImpl extends AbstractDaoImpl<Long, AttributeOfInquiry> implements AttributeOfInquiryDao{

	protected AttributeOfInquiryDaoImpl() {
		super(AttributeOfInquiry.class);
	}

}
