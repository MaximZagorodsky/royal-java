package com.transport.repository;

import com.transport.model.Helper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Proxima on 11.08.2016.
 */
@Repository
public interface HelperRepository extends JpaRepository<Helper, Integer> {

    List<Helper> findByEnabledTrue();

}
