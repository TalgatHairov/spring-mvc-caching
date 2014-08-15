package org.example.domain

import javax.persistence.{Column, Table}
import javax.validation.constraints.NotNull

import scala.beans.BeanProperty

/**
 * Represents a person.
 */
@javax.persistence.Entity
@Table(name = "person")
class Person extends Entity {
  /**
   * The person's gender.
   */
  @BeanProperty
  @Column(name = "gender")
  @NotNull
  var gender: String = _

  /**
   * The person's email address.
   */
  @BeanProperty
  @Column(name = "email")
  @NotNull
  var email: String = _

  /**
   * The person's name.
   */
  @BeanProperty
  @Column(name = "name")
  @NotNull
  var name: String = _
}
