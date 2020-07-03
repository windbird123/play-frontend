package controllers

import javax.inject.{ Inject, Singleton }
import play.api.mvc._
@Singleton
class KnitController @Inject() (cc: MessagesControllerComponents)(implicit assetsFinder: AssetsFinder)
  extends MessagesAbstractController(cc) {

  def index(): Action[AnyContent] = profile("Active")

  def element(): Action[AnyContent] = Action(implicit request => Ok(views.html.knit.element()))

  def profile(section: String): Action[AnyContent] = Action(implicit request => Ok(views.html.knit.profile(section)))

  def series(): Action[AnyContent] = Action(implicit request => Ok(views.html.knit.series()))
}
