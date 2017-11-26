package com.example.game.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import com.example.game.model.Sponsor;

public interface SponsorRepository extends JpaRepository<Sponsor,Long> {

}
