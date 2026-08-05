package gestao_cadastros.mapper;

import org.mapstruct.Mapper;
import gestao_cadastros.dto.ProdutoRequest;
import gestao_cadastros.dto.ProdutoResponse;
import gestao_cadastros.entity.Produto;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
	
	ProdutoResponse toResponse(Produto produto);
	
	Produto toEntity(ProdutoRequest request);

}