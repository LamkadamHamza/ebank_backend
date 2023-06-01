package com.lamkadam.ebank_backend.repositories;

import com.lamkadam.ebank_backend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository  extends JpaRepository<BankAccount,String> {
}
