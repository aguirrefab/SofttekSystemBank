package services;

import models.banks.Bank;

/**
 * <h2>Bank Service</h2>
 * {@code IBankService} is an interface<br>
 * <p>
 * This interface defines the methods for commons behaviors.<br>
 * <p>
 * @author AguirreFab
 * @version 1.0
 *
 */

public interface IBankService {

    void addBankEntity(Bank bank);

    void deleteBankEntity(Bank bank);

    void updateBankEntity(Bank bank);

    Bank findBankByEntityCode(Long entityCode);


}
