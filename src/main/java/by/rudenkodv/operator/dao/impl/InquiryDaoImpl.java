package by.rudenkodv.operator.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import by.rudenkodv.operator.dao.InquiryDao;
import by.rudenkodv.operator.model.Inquiry;



@Repository
public class InquiryDaoImpl extends AbstractDaoImpl<Long, Inquiry> implements InquiryDao{

	protected InquiryDaoImpl() {
		super(Inquiry.class);
	}

	@Override
	public Inquiry singleUserInquiry(String customerName, Long customerId) {
		CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

		CriteriaQuery<Inquiry> criteria = cBuilder.createQuery(Inquiry.class);
		Root<Inquiry> root = criteria.from(Inquiry.class);

		criteria.select(root).where(cBuilder.and(cBuilder.equal(root.get("customerName"), customerName),
				(cBuilder.equal(root.get("id"), customerId))));

		TypedQuery<Inquiry> query = getEm().createQuery(criteria);

		Inquiry results = query.getSingleResult();

		return results;
	}

	@Override
	public List<Inquiry> listUserInquiry(String customerName) {
		CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

		CriteriaQuery<Inquiry> criteria = cBuilder.createQuery(Inquiry.class);
		Root<Inquiry> root = criteria.from(Inquiry.class);

		criteria.select(root).where(cBuilder.and(cBuilder.equal(root.get("customerName"), customerName)));

		TypedQuery<Inquiry> query = getEm().createQuery(criteria);

		List<Inquiry> results = query.getResultList();

		return results;
	}

	@Override
	public List<Inquiry> searchByString(String str) {
		
		
		CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

		CriteriaQuery<Inquiry> criteria = cBuilder.createQuery(Inquiry.class);
		Root<Inquiry> root = criteria.from(Inquiry.class);
		
		Expression<String> path = root.get("customerName");
		
		Predicate param = cBuilder.like(path, str + "%");
		
		
		criteria.select(root).where(param);
		
		TypedQuery<Inquiry> query = getEm().createQuery(criteria);

		List<Inquiry> results = query.getResultList();
		
		return results;
	}

}
