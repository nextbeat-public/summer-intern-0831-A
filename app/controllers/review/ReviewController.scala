package controllers.review

import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, MessagesControllerComponents}
import persistence.review.dao.ReviewDAO
import model.site.review.SiteViewValueReviewList
import model.component.util.ViewValuePageLayout
import mvc.action.AuthenticationAction

class ReviewController @javax.inject.Inject()(
  val reviewDao: ReviewDAO,
  cc: MessagesControllerComponents
) extends AbstractController(cc) with I18nSupport {
  implicit lazy val executionContext = defaultExecutionContext

  /**
    * 施設一覧ページ
    */
  def list(castId: Long) = (Action andThen AuthenticationAction()).async { implicit request =>
    for {
      reviewSeq <- reviewDao.filterByCastId(castId)
    } yield {
      val vv = SiteViewValueReviewList(
        layout     = ViewValuePageLayout(id = request.uri),
        reviews = reviewSeq
      )
      Ok(views.html.site.review.list.Main(vv))
    }
  }
}