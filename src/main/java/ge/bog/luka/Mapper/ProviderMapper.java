package ge.bog.luka.Mapper;

import ge.bog.luka.entity.Provider;
import ge.bog.luka.entity.dto.ProviderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {ProviderGroupMapper.class, TerminalMapper.class})
public interface ProviderMapper {

    @Mapping(target = "providerGroup.id", source = "providerGroupId")
    Provider mapProviderDto(ProviderDto providerDto);

    @Mapping(target = "providerGroupId", source = "providerGroup.id")
    ProviderDto mapProvider(Provider provider);

    @Mapping(target = "providerGroupId", source = "providerGroup.id")
    List<ProviderDto> mapProviderList(List<Provider> providersList);
}

