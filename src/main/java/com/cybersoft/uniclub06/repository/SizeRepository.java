package com.cybersoft.uniclub06.repository;

import com.cybersoft.uniclub06.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity, Integer> {
}
