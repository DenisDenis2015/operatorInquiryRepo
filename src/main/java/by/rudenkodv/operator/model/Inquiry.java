package by.rudenkodv.operator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Inquiry implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@NotNull	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Topic.class)
	private Topic topic;
	
	
	//@NotNull	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = AttributeOfInquiry.class)
	private AttributeOfInquiry attributeOfInquiry;
	
	@Column
	//@NotNull
	private String description;
	
	@Column
	//@NotNull
	private Date createDate;
	
	@Column
	//@NotNull
	private String customerName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public AttributeOfInquiry getAttributeOfInquiry() {
		return attributeOfInquiry;
	}

	public void setAttributeOfInquiry(AttributeOfInquiry attributeOfInquiry) {
		this.attributeOfInquiry = attributeOfInquiry;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	
	
}
