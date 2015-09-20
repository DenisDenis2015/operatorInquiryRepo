package by.rudenkodv.operator.services.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.rudenkodv.operator.dao.TopicDao;
import by.rudenkodv.operator.model.Topic;
import by.rudenkodv.operator.services.TopicService;

@Service
public class TopicServiceImpl implements TopicService{
	private static final Logger LOGGER = LoggerFactory.getLogger(TopicService.class);
	
	@Inject
	private TopicDao dao;

	@PostConstruct
	private void init() {
		//  од который будет выполнен после создание€ бина
		// this method will be called by Spring after bean instantiation. Can be
		// used for any initialization process.
		LOGGER.info("Instance of Responses is created. Class is: {}",
				getClass().getName());
	}

	@Override
	public Topic get(Long id) {
		Topic entity = dao.getById(id);
		return entity;
	}

	@Override
	public void saveOrUpdate(Topic topic) {
		if (topic.getId() == null) {
			LOGGER.debug("Save new: {topic}", topic);
			dao.insert(topic);
		} else {
			LOGGER.debug("Update: {topic}", topic);
			dao.update(topic);
		}
	}

	@Override
	public void delete(Topic topic) {
		LOGGER.debug("Remove: {topic}", topic);
		dao.delete(topic.getId());

	}

	@Override
	public void deleteAll() {
		LOGGER.debug("Remove all topic");
		dao.deleteAll();
	}

	@Override
	public List<Topic> getAllTopic() {
		return dao.getAll();
	}


}
