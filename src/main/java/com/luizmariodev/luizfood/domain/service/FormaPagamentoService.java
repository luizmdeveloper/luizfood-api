package com.luizmariodev.luizfood.domain.service;

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
	
	private static final String FORMA_DE_PAGAMENTO_NAO_PODE_EXCLUIDA = "Forma de pagamento com código %d, não pode ser excluída";
	private static final String FORMA_DE_PAGAMENTO_NAO_ENCONTRADO = "Forma de pagamento com código %d, não foi encontrado";
	
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

	public void excluir(Long id) {
		try {
			formaPagamentoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(FORMA_DE_PAGAMENTO_NAO_ENCONTRADO, id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(FORMA_DE_PAGAMENTO_NAO_PODE_EXCLUIDA, id));
		}
	}
	
	public FormaPagamento buscarPorId(Long id) {
		FormaPagamento formaPagamento = formaPagamentoRepository.findById(id)
					.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(FORMA_DE_PAGAMENTO_NAO_ENCONTRADO, id)));
			
		return formaPagamento;
	}
}