package persistence.shift.dao

import java.time.LocalDateTime
import scala.concurrent.Future

import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import persistence.shift.model.Shift

// DAO: 施設情報
//~~~~~~~~~~~~~~~~~~
class ShiftDAO @javax.inject.Inject()(
  val dbConfigProvider: DatabaseConfigProvider
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  // --[ リソース定義 ] --------------------------------------------------------
  lazy val slick = TableQuery[ShiftTable]

  def filterByCastId(castId: Long) : Future[Shift] =
    db.run {
      slick
      .filter(_.castId === castId)
        .result
    }
  // --[ テーブル定義 ] --------------------------------------------------------
  class ShiftTable(tag: Tag) extends Table[Shift](tag, "shift") {
    // Table's columns
    /* @1 */ def id           = column[Shift.Id]       ("id", O.PrimaryKey, O.AutoInc)
    /* @2 */ def dayOfWeek    = column[String]         ("day_of_week")
             def startTime    = column[LocalDateTime]  ("start_time")
             def endTime      = column[LocalDateTime]  ("end_time")
             def castId       = column[Long]           ("cast_id")

    // The * projection of the table
    def * = (
      id.?, dayOfWeek, startTime, endTime, castId
    ) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (Shift.apply _).tupled,
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType) => Shift.unapply(v)
      )
  }
}