package com.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.training.domain.Product;

@Transactional
@Primary
@Repository
public class ProductDAOJpaImpl implements ProductDAO {
	
	@Autowired
	EntityManager em;

	@Override
	public Product save(Product toBeSaved) {
		em.persist(toBeSaved);
		return toBeSaved;
	}

	@Override
	public Product findById(int id) {
		return em.find(Product.class, id);
	}

	@Override
	public List<Product> findAll() {
		Query q = em.createQuery("seleCt p fRom Product as p");
		return q.getResultList();
	}

	@Override
	public void deleteById(int id) {
		Query q = em.createQuery("delete from Product p where p.id=:idParam");
		q.setParameter("idParam", id);
		q.executeUpdate();
//		Product existing = em.find(Product.class, id);
//		if(existing != null) {
//			em.remove(existing);
//		}
	}

}