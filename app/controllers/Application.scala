package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

trait Authenticate[A] extends Action[A]
object Authenticate {
  def apply[A](bodyParser: BodyParser[A])(block: Request[A] => Result) = new Authenticate[A] {
    def parser = bodyParser
    def apply( req: Request[A] ) = {
      block(req)
    }
  }
  def apply (block: Request[AnyContent] => Result): Action[AnyContent] = {
    Authenticate(BodyParsers.parse.anyContent)(block)
  }
}
 

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
      
  def authenticate = Authenticate{ Action{ implicit request =>
    loginForm.bindFromRequest.fold (
      hasErrors => BadRequest("Bad Request"),
      success   => Redirect(routes.Application.home(success._1))
    )
  }}
  
  def home(id: String) = Action { implicit request =>
    Ok(views.html.home(id))
  }
  
}