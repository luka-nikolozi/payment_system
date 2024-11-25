package ge.bog.luka.service.impl;

import ge.bog.luka.entity.Provider;
import ge.bog.luka.entity.ProviderGroup;
import ge.bog.luka.repository.ProviderGroupRepository;
import ge.bog.luka.service.ProviderGroupService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.action.internal.EntityActionVetoException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderGroupServiceImpl implements ProviderGroupService {

    private final ProviderGroupRepository providerGroupRepository;

    @Override
    @Transactional
    public ProviderGroup addProviderGroup(ProviderGroup providerGroup) {
        try {
            return providerGroupRepository.save(providerGroup);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("unique constraint violation");
        }
    }

    @Override
    public ProviderGroup getProviderGroupById(Long id) {
        return providerGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider Group with ID " + id + " not found"));
    }

    @Override
    public List<ProviderGroup> getAllProviderGroups() {
        return providerGroupRepository.findAll();
    }

    @Override
    @Transactional
    public List<Provider> getAllProviders(Long id) {
        ProviderGroup providerGroup = providerGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider Group with ID " + id + " not found"));
        List<Provider> providers = providerGroup.getProviders();
        return providers;
    }

    @Override
    public void deleteProviderGroupById(Long id) {
        if (!providerGroupRepository.existsById(id)) {
            throw new EntityNotFoundException("Provider Group with ID " + id + " not found");
        }
        providerGroupRepository.deleteById(id);
    }

    @Override
    public ProviderGroup updateProviderGroupById(Long id, ProviderGroup updatedProviderGroup) {

        ProviderGroup providerGroup = providerGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider Group with ID " + id + " not found"));

        providerGroup.setName(updatedProviderGroup.getName());
        providerGroup.setDescription(updatedProviderGroup.getDescription());

        try {
            return providerGroupRepository.save(providerGroup);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("unique constraint violation");
        }
    }

}
