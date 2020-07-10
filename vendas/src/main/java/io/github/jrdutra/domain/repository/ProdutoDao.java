package io.github.jrdutra.domain.repository;

import io.github.jrdutra.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoDao extends JpaRepository<Produto, Integer> {

}
