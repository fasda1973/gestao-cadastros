package gestao_cadastros.mapper;

import org.mapstruct.Mapper;

import gestao_cadastros.dto.ClienteRequest;
import gestao_cadastros.dto.ClienteResponse;
import gestao_cadastros.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	
	ClienteResponse toResponse(Cliente cliente);
	
	Cliente toEntity(ClienteRequest request);

}