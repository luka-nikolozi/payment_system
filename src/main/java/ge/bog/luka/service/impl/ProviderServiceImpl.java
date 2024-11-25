package ge.bog.luka.service.impl;

import ge.bog.luka.entity.Provider;
import ge.bog.luka.repository.ProviderGroupRepository;
import ge.bog.luka.repository.ProviderRepository;
//import ge.bog.luka.repository.ProviderTerminalsRepository;
import ge.bog.luka.service.ProviderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderGroupRepository providerGroupRepository;

    @Override
    @Transactional
    public Provider addProvider(Provider provider) {

        validation(provider);

        try {
            return providerRepository.save(provider);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("unique constraint violation");
        }

    }

    @Override
    public Provider getProviderById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider with ID " + id + " not found"));
    }

    @Override
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    @Override
    public void deleteProviderById(Long id) {

        if (!providerRepository.existsById(id)) {
            throw new EntityNotFoundException("Provider with ID " + id + " not found");
        }
        providerRepository.deleteById(id);
    }

    @Override
    public Provider updateProviderById(Long id, Provider updatedProvider) {
        validation(updatedProvider);

        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider with ID " + id + " not found"));

        provider.setName(updatedProvider.getName());
        provider.setDebtCode(updatedProvider.getDebtCode());
        provider.setPayCode(updatedProvider.getPayCode());
        provider.setMaxThreads(updatedProvider.getMaxThreads());
        provider.setMinAmount(updatedProvider.getMinAmount());
        provider.setMaxAmount(updatedProvider.getMaxAmount());
        provider.setActive(updatedProvider.getActive());



        try {
            return providerRepository.save(provider);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("unique constraint violation");
        }
    }

    private void validation (Provider provider) {
        if (provider.getMaxThreads() < 0) {
            throw new DataIntegrityViolationException("MaxThreads must be positive");
        }else if (provider.getMinAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new DataIntegrityViolationException("MinAmount must be positive");
        }else if (provider.getMaxAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new DataIntegrityViolationException("MaxAmount must be positive");
        }
    }

}
