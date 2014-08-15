package org.example.data

import java.lang.Long

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

import org.example.domain.Entity

/**
 * Contract for data access operations on a domain entity.
 */
@NoRepositoryBean
trait EntityRepository[E <: Entity] extends JpaRepository[E, Long]
