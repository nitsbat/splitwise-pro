package com.app.splitwise.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExpenseRequestBodyDTO {
    String username;
    String money;
}
