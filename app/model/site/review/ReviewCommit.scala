package model.site.review

import com.github.t3hnar.bcrypt._
import model.component.util.ViewValuePageLayout
import model.site.app.SiteViewValueReview.ReviewForm
import persistence.review.model.Review
import play.api.data.Form
import play.api.data.Forms._

case class SiteViewValueReview(
    layout: ViewValuePageLayout,
    form:   Form[ReviewForm]
)

object SiteViewValueReview {

    case class ReviewForm(
        title:          Char,
        body:           String,
        star:           Double,
        fun:            Double,
        hospitality:    Double,
    ){
        def toReview =
            Review(None, None, None, title, body, star, fun, hospitality, None)
    }

    val formReview = Form(
        mapping(
            "title"         -> nonEmptyText,
            "body"          -> nonEmptyText,
            "star"          -> number(min=0.5, max=5),
            "fun"           -> number(min=0.5, max=5),
            "hospitality"   -> number(min=0.5, max=5),
        )(ReviewForm.apply)(ReviewForm.unapply)
    )
}