/*
 * This file is part of the Nextbeat services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package persistence.cast.model

import play.api.data._
import play.api.data.Forms._
import java.time.LocalDateTime
import persistence.geo.model.Location

// 施設情報 (sample)
//~~~~~~~~~~~~~
case class Cast(
  id:          Option[Cast.Id],      // 施設ID
  name:        String,               // 名前
  picture:     String,               // 写真
)

// コンパニオンオブジェクト
//~~~~~~~~~~~~~~~~~~~~~~~~~~
object Cast {

  // --[ 管理ID ]---------------------------------------------------------------
  type Id = Long

  // --[ フォーム定義 ]---------------------------------------------------------
  // val formForFacilityCast = Form(
  //   mapping(
  //     "locationId" -> optional(text),
  //   )(CastSearch.apply)(CastSearch.unapply)
  // )
}
