/*
 * This file is part of the Nextbeat services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package persistence.store.model

import play.api.data._
import play.api.data.Forms._
import java.time.LocalDateTime
import persistence.geo.model.Location

// 施設情報 (sample)
//~~~~~~~~~~~~~
case class Store(
  id:          Option[Store.Id],      // 施設ID
  name:        String,               // 名前
  address:     String,               // 住所
  phione_number:  Int,               // 電話
  location_id:     Location.Id,      // 県ID
)

case class StoreSearch(
  locationIdOpt: Option[Location.Id]
)

// コンパニオンオブジェクト
//~~~~~~~~~~~~~~~~~~~~~~~~~~
object Store {

  // --[ 管理ID ]---------------------------------------------------------------
  type Id = Long

  // --[ フォーム定義 ]---------------------------------------------------------
  val formForStoreSearch = Form(
    mapping(
      "locationId" -> optional(text),
    )(StoreSearch.apply)(StoreSearch.unapply)
  )
}
