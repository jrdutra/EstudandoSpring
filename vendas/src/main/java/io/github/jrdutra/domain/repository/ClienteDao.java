package io.github.jrdutra.domain.repository;

import io.github.jrdutra.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClienteDao extends JpaRepository<Cliente, Integer> {

    @Query(value = "SELECT c FROM Cliente c WHERE c.nome like :nome")
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    @Query("DELETE from Cliente c WHERE c.nome = :nome")
    @Modifying
    void deleteByNome(String nome);

    boolean existsByNome(String nome);

    @Query("SELECT c FROM Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

}
