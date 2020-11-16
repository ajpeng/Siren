package com.pengal.Siren.Repository;

import com.pengal.Siren.Entity.Redirect;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RedirectRepository extends CrudRepository<Redirect, Long> {
    Optional<Redirect> findByAlias(String alias);

    Boolean existsByAlias(String alias);
}
