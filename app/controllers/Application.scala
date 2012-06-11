package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.main("Hello World"))
  }

  // Prompt User with Login Form
  def login = Action {
  	Ok(views.html.login())
  }

  val loginForm = Form(tuple(
      "username" -> text,
      "password" -> text))
      
  def authenticate = Action{ implicit request =>
    loginForm.bindFromRequest.fold (
      hasErrors => BadRequest("Bad Request"),
      success   => Ok("Success")
    )
  }
  
}