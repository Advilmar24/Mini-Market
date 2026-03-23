package com.groupadso.mini_market.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupadso.mini_market.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
