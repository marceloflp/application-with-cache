
package br.com.marceloflp.spring_boot_with_cache;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.marceloflp.spring_boot_with_cache.entities.Produto;
import br.com.marceloflp.spring_boot_with_cache.repositories.ProdutoRepository;

@Component
@Profile("carga")
public class CargaProdutos implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;

    public CargaProdutos(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) {
        int total = 10_000;
        int tamanhoLote = 500;

        String[] categorias = {
            "Eletrônicos",
            "Informática",
            "Livros",
            "Casa",
            "Esportes"
        };

        long inicio = System.currentTimeMillis();

        for (int inicioLote = 0; inicioLote < total;
             inicioLote += tamanhoLote) {

            int fimLote = Math.min(inicioLote + tamanhoLote, total);
            List<Produto> produtos = new ArrayList<>();

            for (int i = inicioLote; i < fimLote; i++) {
                Produto produto = new Produto();

                produto.setNome("Produto de teste " + (i + 1));
                produto.setDescricao(
                    "Produto gerado automaticamente para testes de cache. "
                    + "Registro: " + (i + 1)
                );

                double preco = ThreadLocalRandom.current()
                    .nextDouble(10.0, 2000.0);

                produto.setPreco(
                    BigDecimal.valueOf(preco)
                        .setScale(2, java.math.RoundingMode.HALF_UP)
                );

                produto.setQuantidadeEstoque(
                    ThreadLocalRandom.current().nextInt(0, 501)
                );

                produto.setCategoria(
                    categorias[i % categorias.length]
                );

                produtos.add(produto);
            }

            produtoRepository.saveAll(produtos);

            System.out.printf(
                "Progresso: %d/%d produtos inseridos%n",
                fimLote, total
            );
        }

        long fim = System.currentTimeMillis();

        System.out.printf(
            "Carga concluída! Total: %d produtos. Tempo: %d ms%n",
            produtoRepository.count(),
            fim - inicio
        );
    }
}

