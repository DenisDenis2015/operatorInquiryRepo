package by.rudenkodv.operator.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import by.rudenkodv.operator.model.Inquiry;
import by.rudenkodv.operator.model.Topic;
import by.rudenkodv.operator.services.InquiryService;
import by.rudenkodv.operator.services.TopicService;

@Controller
public class OperatorController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OperatorController.class);

	@Inject
	private InquiryService inquiryService;

	@Inject
	private TopicService topicService;
	
	@RequestMapping(value = "/topics", method = RequestMethod.GET)
	@Produces({ MediaType.APPLICATION_JSON})
	public @ResponseBody List<Topic> getTopics() {
		return topicService.getAllTopic();
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public List<Topic> createNewInquiryPage() {
		return topicService.getAllTopic();
	}
	
	@Produces({ MediaType.APPLICATION_JSON})
	@Consumes({ MediaType.APPLICATION_JSON})
	@RequestMapping(value = "/customers/{customerName}/inquiries", method = RequestMethod.POST)
	public @ResponseBody void save(@RequestBody Inquiry inquiry) {
		 LOGGER.debug("Save inquiry by request POST : /customers/" +inquiry.getCustomerName() + "/inquiries");
		 inquiryService.saveOrUpdate(inquiry);  	 
	}
		
	@RequestMapping(value = "/inquiry/list", method = RequestMethod.GET)
	@Produces({ MediaType.APPLICATION_JSON})
	public @ResponseBody List<Inquiry> getInquiry() {
		return inquiryService.getAllInquiry();
	}
	
	@Produces({ MediaType.APPLICATION_JSON})
	@Consumes({ MediaType.APPLICATION_JSON})
	@RequestMapping(value = "/customers/{customerName}/inquiries/{id}", method = RequestMethod.GET)
	public @ResponseBody Inquiry editInquiriePage(@PathVariable("customerName") String customerName,
			@PathVariable("id") long id) {
		Inquiry inquiry =  inquiryService.singleUserInquiry(customerName, id);
		return inquiry;
	}
		
	@Produces({ MediaType.APPLICATION_JSON})
	@Consumes({ MediaType.APPLICATION_JSON})
	@RequestMapping(value = "/customers/{customerName}/inquiries/{id}", method = RequestMethod.PUT)
	public @ResponseBody void updateUserInquiry(@RequestBody Inquiry inquiry, @PathVariable("customerName") String customerName,
			@PathVariable("id") long id){	
		inquiryService.saveOrUpdate(inquiry);
		 LOGGER.debug("Update  inquiry by request PUT : /customers/"+inquiry.getCustomerName()+"/inquiries/"+inquiry.getId());
	}	
	
	@Consumes({ MediaType.APPLICATION_JSON})
	@RequestMapping(value = "/customers/{customerName}/inquiries/{id}", method = RequestMethod.DELETE)
	public @ResponseBody  void deleteSingleTopics(@PathVariable("customerName") String customerName,
			@PathVariable("id") long inquiryId){	
		Inquiry inquiry =  inquiryService.singleUserInquiry(customerName, inquiryId);
		LOGGER.debug("DELETE  inquiry by request DELETE forID : {id}" + inquiry.getId());
		inquiryService.delete(inquiry);
	}		
	
	@Produces({ MediaType.APPLICATION_JSON})
	@RequestMapping(value = "/search/{query}", method = RequestMethod.GET)
	public @ResponseBody List<Inquiry> findByName (@PathVariable("query") String query) {
		LOGGER.debug("GET inquiry by request string - " + query);
		return inquiryService.searchByString(query);
	}
}
