package by.rudenkodv.operator.dao.impl;

import org.springframework.stereotype.Repository;

import by.rudenkodv.operator.dao.TopicDao;
import by.rudenkodv.operator.model.Topic;

@Repository
public class TopicDaoImpl extends AbstractDaoImpl<Long, Topic> implements TopicDao{

	protected TopicDaoImpl() {
		super(Topic.class);
	}

}
