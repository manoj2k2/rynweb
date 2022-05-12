package com.adi.productmanagement.generic;

import com.adi.productmanagement.repository.CategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public class ServiceInterface<T, ID>  {

    public JpaRepository<T, ID> getJpaRepository() {
        return jpaRepository;
    }

    public void setJpaRepository(JpaRepository<T, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public JpaRepository<T, ID> jpaRepository;
}
