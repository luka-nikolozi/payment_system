package ge.bog.luka.controller;

import ge.bog.luka.Mapper.ProviderGroupMapper;
import ge.bog.luka.entity.Provider;
import ge.bog.luka.entity.ProviderGroup;
import ge.bog.luka.entity.dto.ProviderGroupDto;
import ge.bog.luka.entity.dto.request.ProviderGroupRequest;
import ge.bog.luka.service.ProviderGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("provider-group")
@RequiredArgsConstructor
@Slf4j
public class ProviderGroupController {

    private final ProviderGroupService providerGroupService;
    private final ProviderGroupMapper providerGroupMapper;

    @PostMapping()
    public ResponseEntity<ProviderGroupDto> createProviderGroup(@Valid @RequestBody ProviderGroupRequest providerGroupRequest) {
        ProviderGroup providerGroup = ProviderGroup.builder()
                .name(providerGroupRequest.getName())
                .description(providerGroupRequest.getDescription())
                .build();

        ProviderGroup savedProviderGroup = providerGroupService.addProviderGroup(providerGroup);
        return ResponseEntity.status(HttpStatus.CREATED).body(providerGroupMapper.mapProviderGroup(savedProviderGroup));
    }

    @GetMapping()
    public ResponseEntity<ProviderGroupDto> getProviderGroupById(@RequestParam Long id) {
        ProviderGroup providerGroup = providerGroupService.getProviderGroupById(id);
        return ResponseEntity.ok(providerGroupMapper.mapProviderGroup(providerGroup));
    }

    @GetMapping("getAll")
    public List<ProviderGroupDto> getAllProviderGroup() {
        return providerGroupMapper.mapProviderGroupList(providerGroupService.getAllProviderGroups());
    }

    @GetMapping("getAllProviders")
    public List<Provider> getAllProviders(Long providerGroupId) {
        return (providerGroupService.getAllProviders(providerGroupId));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteProviderGroupById(@RequestParam Long id) {
        providerGroupService.deleteProviderGroupById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<ProviderGroupDto> updateProviderGroupById(@RequestParam Long id, @Valid @RequestBody ProviderGroupRequest providerGroupRequest) {
        ProviderGroup providerGroup = ProviderGroup.builder()
                .name(providerGroupRequest.getName())
                .description(providerGroupRequest.getDescription())
                .build();

        ProviderGroup updatedProviderGroup = providerGroupService.updateProviderGroupById(id, providerGroup);
        return ResponseEntity.ok(providerGroupMapper.mapProviderGroup(updatedProviderGroup));
    }
}
