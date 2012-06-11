package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.main("Hello World"))
  }

  // Prompt User with Login Form
  def login = Action {
  	Ok(views.html.login())
  }
  
}