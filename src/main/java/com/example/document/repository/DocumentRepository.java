package com.example.document.repository;

import com.example.document.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * Created by Edi on 29.07.2019.
 */
public interface DocumentRepository extends JpaRepository<Document, Long>{
    @Query(value = "select exists ( select d.id from document as d where d.name =:nameDocument)", nativeQuery = true)
    boolean isNameDocument(@Param("nameDocument") String nameDocument);

    @Query(value = "select count(d.id) from document as d " +
            "where (d.sender_id =:companyId or d.recipient_id =:companyId) and d.electronic_signature_recipient = false", nativeQuery = true)
    Long countDocumentForCompanyInRuntime(@Param("companyId") Long companyId);

    @Query(value = "select count(d.id) from document as d " +
            "where ((d.sender_id =:sendCompanyId and d.recipient_id =:recipientCompanyId) or" +
            "(d.recipient_id =:sendCompanyId and d.sender_id =:recipientCompanyId)) and d.electronic_signature_recipient = false", nativeQuery = true)
    Long countDocumentCompanyToCompany(@Param("sendCompanyId") Long sendCompanyId, @Param("recipientCompanyId") Long recipientCompanyId);

    @Query(value = "select :limitCountCreateDocumentInTime in (select count(d.id) from document as d " +
            "where d.initiation_date_time between :timestampWith and :timestampBy and d.initiator_id=:sendCompanyId)",nativeQuery = true)
    boolean isMaxCountCreateDocument(@Param("timestampWith") Timestamp timestampWith,
                                     @Param("timestampBy") Timestamp timestampBy,
                                     @Param("limitCountCreateDocumentInTime") Long limitCountCreateDocumentInTime,
                                     @Param("sendCompanyId") Long sendCompanyId);
}
