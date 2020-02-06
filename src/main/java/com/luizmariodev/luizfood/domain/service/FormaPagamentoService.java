package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.luizmariodev.luizfood.domain.model.FormaPagamento;
import com.luizmariodev.luizfood.domain.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {
	
	private static final String FORMA_DE_PAGAMENTO_NAO_PODE_EXCLUIDA = "Forma de pagamento com código %d, não pode ser excluída";
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}

	@Transactional
	public FormaPagamento atualizar(Long id, FormaPagamento formaPagamento) {
		FormaPagamento formaPagamentoSalva = buscarPorId(id);
		BeanUtils.copyProperties(formaPagamento, formaPagamentoSalva, "id");
		return formaPagamentoRepository.save(formaPagamentoSalva);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			formaPagamentoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(FORMA_DE_PAGAMENTO_NAO_PODE_EXCLUIDA, id));
		}
	}
	
	public FormaPagamento buscarPorId(Long id) {
		FormaPagamento formaPagamento = formaPagamentoRepository.findById(id)
					.orElseThrow(() -> new FormaPagamentoNaoEncontradaException(id));
			
		return formaPagamento;
	}
}