package com.cybersoft.uniclub06.repository;

import com.cybersoft.uniclub06.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Integer> {
}
