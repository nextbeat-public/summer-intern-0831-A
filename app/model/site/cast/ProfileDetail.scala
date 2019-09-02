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
  cast:  Seq[Cast]
)
