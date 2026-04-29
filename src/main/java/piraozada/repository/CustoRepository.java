package piraozada.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import piraozada.domain.entity.Custo;

public interface CustoRepository extends JpaRepository<Custo, Long> {
}