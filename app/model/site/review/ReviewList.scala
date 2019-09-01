package model.site.review

import model.component.util.ViewValuePageLayout
import persistence.review.model.Review

// 表示: 施設一覧
//~~~~~~~~~~~~~~~~~~~~~
case class SiteViewValueReviewList(
  layout:   ViewValuePageLayout,
  reviews: Seq[Review]
)