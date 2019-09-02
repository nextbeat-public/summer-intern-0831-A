/*
 * This file is part of the MARIAGE services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package model.site.cast

import model.component.util.ViewValuePageLayout
import persistence.geo.model.Location
import persistence.profile.model.Profile
import persistence.store.model.Store
import persistence.cast.model.Cast
import persistence.review.model.Review

// 表示: 施設一覧
//~~~~~~~~~~~~~~~~~~~~~
case class SiteViewValueProfileDetail(
  layout:   ViewValuePageLayout,
  profile: Profile
)

case class SiteViewValueProfileList(
  layout:   ViewValuePageLayout,
  location: Seq[Location],
  store: Seq[Store],
  cast:  Seq[Cast],
  pairs: Seq[SiteViewValueProfileList.Pair]
)

object SiteViewValueProfileList {

  case class Pair(
    store: Store,
    cast: Cast,
    avereview: (Double, Double, Double)
  )

  def from(
    layout:   ViewValuePageLayout,
    location: Seq[Location],
    store:  Seq[Store],
    cast:   Seq[Cast],
    profile: Seq[Profile],
    review:  Seq[Review]
  ) = {

 
    val pairs = 
      for {
        c <- cast
        p <- profile.find(v => c.id.contains(v.user_id))
        s <- store.find(_.id.contains(p.store_id))
        r =  review.filter(v => c.id.contains(v.castId))
      } yield {
        val starAve = r.map(_.star).sum / r.length
        val funAve = r.map(_.fun).sum /r.length
        val hospitalityAve = r.map(_.hospitality).sum / r.length

        val avereview = (starAve, funAve, hospitalityAve)

        Pair(s, c, avereview)
      }
    new SiteViewValueProfileList(
      layout, location, store, cast, pairs
    )
  }
}
