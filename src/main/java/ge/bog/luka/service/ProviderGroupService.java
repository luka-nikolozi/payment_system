package ge.bog.luka.service;

import ge.bog.luka.entity.Provider;
import ge.bog.luka.entity.ProviderGroup;

import java.util.List;

public interface ProviderGroupService {

    ProviderGroup addProviderGroup(ProviderGroup providerGroup);

    ProviderGroup getProviderGroupById(Long id);

    List<ProviderGroup> getAllProviderGroups();

    List<Provider> getAllProviders(Long id);

    void deleteProviderGroupById(Long id);

    ProviderGroup updateProviderGroupById(Long id, ProviderGroup providerGroup);
}
