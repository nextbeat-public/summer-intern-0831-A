/*
 * This file is part of the Nextbeat services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package persistence.review.model

import play.api.data._
import play.api.data.Forms._
import java.time.LocalDateTime

// 施設情報 (sample)
//~~~~~~~~~~~~~
case class Review(
  id:          Option[Review.Id],                // 施設ID
  castId:      Long,
  userId:      Long,
  title:       String,
  body:        String,
  star:        Double,
  fun:         Double,
  hospitality: Double,
  createdAt:   LocalDateTime = LocalDateTime.now   // データ作成日
)

// コンパニオンオブジェクト
//~~~~~~~~~~~~~~~~~~~~~~~~~~
object Review {
  type Id = Long
}

