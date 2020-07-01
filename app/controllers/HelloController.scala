package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

@Singleton
class HelloController @Inject() (cc: ControllerComponents)(implicit assetsFinder: AssetsFinder) extends AbstractController(cc) {

  def hello: Action[AnyContent] = Action {
    Ok(views.html.hello("windbird"))
  }
}
