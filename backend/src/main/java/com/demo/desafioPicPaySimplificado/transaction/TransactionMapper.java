package com.demo.desafioPicPaySimplificado.transaction;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface TransactionMapper {

    @Mapping(target = "amount", expression = "java(transaction.getAmount())")
    TransactionDTO toDTO(Transaction transaction);

    Transaction toEntity(Transaction walletDTO);
}
