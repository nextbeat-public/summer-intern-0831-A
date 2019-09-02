package model.site.review

import com.github.t3hnar.bcrypt._
import model.component.util.ViewValuePageLayout
import model.site.review.SiteViewValueReview.ReviewForm
import persistence.review.model.Review
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import java.time.LocalDateTime

case class SiteViewValueReview(
    layout: ViewValuePageLayout,
    form:   Form[ReviewForm],
    castId: Long,
    userId: Long,
)

object SiteViewValueReview {

    case class ReviewForm(
        title:          String,
        body:           String,
        star:           Double,
        fun:            Double,
        hospitality:    Double,
    ){
        def toReview(castId: Long, userId: Long) =
            Review(None, castId, userId, title, body, star, fun, hospitality, LocalDateTime.now())
    }

    val formReview = Form(
        mapping(
            "title"         -> text,
            "body"          -> text,
            "star"          -> of(doubleFormat),
            "fun"           -> of(doubleFormat),
            "hospitality"   -> of(doubleFormat),
        )(ReviewForm.apply)(ReviewForm.unapply)
    )
}