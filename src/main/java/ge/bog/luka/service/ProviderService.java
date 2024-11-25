package ge.bog.luka.service;


import ge.bog.luka.entity.Provider;

import java.util.List;

public interface ProviderService {

    Provider addProvider(Provider provider);

    Provider getProviderById(Long id);

    List<Provider> getAllProviders();

    void deleteProviderById(Long id);

    Provider updateProviderById(Long id, Provider updatedProvider);
}
