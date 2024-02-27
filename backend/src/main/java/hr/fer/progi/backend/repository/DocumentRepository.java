package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.entity.DocumentEntity;
import hr.fer.progi.backend.entity.DocumentType;
import hr.fer.progi.backend.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    List<DocumentEntity> findByType(DocumentType documentType);

    List<DocumentEntity> findByVerificationEmployeeIdAndVerifiedIsFalse(Long validationEmployee_id);
    List<DocumentEntity> findByToBeSignedIsTrueAndVerifiedIsTrue();

    List<DocumentEntity> findByScanEmployeeIdAndCorrectIsNullAndVerificationEmployeeIdIsNull(Long scanEmployeeId);


    List<DocumentEntity> findByScanEmployeeId(Long employeeId);

    @Query("SELECT d FROM DocumentEntity d WHERE d.verified = true AND (d.toBeSigned = false OR d.toBeSigned IS NULL)")
    List<DocumentEntity> findAllVerifiedDocuments();



    @Query("SELECT d FROM DocumentEntity d WHERE d.verified = true AND d.toBeSigned = true")
    List<DocumentEntity> findDocumentsToBeSigned();

    Optional<DocumentEntity> findByPhoto(PhotoEntity photo);



}
