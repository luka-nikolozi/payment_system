package ge.bog.luka.Mapper;

import ge.bog.luka.entity.ProviderTerminals;
import ge.bog.luka.entity.dto.ProviderTerminalsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {ProviderMapper.class, TerminalMapper.class})
public interface ProviderTerminalsMapper {

    @Mapping(target = "terminal.id", source = "terminalId")
    @Mapping(target = "provider.id", source = "providerId")
    ProviderTerminals mapProviderTerminalsDto(ProviderTerminalsDto providerTerminalsDto);

    @Mapping(target = "terminalId", source = "terminal.id")
    @Mapping(target = "providerId", source = "provider.id")
    ProviderTerminalsDto mapProviderTerminals(ProviderTerminals providerTerminals);

    @Mapping(target = "terminalId", source = "terminal.id")
    @Mapping(target = "providerId", source = "provider.id")
    List<ProviderTerminalsDto> mapProviderTerminalsList(List<ProviderTerminals> providerTerminals);
}
