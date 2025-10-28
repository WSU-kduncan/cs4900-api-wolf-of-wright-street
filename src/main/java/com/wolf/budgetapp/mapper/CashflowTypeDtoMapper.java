package com.wolf.budgetapp.mapper;

import com.wolf.budgetapp.dto.CashflowTypeDto;
import com.wolf.budgetapp.model.CashflowType;
import com.wolf.budgetapp.service.CashflowTypeService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
    componentModel = "spring",
    uses = {CashflowTypeService.class})
public interface CashflowTypeDtoMapper {

  CashflowType toEntity(CashflowTypeDto cashflowTypeDto) throws EntityNotFoundException;

  CashflowTypeDto toDto(CashflowType cashflowType) throws EntityNotFoundException;

  List<CashflowTypeDto> toDtoList(List<CashflowType> cashflowTypes) throws EntityNotFoundException;

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "cashflowName", source = "cashflowName")
  @Mapping(target = "cashflowDescription", source = "cashflowDescription")
  @Mapping(target = "factor", source = "factor")
  void updateEntity(CashflowTypeDto dto, @MappingTarget CashflowType entity);
}
