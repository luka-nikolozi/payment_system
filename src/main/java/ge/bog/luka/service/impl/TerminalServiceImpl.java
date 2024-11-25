package ge.bog.luka.service.impl;

import ge.bog.luka.entity.ProviderGroup;
import ge.bog.luka.entity.ProviderTerminals;
import ge.bog.luka.entity.Terminal;
import ge.bog.luka.repository.ProviderRepository;
import ge.bog.luka.repository.ProviderTerminalsRepository;
import ge.bog.luka.repository.TerminalRepository;
import ge.bog.luka.service.TerminalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TerminalServiceImpl implements TerminalService {

    private final TerminalRepository terminalRepository;

    @Override
    @Transactional
    public Terminal addTerminal(Terminal terminal) {

        try {
            return terminalRepository.save(terminal);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("unique constraint violation");
        }
    }

    @Override
    public Terminal getTerminalById(Long id) {
        return terminalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Terminal with ID " + id + " not found"));
    }

    @Override
    public List<Terminal> getAllTerminals() {
        return terminalRepository.findAll();
    }

    @Override
    public void deleteTerminalById(Long id) {
        if (!terminalRepository.existsById(id)) {
            throw new EntityNotFoundException("Terminal with ID " + id + " not found");
        }
        terminalRepository.deleteById(id);
    }

    @Override
    public Terminal updateTerminalById(Long id, Terminal updatedTerminal) {

        Terminal terminal = terminalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Terminal with ID " + id + " not found"));

        terminal.setAddress(updatedTerminal.getAddress());
        terminal.setActive(updatedTerminal.getActive());
        terminal.setLastAccessTime(updatedTerminal.getLastAccessTime());

        try {
            return terminalRepository.save(terminal);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("unique constraint violation");
        }
    }

}
