package com.example.document.repository;

import com.example.document.model.Company;
import com.example.document.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Edi on 29.07.2019.
 */
public interface CompanyRepository extends JpaRepository<Company, Long>{
}
