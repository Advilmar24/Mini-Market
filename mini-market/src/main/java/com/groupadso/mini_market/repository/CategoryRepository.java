package com.groupadso.mini_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupadso.mini_market.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
