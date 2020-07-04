package controllers

import javax.inject.{ Inject, Singleton }
import play.api.mvc.{ Action, AnyContent, MessagesAbstractController, MessagesControllerComponents }

// Controller
@Singleton
class AdminController @Inject() (cc: MessagesControllerComponents)(implicit assetsFinder: AssetsFinder)
    extends MessagesAbstractController(cc) {

  def index(): Action[AnyContent] = Action {
    Redirect(routes.AdminController.home("Active"))
  }

  def home(section: String): Action[AnyContent] = Action(implicit request => Ok(views.html.admin.home(section)))

  def dashboard(): Action[AnyContent]           = Action(implicit request => Ok(views.html.admin.dashboard()))
}
