package persistence.shift.model

import play.api.data._
import play.api.data.Forms._
import java.time.LocalDateTime

// 施設情報 (sample)
//~~~~~~~~~~~~~
case class Shift(
  id:           Option[Shift.Id],                // 施設ID
  dayOfWeek:   String,
  startTime:   LocalDateTime,
  endTime:     LocalDateTime,
  castId:      Long,
)

// コンパニオンオブジェクト
//~~~~~~~~~~~~~~~~~~~~~~~~~~
object Shift {
  type Id = Long
}
