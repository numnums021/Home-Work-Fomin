package com.sbrf.reboot.repository;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Set;

public interface AccountRepository {

    Set<Long> getAllContractsByClientId(long clientId) throws FileNotFoundException;

    Set<Long> getBanksByContractNumber(long contractNumber);

}
