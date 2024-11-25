package ge.bog.luka.Mapper;

import ge.bog.luka.entity.Debt;
import ge.bog.luka.entity.dto.DebtDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DebtMapper {
    Debt mapDebtDto(DebtDto providerGroupDto);

    DebtDto mapDebt(Debt providerGroup);

    List<DebtDto> mapDebtList(List<Debt> providerGroups);

}
