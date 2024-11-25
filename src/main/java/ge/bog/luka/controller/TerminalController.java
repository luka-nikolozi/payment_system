package ge.bog.luka.controller;

import ge.bog.luka.Mapper.TerminalMapper;
import ge.bog.luka.entity.ProviderTerminals;
import ge.bog.luka.entity.Terminal;
import ge.bog.luka.entity.dto.TerminalDto;
import ge.bog.luka.entity.dto.request.TerminalRequest;
import ge.bog.luka.service.TerminalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/terminal")
@RequiredArgsConstructor
@Slf4j
public class TerminalController {

    private final TerminalService terminalService;
    private final TerminalMapper terminalMapper;

    @PostMapping()
    public ResponseEntity<TerminalDto> createTerminal(@Valid  @RequestBody TerminalRequest terminalRequest) {
        Terminal terminal = Terminal.builder()
                .address(terminalRequest.getAddress())
                .active(terminalRequest.getActive())
                .active(terminalRequest.getActive())
                .build();

        Terminal createdTerminal = terminalService.addTerminal(terminal);
        return ResponseEntity.status(HttpStatus.CREATED).body(terminalMapper.mapTerminal(createdTerminal));
    }

    @GetMapping()
    public ResponseEntity<TerminalDto> getTerminalById(@RequestParam Long id) {
        Terminal terminal = terminalService.getTerminalById(id);
        return ResponseEntity.ok(terminalMapper.mapTerminal(terminal));
    }

    @GetMapping(("getAll"))
    public ResponseEntity<List<TerminalDto>> getAllTerminals() {
        return ResponseEntity.ok(terminalMapper.mapTerminalList(terminalService.getAllTerminals()));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteTerminalById(@RequestParam Long id) {
        terminalService.deleteTerminalById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<TerminalDto> updateTerminalById(@RequestParam Long id, @Valid  @RequestBody TerminalRequest terminalRequest) {
        Terminal terminal = Terminal.builder()
                .address(terminalRequest.getAddress())
                .active(terminalRequest.getActive())
                .active(terminalRequest.getActive())
                .build();
        
        Terminal updatedTerminal = terminalService.updateTerminalById(id, terminal);
        return ResponseEntity.ok(terminalMapper.mapTerminal(updatedTerminal));
    }
}
