package by.rudenkodv.operator.dao;

import java.util.List;

import by.rudenkodv.operator.model.Inquiry;

public interface InquiryDao extends AbstractDao<Long, Inquiry> {

	Inquiry singleUserInquiry(String customerName, Long customerId);

	List <Inquiry> listUserInquiry(String customerName);
	
	List<Inquiry> searchByString(String str);
}
