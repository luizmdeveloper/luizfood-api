package com.luizmariodev.luizfood.infrastructure.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.luizmariodev.luizfood.domain.filtro.PedidoFilter;
import com.luizmariodev.luizfood.domain.model.Pedido;
import com.luizmariodev.luizfood.domain.model.StatusPedido;
import com.luizmariodev.luizfood.domain.model.dto.PedidosDiario;
import com.luizmariodev.luizfood.domain.service.PedidosDiarioQueryService;

@Repository
public class PedidosDiarioQueryServiceImpl implements PedidosDiarioQueryService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<PedidosDiario> consultarPedidos(PedidoFilter filtro, String timeZoneOffset) {		
		var builder = entityManager.getCriteriaBuilder();
		var criteria = builder.createQuery(PedidosDiario.class);
		var root = criteria.from(Pedido.class);
		
		var functionConvertTzDataCriacao = builder.function("convert_tz", Date.class, root.get("dataCriacao"), builder.literal("+00:00"), builder.literal(timeZoneOffset));
		
		var functionDateDataCriacao = builder.function("date", Date.class, functionConvertTzDataCriacao);
		
		var selection = builder.construct(PedidosDiario.class, 
					functionDateDataCriacao, 
					builder.count(root.get("id")), 
					builder.sum(root.get("valorTotal")));
		
		criteria.select(selection);
		criteria.where(adicionarFiltros(builder, root, filtro));
		criteria.groupBy(functionDateDataCriacao);
		
		return entityManager.createQuery(criteria).getResultList();
	}
	
	private Predicate[] adicionarFiltros(CriteriaBuilder builder, Root<Pedido> root, PedidoFilter filtro) {		
		var predicates = new ArrayList<Predicate>();
		
		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
		
		if (filtro.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		} 
					
		if (filtro.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFinal()));
		}

		if (filtro.getDataCriacaoFinal() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFinal()));
		}		
				
	
		return predicates.toArray(new Predicate[predicates.size()]);
	} 
}