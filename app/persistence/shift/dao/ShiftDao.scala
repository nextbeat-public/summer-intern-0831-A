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
    /* @1 */ def id            = column[Shift.Id]       ("id", O.PrimaryKey, O.AutoInc)
    /* @2 */ def day_of_week   = column[String]         ("day_of_week")
             def start_time    = column[LocalDateTime]  ("start_time")
             def end_time      = column[LocalDateTime]  ("end_time")

    // The * projection of the table
    def * = (
      id.?, day_of_week, start_time, end_time
    ) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (Shift.apply _).tupled,
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType) => Shift.unapply(v)
      )
  }
}