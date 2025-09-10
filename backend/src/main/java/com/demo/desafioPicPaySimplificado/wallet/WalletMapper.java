package com.demo.desafioPicPaySimplificado.wallet;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface WalletMapper {

    WalletDTO toDTO(Wallet wallet);

    Wallet toEntity(WalletDTO walletDTO);
}
