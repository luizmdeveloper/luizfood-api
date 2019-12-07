package com.luizmariodev.luizfood.domain.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.EntidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.model.FormaPagamento;
import com.luizmariodev.luizfood.domain.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}

	public FormaPagamento atualizar(Long id, FormaPagamento formaPagamento) {
		FormaPagamento formaPagamentoSalva = buscarPorId(id);
		BeanUtils.copyProperties(formaPagamento, formaPagamentoSalva, "id");
		return formaPagamentoRepository.save(formaPagamentoSalva);
	}
	
	public FormaPagamento buscarPorId(Long id) {
		Optional<FormaPagamento> formaPagamentoSalva = formaPagamentoRepository.findById(id);
		
		if (!formaPagamentoSalva.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Forma de pagamento com o códgo %d, não foi encontrada", id));
		}
		
		return formaPagamentoSalva.get();
	}

	public void excluir(Long id) {
		try {
			formaPagamentoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Forma de pagamento com código %d, não foi encontrado", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Forma de pagamento com código %d, não pode ser excluída", id));
		}
		
	}

}
