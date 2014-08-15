package org.example.domain

import java.io.Serializable
import java.lang.Long
import javax.persistence._

import scala.beans.BeanProperty

/**
 * Represents a domain entity.
 */
@MappedSuperclass
abstract class Entity extends Serializable {
  /**
   * The primary key for the instance.
   */
  @BeanProperty
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  var id: Long = _

  /**
   * Gets whether this is a newly created instance, that is, it has not been assigned an identifier yet.
   *
   * @return { @code true} if this is a new instance, { @code false} otherwise.
   */
  def isNew = this.id == null || this.id == 0

  /**
   * Gets whether this instance is the same as another object.
   *
   * @param o The object to compare with.
   * @return { @code true} if this instance is the same as another object, { @code false} otherwise.
   */
  override def equals(o: Any): Boolean = {
    if (o == null) false
    if (this == o) true
    if (!this.getClass.equals(o.getClass)) false

    val that = o.asInstanceOf[Entity]

    if ((this.id == null) && (that.id == null)) true

    (this.id != null) && (that.id != null) && this.id.equals(that.id)
  }

  /**
   * Gets the hash code for this instance.
   *
   * @return The hash code for this instance.
   */
  override def hashCode = if (this.id != null) this.id.hashCode else super.hashCode
}
