package com.altrocks.forms.mrin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MRINRepository  extends JpaRepository<MRINEntity, Integer>{

}
