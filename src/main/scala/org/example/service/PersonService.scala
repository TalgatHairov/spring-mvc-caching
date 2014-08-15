package org.example.service

import java.lang.Long

import org.example.data.PersonRepository
import org.example.domain.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.{CacheEvict, Cacheable, Caching}
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.{Propagation, Transactional}

/**
 * Business logic operations on {@link Person}s.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
class PersonService {
  @Autowired
  protected[this] var repository: PersonRepository = _

  /**
   * Gets all the persons available in the system.
   *
   * @return A { @link List} of { @link Person}s.
   */
  @Cacheable(value = Array("persons"))
  def all = this.repository.findAll(new Sort("name"))

  /**
   * Gets the person with a specified identifier.
   *
   * @param id The person identifier to load.
   * @return A { @link Person}.
   */
  @Cacheable(key = "#id", value = Array("person"))
  def get(id: Long) = this.repository.findOne(id)

  /**
   * Saves a person.
   *
   * @param person The { @link Person} to save.
   */
  @Caching(evict = Array(new CacheEvict(allEntries = true, value = Array("persons")), new CacheEvict(key = "#person.id", value = Array("person"))))
  def save(person: Person): Unit = {
    if (person.isNew) {
      this.repository.save(person)
    }
    else {
      val saved = this.repository.findOne(person.id)
      saved.email = person.email
      saved.gender = person.gender
      saved.name = person.name

      this.repository.save(saved)
    }
  }
}
