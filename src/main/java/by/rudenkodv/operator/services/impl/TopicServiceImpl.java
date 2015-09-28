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
		LOGGER.info("Instance of Responses is created. Class is: {}",
				getClass().getName());
	}

	@Override
	public Topic get(Long id) {
		try {
			Topic entity = dao.getById(id);
			return entity;
		} catch (Exception e) {
			throw new ServiceException("The get method has throwen an exception for id:" + id, e);
		}
	}

	@Override
	public void saveOrUpdate(Topic topic) {
		try {
			if (topic.getId() == null) {
				dao.insert(topic);
			} else {
				dao.update(topic);
			}
		} catch (Exception e) {
			throw new ServiceException("The  saveOrUpdate has throwen an exception for id:" + topic.getId(), e);
		}
	}

	@Override
	public void delete(Topic topic) {
		try {
			dao.delete(topic.getId());
		} catch (Exception e) {
			throw new ServiceException("The delete has throwen an exception for id:" + topic.getId(), e);
		}

	}

	@Override
	public void deleteAll() {
		try {
			dao.deleteAll();
		} catch (Exception e) {
			throw new ServiceException("The delete has throwen an exception for id:", e);
		}
	}

	@Override
	public List<Topic> getAllTopic() {
		try {
			return dao.getAll();
		} catch (Exception e) {
			throw new ServiceException("The getAllTopic() has throwen an exception for id:", e);
		}
	}


}
