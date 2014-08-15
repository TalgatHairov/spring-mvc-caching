package org.example.data

import org.example.domain.Person

/**
 * Contract for data access operations on {@link Person}s.
 */
trait PersonRepository extends EntityRepository[Person]
