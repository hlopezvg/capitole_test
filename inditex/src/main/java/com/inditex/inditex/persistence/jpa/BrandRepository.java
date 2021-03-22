package com.inditex.inditex.persistence.jpa;

import com.inditex.inditex.persistence.entitites.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {}
