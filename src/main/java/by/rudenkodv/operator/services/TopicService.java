package by.rudenkodv.operator.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import by.rudenkodv.operator.model.Topic;

public interface TopicService {

	
	
	@Transactional
	void saveOrUpdate(Topic topic);

	@Transactional
	void delete(Topic topic);

	@Transactional
	void deleteAll();
		
	Topic get(Long id);
	
	//Long getCount();
	
	List<Topic> getAllTopic();

}
