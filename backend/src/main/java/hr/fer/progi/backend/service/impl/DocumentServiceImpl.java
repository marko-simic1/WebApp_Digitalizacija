package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.ChooseReviserDto;
import hr.fer.progi.backend.dto.DocumentDto;
import hr.fer.progi.backend.dto.EmployeeDto;
import hr.fer.progi.backend.dto.PhotoDocumentDto;
import hr.fer.progi.backend.entity.DocumentEntity;
import hr.fer.progi.backend.entity.DocumentType;
import hr.fer.progi.backend.entity.EmployeeEntity;
import hr.fer.progi.backend.entity.PhotoEntity;
import hr.fer.progi.backend.exception.DocumentNotFoundException;
import hr.fer.progi.backend.exception.EmployeeNotFoundException;
import hr.fer.progi.backend.repository.ArchiveRepository;
import hr.fer.progi.backend.repository.DocumentRepository;
import hr.fer.progi.backend.repository.EmployeeRepository;
import hr.fer.progi.backend.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final EmployeeRepository employeeRepository;
    private final ArchiveRepository archiveRepository;


    @Override
    public List<PhotoDocumentDto> getDocumentsByType(DocumentType documentType) {
        List<DocumentEntity> documents = documentRepository.findByType(documentType);

        documents.removeIf(document -> archiveRepository.existsByDocumentId(document.getId()));

        return generatePhotoDocumentDtos(documents);

    }


    @Override
    public String changeDocumentType(DocumentDto documentDto) {

        DocumentEntity documentEntity = documentRepository.findById(documentDto.getId())
                .orElseThrow(() -> new DocumentNotFoundException("Document could not be found"));

        documentEntity.setType(documentDto.getType());

        documentRepository.save(documentEntity);

        return String.format("Type of document %s has been changed to %s", documentEntity.getId(), documentEntity.getType());
    }

    @Override
    public List<DocumentEntity> getAllVerifiedDocuments() {
        return documentRepository.findAllVerifiedDocuments();
    }

    @Override
    public String setDocumentToBeSinged(DocumentDto documentDto) {
        DocumentEntity document = documentRepository.findById(documentDto.getId())
                .orElseThrow(() -> new DocumentNotFoundException("Document not found"));

        document.setToBeSigned(documentDto.getToBeSigned());
        documentRepository.save(document);

        return String.format("Document %s has been sent to director for signing", document.getId());
    }

    @Override
    public List<PhotoDocumentDto> getRevisionDocuments(Principal connectedEmployee) {
        EmployeeEntity employeeEntity = (EmployeeEntity) ((UsernamePasswordAuthenticationToken) connectedEmployee).getPrincipal();
        List<DocumentEntity> documents = documentRepository.findByVerificationEmployeeIdAndVerifiedIsFalse(employeeEntity.getId());

        return generatePhotoDocumentDtos(documents);
    }

    @Override
    public List<PhotoDocumentDto> getAllDocumentsForSigning() {
        List<DocumentEntity> documents = documentRepository.findByToBeSignedIsTrueAndVerifiedIsTrue();

        return generatePhotoDocumentDtos(documents);

    }

    @Override
    public List<PhotoDocumentDto> getDocumentHistory(Principal connectedEmployee) {

        EmployeeEntity employeeEntity = (EmployeeEntity) ((UsernamePasswordAuthenticationToken) connectedEmployee).getPrincipal();
        List<DocumentEntity> documents = documentRepository.findByScanEmployeeId(employeeEntity.getId());

        return generatePhotoDocumentDtos(documents);
    }

    @Override
    public String signDocument(DocumentDto documentDto) {

        DocumentEntity document = documentRepository.findById(documentDto.getId()).orElseThrow(() -> new DocumentNotFoundException("Document not found"));
        document.setSigned(documentDto.getSigned());
        document.setToBeSigned(false);

        documentRepository.save(document);

        return String.format("Document %s has been signed", document.getId());
    }

    @Override
    public List<PhotoDocumentDto> getAllDocuments() {
        List<DocumentEntity> documents = documentRepository.findAll();

        List<PhotoDocumentDto> photoDocumentDtos = generatePhotoDocumentDtos(documents).stream()
                .map(dto->{
                    DocumentEntity document = documentRepository.findById(dto.getDocumentId())
                            .orElseThrow(() -> new DocumentNotFoundException("Document not found"));

                    EmployeeEntity employee = document.getScanEmployee();
                    EmployeeDto employeeDto = EmployeeDto.builder()
                            .id(employee.getId())
                            .firstName(employee.getFirstName())
                            .lastName(employee.getLastName())
                            .build();

                    dto.setEmployeeDto(employeeDto);
                    return dto;

                }).collect(Collectors.toList());

        return photoDocumentDtos;
    }

    @Override
    public void sendToReviser(ChooseReviserDto choosereviserdto) {
        DocumentEntity document = documentRepository.findById(choosereviserdto.getDocumentId())
                .orElseThrow(() -> new DocumentNotFoundException("Document not found"));

        EmployeeEntity reviser = employeeRepository.findById(choosereviserdto.getReviserId())
                .orElseThrow(() -> new EmployeeNotFoundException("Reviser not found"));

        document.setVerificationEmployee(reviser);
        documentRepository.save(document);
    }

    @Override
    public String setCorrect(DocumentDto documentDto) {
        DocumentEntity document = documentRepository.findById(documentDto.getId())
                .orElseThrow(() -> new DocumentNotFoundException("Document not found"));

        document.setCorrect(documentDto.getCorrect());
        documentRepository.save(document);

        return String.format("Document %s is set to be %s", document.getId(), document.getCorrect() ? "correct" : "incorrect");
    }

    @Override
    public String setVerified(DocumentDto documentDto) {
        DocumentEntity document = documentRepository.findById(documentDto.getId())
                .orElseThrow(() -> new DocumentNotFoundException("Document not found"));

        document.setVerified(documentDto.getVerified());
        documentRepository.save(document);
        return String.format("Document %s has been verified", document.getId());
    }

    @Override
    public DocumentType categorizeDocument(String documentText) {

        String patternString = "P\\d{9}|R\\d{6}|INT\\s\\d{4}";

        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(documentText);

        if (matcher.find()) {
            String documentTag = matcher.group();
            if (documentTag.startsWith("INT")) {
                return DocumentType.INTERNAL_DOCUMENT;
            } else if (documentTag.startsWith("P")) {
                return DocumentType.OFFER;
            } else if (documentTag.startsWith("R")) {
                return DocumentType.RECEIPT;
            }
        }

        return null;
    }

    @Override
    public List<PhotoDocumentDto> getAllUnconfirmedDocuments(Principal connectedEmployee) {

        EmployeeEntity employee = (EmployeeEntity) ((UsernamePasswordAuthenticationToken)connectedEmployee).getPrincipal();

        List<DocumentEntity> documents = documentRepository.findByScanEmployeeIdAndCorrectIsNullAndVerificationEmployeeIdIsNull(employee.getId());

        return generatePhotoDocumentDtos(documents);
    }


    public List<PhotoDocumentDto> generatePhotoDocumentDtos(List<DocumentEntity> documents) {
        return documents.stream()
                .map(document -> {
                    PhotoEntity photo = document.getPhoto();

                    return PhotoDocumentDto.builder()
                            .documentId(document.getId())
                            .documentUrl(document.getUrl())
                            .documentName(document.getFileName())
                            .documentType(document.getType())
                            .photoId(photo.getPhotoID())
                            .photoUrl(photo.getUrl())
                            .photoName(photo.getImageName())
                            .build();
                }).collect(Collectors.toList());
    }
}



