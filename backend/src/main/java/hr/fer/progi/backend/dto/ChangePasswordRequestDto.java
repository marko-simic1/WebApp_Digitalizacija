package hr.fer.progi.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequestDto {

    private String oldPassword;
    private String newPassword;
    private String passwordConfirmation;


}
