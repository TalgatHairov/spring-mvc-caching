package org.example.web

import java.lang.Long

import org.example.domain.Person
import org.example.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{PathVariable, RequestMapping, RequestMethod}

/**
 * Person pages controller.
 */
@Controller
class PersonController {
  @Autowired
  protected[this] var service: PersonService = _

  /**
   * Displays the person add page.
   */
  @RequestMapping(method = Array(RequestMethod.GET), value = Array("person"))
  def add(model: Model): String = {
    if (!model.containsAttribute("person")) model.addAttribute("person", new Person)

    "show"
  }

  /**
   * Displays the person listing page.
   */
  @RequestMapping(method = Array(RequestMethod.GET), value = Array("/"))
  def list(model: Model): String = {
    model.addAttribute("persons", this.service.all)

    "list"
  }

  /**
   * Saves a person and displays an updated home page.
   */
  @RequestMapping(method = Array(RequestMethod.POST), value = Array("person", "person/*"))
  def save(person: Person, model: Model): String = {
    this.service.save(person)

    "redirect:/"
  }

  /**
   * Displays the person details page.
   */
  @RequestMapping(method = Array(RequestMethod.GET), value = Array("person/{id}"))
  def show(@PathVariable id: Long, model: Model): String = {
    model.addAttribute("person", this.service.get(id))

    this.add(model)
  }
}
