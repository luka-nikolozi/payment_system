package ge.bog.luka.Mapper;

import ge.bog.luka.entity.Terminal;
import ge.bog.luka.entity.dto.TerminalDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TerminalMapper {

    Terminal mapTerminalDto(TerminalDto terminalDto);

    TerminalDto mapTerminal(Terminal terminal);

    List<TerminalDto> mapTerminalList(List<Terminal> terminals);
}
