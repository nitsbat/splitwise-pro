package com.app.splitwise.controller;


import com.app.splitwise.dto.ExpenseRequestBodyDTO;
import com.app.splitwise.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PutMapping(value = "/paid-money/{groupName}")
    public ResponseEntity<String> addMoney(@PathVariable() String groupName,
                                           @RequestBody()ExpenseRequestBodyDTO expenseRequestBodyDTO) {

        expenseService.addMoney(groupName, expenseRequestBodyDTO);
        return ResponseEntity.ok("OK");
    }
}
