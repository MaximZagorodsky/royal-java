package com.transport.repository;

import com.transport.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Proxima on 26.07.2016.
 */
@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
}
