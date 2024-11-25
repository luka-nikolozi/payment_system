package ge.bog.luka.controller;

import ge.bog.luka.Mapper.ProviderMapper;
import ge.bog.luka.entity.Provider;
import ge.bog.luka.entity.dto.request.ProviderRequest;
import ge.bog.luka.entity.dto.ProviderDto;
import ge.bog.luka.service.ProviderGroupService;
import ge.bog.luka.service.ProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
@RequiredArgsConstructor
@Slf4j
public class ProviderController {

    private final ProviderService providerService;
    private final ProviderGroupService providerGroupService;
    private final ProviderMapper providerMapper;

    @PostMapping()
    public ResponseEntity<ProviderDto> createProvider(@Valid @RequestBody ProviderRequest createProviderRequest) {
        Provider provider = Provider.builder()
                .name(createProviderRequest.getName())
                .payCode(createProviderRequest.getPayCode())
                .payCode(createProviderRequest.getPayCode())
                .maxThreads(createProviderRequest.getMaxThreads())
                .minAmount(createProviderRequest.getMinAmount())
                .maxAmount(createProviderRequest.getMaxAmount())
                .active(createProviderRequest.isActive())
                .providerGroup(providerGroupService.getProviderGroupById(createProviderRequest.getProviderGroupId()))
                .build();
        Provider savedProvider = providerService.addProvider(provider);
        return ResponseEntity.status(HttpStatus.CREATED).body(providerMapper.mapProvider(savedProvider));
    }

    @GetMapping()
    public ResponseEntity<ProviderDto> getProviderById(@RequestParam Long id) {
        Provider provider = providerService.getProviderById(id);
        return ResponseEntity.ok(providerMapper.mapProvider(provider));
    }

    @GetMapping("findAll")
    public List<ProviderDto> getAllProviders() {
        return providerMapper.mapProviderList(providerService.getAllProviders());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProviderById(@RequestParam Long id) {
        providerService.deleteProviderById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<ProviderDto> updateProviderById(@RequestParam Long id, @Valid @RequestBody ProviderRequest updatedProviderRequest) {
        Provider provider = Provider.builder()
                .name(updatedProviderRequest.getName())
                .payCode(updatedProviderRequest.getPayCode())
                .payCode(updatedProviderRequest.getPayCode())
                .maxThreads(updatedProviderRequest.getMaxThreads())
                .minAmount(updatedProviderRequest.getMinAmount())
                .maxAmount(updatedProviderRequest.getMaxAmount())
                .active(updatedProviderRequest.isActive())
                .providerGroup(providerGroupService.getProviderGroupById(updatedProviderRequest.getProviderGroupId()))
                .build();

        Provider updatedProvider = providerService.updateProviderById(id, provider);
        return ResponseEntity.ok(providerMapper.mapProvider(updatedProvider));
    }
}
