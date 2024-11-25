package ge.bog.luka.Mapper;

import ge.bog.luka.entity.ProviderGroup;
import ge.bog.luka.entity.dto.ProviderGroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProviderGroupMapper {

    ProviderGroup mapProviderGroupDto(ProviderGroupDto providerGroupDto);

    ProviderGroupDto mapProviderGroup(ProviderGroup providerGroup);

    List<ProviderGroupDto> mapProviderGroupList(List<ProviderGroup> providerGroups);
}
