package by.rudenkodv.operator.services;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.transaction.annotation.Transactional;

import by.rudenkodv.operator.model.Inquiry;

/**
 * Ётот интерфейс содержит все элементарные операции св€занные с классом InquiryService
 * —оздание, чтение, добавление, удаление и т. д.
 * 
 * @author Rudenko Denis
 */

public interface InquiryService {

	@Transactional
	void saveOrUpdate(Inquiry inquiry);

	@Transactional
	void delete(Inquiry inquiry);

	@Transactional
	void deleteAll();
		
	Inquiry get(Long id);
		
	List<Inquiry> getAllInquiry();
	
	/**
     * »щет пользовател€ по customerName и customerId
     * @param customerName им€ пользовател€
     * @param customerId Id пользовател€
     * @return Inquiry 
     */
	Inquiry singleUserInquiry(String customerName, Long customerId);
	
	/**
     * »щет всех пользоватей по customerName
     * @param customerName им€ пользовател€
     * @return список запросов Inquiry
     */
	List <Inquiry> listUserInquiry(String customerName);
		
	/**
     * »щет всех пользоватей по заданой строке или символу
     * @param str строка дл€ поиска по имени
     * @return список запросов Inquiry
     */	
	List <Inquiry>  searchByString(String str);
}
