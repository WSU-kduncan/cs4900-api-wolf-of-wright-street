package com.wolf.budgetapp.mapper;

import com.wolf.budgetapp.dto.CashflowTypeDto;
import com.wolf.budgetapp.model.CashflowType;
import com.wolf.budgetapp.service.CashflowTypeService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {CashflowTypeService.class})
public interface CashflowTypeDtoMapper {

  CashflowType toEntity(CashflowTypeDto cashflowTypeDto) throws EntityNotFoundException;

  CashflowTypeDto toDto(CashflowType cashflowType) throws EntityNotFoundException;

  List<CashflowTypeDto> toDtoList(List<CashflowType> cashflowTypes) throws EntityNotFoundException;
}
