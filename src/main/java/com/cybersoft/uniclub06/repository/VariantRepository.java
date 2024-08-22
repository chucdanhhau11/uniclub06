package com.cybersoft.uniclub06.repository;

import com.cybersoft.uniclub06.entity.VariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends JpaRepository<VariantEntity,Integer> {
}
