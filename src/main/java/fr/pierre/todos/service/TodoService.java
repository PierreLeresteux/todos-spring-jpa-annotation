package fr.pierre.todos.service;

import fr.pierre.todos.model.Todo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;


@Service
@Transactional(propagation = REQUIRED, readOnly = true, value = "transactionManager")
public class TodoService {

	@PersistenceContext(name="todoPersistenceUnit")
	protected EntityManager entityManager;

	public List<Todo> getTodos(){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Todo> criteriaQuery = criteriaBuilder.createQuery(Todo.class);
		Root<Todo> root = criteriaQuery.from(Todo.class);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

		final TypedQuery<Todo> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Transactional(propagation = REQUIRES_NEW, readOnly = false, value = "transactionManager")
	public Todo create(Todo todo) {
		entityManager.persist(todo);
		return todo;
	}

	@Transactional(propagation = REQUIRED, readOnly = false, value = "transactionManager")
	public void delete(final Long id) {
		Todo todo = getTodo(id);
		if (todo!=null){
			entityManager.remove(todo);
		}
	}

	public Todo getTodo(final Long id) {
		return entityManager.find(Todo.class, id);
	}

	@Transactional(propagation = REQUIRED, readOnly = false, value = "transactionManager")
	public Todo updateTodo(final Long id, final Todo todo) {
		Todo oldTodo = getTodo(id);
		todo.setId(oldTodo.getId());
		return entityManager.merge(todo);
	}
}
