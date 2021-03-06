package com.ufcg.psoft.mercadofacil.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.DTO.CompraDTO;
import com.ufcg.psoft.mercadofacil.DTO.CompraDetalhesDTO;
import com.ufcg.psoft.mercadofacil.DTO.CompraHistoricoDTO;
import com.ufcg.psoft.mercadofacil.DTO.FinalizacaoCompraDTO;
import com.ufcg.psoft.mercadofacil.mappers.CompraMapper;
import com.ufcg.psoft.mercadofacil.mappers.InfoPagamentoMapper;
import com.ufcg.psoft.mercadofacil.models.Compra;
import com.ufcg.psoft.mercadofacil.models.InfoPagamento;
import com.ufcg.psoft.mercadofacil.models.Lote;
import com.ufcg.psoft.mercadofacil.models.Produto;
import com.ufcg.psoft.mercadofacil.models.ProdutoCarrinho;
import com.ufcg.psoft.mercadofacil.models.Usuario;
import com.ufcg.psoft.mercadofacil.repositories.CompraRepository;
import com.ufcg.psoft.mercadofacil.repositories.LoteRepository;
import com.ufcg.psoft.mercadofacil.repositories.UsuarioRepository;
import com.ufcg.psoft.mercadofacil.utils.CustomErrorType;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private LoteRepository loteRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public ResponseEntity<?> finalizarCompra(
			List<ProdutoCarrinho> produtosCarrinho,
			FinalizacaoCompraDTO finalizacaoCompraDTO){
		
		//Validações
		
		if(produtosCarrinho.isEmpty()) {
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("Não é permitido finalizar uma compra com o carrinho vazio"),
					HttpStatus.NOT_FOUND
				);
		}
		
		// Confere se há método de pagamento
		if(finalizacaoCompraDTO == null) {
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("A compra deve receber um método de pagamento"),
					HttpStatus.NOT_FOUND);
		}
		
		//Confere se usuário comprador é null
		if(finalizacaoCompraDTO.getUsuarioId() == null) {
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("A compra deve possuir um cliente"),
					HttpStatus.BAD_REQUEST);
		}
		
		//Confere se o usuário está cadastrado no sistema
		if(!usuarioRepository.existsById(finalizacaoCompraDTO.getUsuarioId())) {
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("O cliente fornecido não está cadastrado no sistema"),
					HttpStatus.NOT_FOUND);
		}
		
		//Confere se os produtos fornecidos estão válidos/disponíveis
		if(!isLotesProdutosCarrinhoDeComprasValidos(produtosCarrinho)) {
			return new ResponseEntity<CustomErrorType>(
				new CustomErrorType("Não temos em estoque suficiente todos os produtos desejados"),
				HttpStatus.CONFLICT
				);
		}
		
		InfoPagamento infoPagamento = InfoPagamentoMapper
				.mapInfoPagamentoFinalizacaoCompraDTOToInfoPagamento(
						finalizacaoCompraDTO.getInfoPagamento()
						);
		
		Usuario usuarioBd = this.usuarioRepository
				.findById(finalizacaoCompraDTO.getUsuarioId())
				.get();
		
		Compra compraASerFinalizada = new Compra(produtosCarrinho,infoPagamento, usuarioBd);
		Compra compraFinalizada = this.compraRepository.save(compraASerFinalizada);
		
		atualizaLotesProdutosCarrinhoDeCompras(produtosCarrinho);		
		
		CompraDTO compraFinalizadaDTO = CompraMapper.mapCompraToCompraDTO(compraFinalizada);
		
		return new ResponseEntity<CompraDTO>(compraFinalizadaDTO, HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> buscaCompraPorId(Long id){
		Optional<Compra> optCompraBD = this.compraRepository.findById(id);
		
		if(!optCompraBD.isPresent()) {
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("Compra não encontrada"),
					HttpStatus.NOT_FOUND);
		}
		
		Compra compraBD = optCompraBD.get();
		
		CompraDetalhesDTO compraDetalhesDTO = CompraMapper.mapCompraToCompraDetalhesDTO(compraBD);
		
		return new ResponseEntity<CompraDetalhesDTO>(compraDetalhesDTO, HttpStatus.OK);
	}
	
	public ResponseEntity<?> listaHistoricoCompras() {
		List<Compra> compras = this.compraRepository.findAll();
		
		List<CompraHistoricoDTO> comprasHistoricoDTO = new ArrayList<>();
		
		for (Compra compra : compras) {
			CompraHistoricoDTO compraHistoricoDTO = CompraMapper
					.mapCompraToCompraHistoricoDTO(compra);
			
			comprasHistoricoDTO.add(compraHistoricoDTO);
		}
		
		return new ResponseEntity<List<CompraHistoricoDTO>>(comprasHistoricoDTO, HttpStatus.OK);
	}
	
	private boolean isLotesProdutosCarrinhoDeComprasValidos(List<ProdutoCarrinho> produtosCarrinho) {
	
		for (ProdutoCarrinho produtoCarrinho : produtosCarrinho) {
			
			Produto produto = produtoCarrinho.getProduto();
			int quantidadeProdutoCarrinho = produtoCarrinho.getQuantidadeProdutos();
			
			Lote loteDoProduto = this.loteRepository.findByProdutoId(produto.getId());
			long quantidadeProdutoLote = loteDoProduto.getNumeroDeItens();
			
			if(quantidadeProdutoLote <= 0 || quantidadeProdutoCarrinho > quantidadeProdutoLote) {
				return false;
			}
		}
	
		return true;
	}

	@Transactional
	private void atualizaLotesProdutosCarrinhoDeCompras(List<ProdutoCarrinho> produtosCarrinho) {
		
		for (ProdutoCarrinho produtoCarrinho : produtosCarrinho) {
			
			Produto produto = produtoCarrinho.getProduto();
			int quantidadeProdutoCarrinho = produtoCarrinho.getQuantidadeProdutos();
			
			
			Lote loteDoProduto = this.loteRepository.findByProdutoId(produto.getId());
			long qtdProdutoLote = loteDoProduto.getNumeroDeItens();
			
			//FIXME: lançar exception se o numero de produtos no carrinho for menor que o existente no lote
			
			int totalProdutosLotePosCompra = (int)(qtdProdutoLote - quantidadeProdutoCarrinho);
			
			loteDoProduto.setNumeroDeItens(totalProdutosLotePosCompra);
			
			this.loteRepository.save(loteDoProduto);
		}
	}
	
}
