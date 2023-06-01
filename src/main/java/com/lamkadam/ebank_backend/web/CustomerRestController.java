package com.lamkadam.ebank_backend.web;

import com.lamkadam.ebank_backend.dtos.AccountHistoryDTO;
import com.lamkadam.ebank_backend.dtos.AccountOperationDTO;
import com.lamkadam.ebank_backend.dtos.BankAccountDTO;
import com.lamkadam.ebank_backend.dtos.CustomerDTO;
import com.lamkadam.ebank_backend.exception.BankAccountNotFoundException;
import com.lamkadam.ebank_backend.exception.CustomerNotFoundException;
import com.lamkadam.ebank_backend.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j

public class CustomerRestController {

    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        bankAccountService.deleteCustomer(id);
    }

    @RestController
    public class BankAccountRestAPI {
        private BankAccountService bankAccountService;

        public BankAccountRestAPI(BankAccountService bankAccountService) {
            this.bankAccountService = bankAccountService;
        }
    }

        @GetMapping("/accounts/{accountId}")
        public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
            return bankAccountService.getBankAccount(accountId);
        }

    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "5")int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }
}
