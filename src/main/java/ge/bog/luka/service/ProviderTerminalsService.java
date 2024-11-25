package ge.bog.luka.service;

import ge.bog.luka.entity.ProviderTerminals;

import java.util.List;

public interface ProviderTerminalsService {

    ProviderTerminals addProviderTerminals(ProviderTerminals providerTerminals);

    ProviderTerminals getProviderTerminals(long id);

    List<ProviderTerminals> getProvidersByTerminal(Long id);

    List<ProviderTerminals> getTerminalsByProvider(Long id);

    List<ProviderTerminals> getAllTerminals();

    void deleteProviderTerminalsById(Long id);

    ProviderTerminals updateProviderTerminalsById(Long id, ProviderTerminals providerTerminals);
}
