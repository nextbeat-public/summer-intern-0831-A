/*
 * This file is part of the Nextbeat services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package persistence.profile.model

import play.api.data._
import play.api.data.Forms._
import java.time.LocalDateTime
import persistence.geo.model.Location

// 施設情報 (sample)
//~~~~~~~~~~~~~
case class Profile(
  id:          Option[Profile.Id],      // 施設ID
  comment:     String,                  // コメント
  description: String,                  // 詳細
  user_id:     Profile.UserId,                  // ユーザーID
  store_id:    Profile.StoreId                  // 店ID
)

// コンパニオンオブジェクト
//~~~~~~~~~~~~~~~~~~~~~~~~~~
object Profile {

  // --[ 管理ID ]---------------------------------------------------------------
  type Id = Long

  type UserId = Long

  type StoreId = Long

}
