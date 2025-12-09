package com.altrocks.forms.kitsheetEms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionHeaderRepository extends JpaRepository<ProductionHeaderEntity, Long> {
}

