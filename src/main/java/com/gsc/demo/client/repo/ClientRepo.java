package com.gsc.demo.client.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsc.demo.domain.Client;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {

}
