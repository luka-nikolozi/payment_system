package ge.bog.luka.controller;

import ge.bog.luka.Mapper.ProviderTerminalsMapper;
import ge.bog.luka.entity.Provider;
import ge.bog.luka.entity.ProviderGroup;
import ge.bog.luka.entity.ProviderTerminals;
import ge.bog.luka.entity.Terminal;
import ge.bog.luka.entity.dto.ProviderDto;
import ge.bog.luka.entity.dto.ProviderTerminalsDto;
import ge.bog.luka.entity.dto.request.ProviderTerminalsRequest;
import ge.bog.luka.service.ProviderService;
import ge.bog.luka.service.ProviderTerminalsService;
import ge.bog.luka.service.TerminalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider-terminals")
@RequiredArgsConstructor
@Slf4j
public class ProviderTerminalsController {

    private final ProviderTerminalsService providerTerminalsService;
    private final ProviderService providerService;
    private final TerminalService terminalService;
    private final ProviderTerminalsMapper providerTerminalsMapper;

    @PostMapping()
    public ResponseEntity<ProviderTerminalsDto> createProvider(@Valid @RequestBody ProviderTerminalsRequest providerTerminalsRequest) {
        ProviderTerminals providerTerminal = ProviderTerminals.builder()
                .provider(providerService.getProviderById(providerTerminalsRequest.getProviderid()))
                .terminal(terminalService.getTerminalById(providerTerminalsRequest.getTerminalid()))
                .build();

        ProviderTerminals savedproviderTerminals = providerTerminalsService.addProviderTerminals(providerTerminal);
        return ResponseEntity.status(HttpStatus.CREATED).body(providerTerminalsMapper.mapProviderTerminals(savedproviderTerminals));
    }

    @GetMapping("getProvidersByTerminalId")
    public ResponseEntity<List<ProviderTerminalsDto>> getProvidersByTerminalId(@RequestParam Long id) {
        List<ProviderTerminals> providerTerminals = providerTerminalsService.getProvidersByTerminal(id);
        return ResponseEntity.ok(providerTerminalsMapper.mapProviderTerminalsList(providerTerminals));
    }

    @GetMapping("getTerminalsByProviderId")
    public List<ProviderTerminalsDto> getTerminalsByProviderId(@RequestParam Long id) {
        List<ProviderTerminals> providerTerminals = providerTerminalsService.getTerminalsByProvider(id);
        return providerTerminalsMapper.mapProviderTerminalsList(providerTerminals);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<ProviderTerminalsDto>> getAllProviderTerminals() {
        return ResponseEntity.ok(providerTerminalsMapper.mapProviderTerminalsList(providerTerminalsService.getAllTerminals()));
    }

    @DeleteMapping
    public ResponseEntity<Void> ProviderTerminalsById(@RequestParam Long id) {
        providerTerminalsService.deleteProviderTerminalsById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<ProviderTerminalsDto> updateProviderById(@RequestParam Long id, @Valid @RequestBody ProviderTerminalsRequest providerTerminalsRequest) {
        ProviderTerminals providerTerminal = ProviderTerminals.builder()
                .provider(providerService.getProviderById(providerTerminalsRequest.getProviderid()))
                .terminal(terminalService.getTerminalById(providerTerminalsRequest.getTerminalid()))
                .build();

        ProviderTerminals updatedProviderTerminals = providerTerminalsService.updateProviderTerminalsById(id, providerTerminal);
        return ResponseEntity.ok(providerTerminalsMapper.mapProviderTerminals(updatedProviderTerminals));
    }

}
