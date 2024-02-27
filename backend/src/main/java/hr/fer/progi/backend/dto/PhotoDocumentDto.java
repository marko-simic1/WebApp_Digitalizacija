package hr.fer.progi.backend.dto;

import hr.fer.progi.backend.entity.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PhotoDocumentDto {

    private Long photoId;
    private String photoUrl;
    private String photoName;
    private Long documentId;
    private String documentUrl;
    private String documentName;
    private DocumentType documentType;
    private EmployeeDto employeeDto;
}
