package ge.bog.luka.service;

import ge.bog.luka.entity.ProviderTerminals;
import ge.bog.luka.entity.Terminal;

import java.util.List;

public interface TerminalService {

    Terminal addTerminal(Terminal terminal);

    Terminal getTerminalById(Long id);

    List<Terminal> getAllTerminals();

    void deleteTerminalById(Long id);

    Terminal updateTerminalById(Long id, Terminal updatedTerminal);
}
