package com.ibeetl.utils.password;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordBO {
    private String salt;
    private String encryptPassword;
}
