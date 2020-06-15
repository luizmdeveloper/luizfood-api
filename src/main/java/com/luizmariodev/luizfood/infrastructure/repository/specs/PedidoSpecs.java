package com.luizmariodev.luizfood.infrastructure.repository.specs;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.luizmariodev.luizfood.domain.filtro.PedidoInputFilter;
import com.luizmariodev.luizfood.domain.model.Pedido;
import com.luizmariodev.luizfood.domain.model.StatusPedido;

public class PedidoSpecs {
	
	public static Specification<Pedido> pesquisar(PedidoInputFilter filtro) {
		return (root, query, builder) -> {
			
			if (Pedido.class.equals(query.getResultType())) {
				root.fetch("restaurante").fetch("cozinha");
				root.fetch("cliente");
				root.fetch("pagamento");
				root.fetch("enderecoEntrega").fetch("cidade");				
			}
			
			var predicates = new ArrayList<Predicate>();
							
			if (filtro.getClienteId() != null) {
				predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
			}
			
			if (filtro.getRestauranteId() != null) {
				predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
			}
			
			if (filtro.getDataCriacaoInicio() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
			}
			
			if (filtro.getDataCriacaoFim() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
			}
			
			if (!StringUtils.isEmpty(filtro.getStatus())) {
				predicates.add(builder.equal(root.get("status"), StatusPedido.valueOf(filtro.getStatus())));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));			
		};
	}
	
}
