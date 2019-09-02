package controllers.review

import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, MessagesControllerComponents}
import persistence.review.dao.ReviewDAO
import model.site.review.SiteViewValueReview
import model.component.util.ViewValuePageLayout
import mvc.action.AuthenticationAction
import concurrent.Future

class ReviewCommitController @javax.inject.Inject()(
    val daoReviewCommit: ReviewDAO,
    cc: MessagesControllerComponents
    ) extends AbstractController(cc) with I18nSupport {
        implicit lazy val executionContext = defaultExecutionContext

    def viewForReview = Action.async {implict request =>
        for
    }

    def post = (Action andThen AuthenticationAction()).async { implicit request =>
        SiteViewValueReview.formReview.bindFromRequest.fold(
            errors => {
                val vv = SiteViewValueReview(
                    layout  = ViewValuePageLayout(id = request.uri),
                    form    = errors
                )
                Future.successful(Ok(views.html.site.review.post.Main(vv)))
            },
            form => {
                for{
                    id <- daoReviewCommit.post(form.toReview)
                } yield {
                Redirect("/cast/list")
                }
            }
        )
    }
}
