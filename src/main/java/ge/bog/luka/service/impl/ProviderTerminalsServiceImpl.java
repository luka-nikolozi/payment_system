package ge.bog.luka.service.impl;

import ge.bog.luka.Mapper.ProviderTerminalsMapper;
import ge.bog.luka.entity.ProviderGroup;
import ge.bog.luka.entity.ProviderTerminals;
import ge.bog.luka.repository.ProviderRepository;
import ge.bog.luka.repository.ProviderTerminalsRepository;
import ge.bog.luka.repository.TerminalRepository;
import ge.bog.luka.service.ProviderTerminalsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderTerminalsServiceImpl implements ProviderTerminalsService {

    private final ProviderTerminalsRepository providerTerminalsRepository;
    private final ProviderRepository providerRepository;
    private final TerminalRepository terminalRepository;

    @Override
    @Transactional
    public ProviderTerminals addProviderTerminals(ProviderTerminals providerTerminals) {

        ProviderTerminals checkProviderTerminals = providerTerminalsRepository.findByProviderIdAndTerminalId(providerTerminals.getProvider().getId(), providerTerminals.getTerminal().getId());

        if (checkProviderTerminals != null) {
            throw new DataIntegrityViolationException("Provider with ID " + providerTerminals.getProvider().getId() + " and terminal with ID " + providerTerminals.getTerminal().getId() + " already exists");
        }

        try {
            return providerTerminalsRepository.save(providerTerminals);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("unique constraint violation");
        }
    }

    @Override
    public ProviderTerminals getProviderTerminals(long id) {
        return providerTerminalsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProviderTerminals with ID " + id + " not found"));
    }

    @Override
    public List<ProviderTerminals> getProvidersByTerminal(Long terminalId) {
        return providerTerminalsRepository.findByTerminalId(terminalId);
    }

    @Override
    public List<ProviderTerminals> getTerminalsByProvider(Long providerId) {
        return providerTerminalsRepository.findByProviderId(providerId);
    }

    @Override
    public List<ProviderTerminals> getAllTerminals() {
        return providerTerminalsRepository.findAll();
    }

    @Override
    public void deleteProviderTerminalsById(Long id) {
        if (!providerTerminalsRepository.existsById(id)) {
            throw new EntityNotFoundException("ProviderTerminals with ID " + id + " not found");
        }
        providerTerminalsRepository.deleteById(id);
    }

    @Override
    public ProviderTerminals updateProviderTerminalsById(Long id, ProviderTerminals updateProviderTerminals) {

        ProviderTerminals providerTerminals = providerTerminalsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProviderTerminals with ID " + id + " not found"));

        ProviderTerminals checkProviderTerminals = providerTerminalsRepository.findByProviderIdAndTerminalId(providerTerminals.getProvider().getId(), providerTerminals.getTerminal().getId());

        if (checkProviderTerminals != null) {
            throw new DataIntegrityViolationException("Provider with ID " + providerTerminals.getProvider().getId() + " and terminal with ID " + providerTerminals.getTerminal().getId() + " already exists");
        }

        providerTerminals.setProvider(updateProviderTerminals.getProvider());
        providerTerminals.setTerminal(updateProviderTerminals.getTerminal());

        try {
            return providerTerminalsRepository.save(providerTerminals);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("unique constraint violation");
        }
    }

}
