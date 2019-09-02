package persistence.shift.model

import play.api.data._
import play.api.data.Forms._
import java.time.LocalDateTime

// 施設情報 (sample)
//~~~~~~~~~~~~~
case class Shift(
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
