package by.rudenkodv.operator.services;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.transaction.annotation.Transactional;

import by.rudenkodv.operator.model.Inquiry;

/**
 * ���� ��������� �������� ��� ������������ �������� ��������� � ������� InquiryService
 * ��������, ������, ����������, �������� � �. �.
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
     * ���� ������������ �� customerName � customerId
     * @param customerName ��� ������������
     * @param customerId Id ������������
     * @return Inquiry 
     */
	Inquiry singleUserInquiry(String customerName, Long customerId);
	
	/**
     * ���� ���� ����������� �� customerName
     * @param customerName ��� ������������
     * @return ������ �������� Inquiry
     */
	List <Inquiry> listUserInquiry(String customerName);
		
	/**
     * ���� ���� ����������� �� ������� ������ ��� �������
     * @param str ������ ��� ������ �� �����
     * @return ������ �������� Inquiry
     */	
	List <Inquiry>  searchByString(String str);
}
