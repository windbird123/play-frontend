package controllers

import javax.inject.{Inject, Singleton}
import play.api.data.Forms._
import play.api.data._
import play.api.data.validation._
import play.api.mvc._


@Singleton
class KnitController @Inject() (cc: MessagesControllerComponents)(implicit assetsFinder: AssetsFinder)
    extends MessagesAbstractController(cc) {

  def default() = show("")

  def show(menu: String): Action[AnyContent] = Action { implicit request =>
    Ok(views.html.knit.main(menu))
  }

  def profile(section: String): Action[AnyContent] = Action { implicit request =>
    Ok(views.html.knit.main(section))
  }
}
