package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

trait Authenticate[A] extends Action[A]
object Authenticate {
  def apply[A](bodyParser: BodyParser[A])(block: String => Request[A] => Result) = new Authenticate[A] {
    def parser = bodyParser
    def apply( req: Request[A] ) = {
      implicit val request = req
      Application.loginForm.bindFromRequest.fold (
    		  hasErrors => Application.BadRequest("Bad Request"),
    		  success   => block(success._1)(req))
    }
  }
  def apply (block: String => Request[AnyContent] => Result): Action[AnyContent] = {
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
      
  def authenticate = Authenticate{ user => Action{ implicit request =>
      Redirect(routes.Application.home(user))
  }}
  
  def home(id: String) = Action { implicit request =>
    Ok(views.html.home(id))
  }
  
}