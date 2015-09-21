package by.rudenkodv.operator.dao.impl;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.commons.lang3.Validate;

import by.rudenkodv.operator.dao.AbstractDao;

public abstract class AbstractDaoImpl<ID, Entity> implements AbstractDao<ID, Entity> {

	protected final Random random = new Random();

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEm(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private final Class<Entity> entityClass;

	protected AbstractDaoImpl(final Class<Entity> entityClass) {
		Validate.notNull(entityClass, "entityClass could not be a null");
		this.entityClass = entityClass;
	}

	@Override
	public Entity getById(ID id) {
		try {
			return entityManager.find(getEntityClass(), id);
		} catch (Exception e) {
			throw new DaoException("method getById entityManager throw exception" + entityClass.getClass(), e);
		}
	}

	@Override
	public Entity insert(final Entity entity) {
		try {
			entityManager.persist(entity);
		} catch (Exception e) {
			throw new DaoException("method insert entityManager throw exception" + entityClass.getClass(), e);
		}
		return entity;
	}

	@Override
	public Entity update(Entity entity) {
		try {
			entity = entityManager.merge(entity);
			entityManager.flush();
			return entity;
		} catch (Exception e) {
			throw new DaoException("method update entityManager throw exception" + entityClass.getClass(), e);
		}
	}

	@Override
	public void delete(final ID key) {
		try {
			entityManager.remove(entityManager.find(getEntityClass(), key));
		} catch (Exception e) {
			throw new DaoException("method delete entityManager throw exception" + entityClass.getClass(), e);
		}
	}

	@Override
	public void delete(List<ID> ids) {
		try {
			entityManager
					.createQuery(String.format("delete from %s e where e.id in (:ids)", entityClass.getSimpleName()))
					.setParameter("ids", ids).executeUpdate();
		} catch (Exception e) {
			throw new DaoException("method delete by List<ID> entityManager throw exception" + entityClass.getClass(),
					e);
		}
	}

	@Override
	public void deleteAll() {
		try {
			final Query q1 = entityManager.createQuery("delete from " + getEntityClass().getSimpleName());
			q1.executeUpdate();
			entityManager.flush();
		} catch (Exception e) {
			throw new DaoException("method deleteAll entityManager throw exception" + entityClass.getClass(), e);
		}
	}

	@Override
	public List<Entity> getAll() {
		try {
			final CriteriaQuery<Entity> query = entityManager.getCriteriaBuilder().createQuery(getEntityClass());
			query.from(getEntityClass());
			final List<Entity> list = entityManager.createQuery(query).getResultList();
			return list;
		} catch (Exception e) {
			throw new DaoException("method getAll entityManager throw exception" + entityClass.getClass(), e);
		}
	}

	@PersistenceContext
	protected void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private Class<Entity> getEntityClass() {
		return entityClass;
	}

}
