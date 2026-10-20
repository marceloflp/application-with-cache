package br.com.marceloflp.spring_boot_with_cache;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marceloflp.spring_boot_with_cache.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
