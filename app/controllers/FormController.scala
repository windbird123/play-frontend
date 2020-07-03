package controllers

import javax.inject.{ Inject, Singleton }
import play.api.data.Forms._
import play.api.data._
import play.api.data.validation._
import play.api.mvc._

case class Todo(name: String, priority: Int, complete: Boolean)

@Singleton
class FormController @Inject() (cc: MessagesControllerComponents)(implicit assetsFinder: AssetsFinder)
  extends MessagesAbstractController(cc) {

  val todoForm: Form[Todo] = Form(
    mapping(
      "name" -> text,
      "priority" -> number.verifying(Constraints.min(1), Constraints.max(3)),
      "complete" -> boolean)(Todo.apply)(Todo.unapply))

  def createForm(): Action[AnyContent] = Action { implicit request =>
    val populatedForm = todoForm.fill(Todo("windbird", 1, complete = true))
    Ok(views.html.todoForm(populatedForm))
    //    Ok(views.html.todoForm(todoForm))
  }

  def submitForm(): Action[AnyContent] = Action { implicit request =>
    todoForm
      .bindFromRequest()
      .fold(
        (error: Form[Todo]) => BadRequest("BAD"),
        (form: Todo) => Ok(form.name))
  }
}
