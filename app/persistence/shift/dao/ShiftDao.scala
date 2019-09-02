package persistence.shift.dao

import java.time.LocalDateTime
import scala.concurrent.Future

import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import persistence.shift.model.Shift

// DAO: 施設情報
//~~~~~~~~~~~~~~~~~~
class ReviewDAO @javax.inject.Inject()(
  val dbConfigProvider: DatabaseConfigProvider
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  // --[ リソース定義 ] --------------------------------------------------------
  lazy val slick = TableQuery[ReviewTable]

  def filterByCastId(castId: Long) : Future[Seq[Review]] =
    db.run {
      slick
      .filter(_.castId === castId)
        .result
    }
  
  def post(data: Review): Future[Review.Id] =
    db.run {
      data.id match {
        case None    => slick returning slick.map(_.id) += data
        case Some(_) => DBIO.failed(
          new IllegalArgumentException("The given object is already assigned id.")
        )
      }
    }

  // --[ テーブル定義 ] --------------------------------------------------------
  class ReviewTable(tag: Tag) extends Table[Review](tag, "review") {
    // Table's columns
    /* @1 */ def id            = column[Review.Id]      ("id", O.PrimaryKey, O.AutoInc)
    /* @2 */ def castId        = column[Long]           ("cast_id")
    /* @3 */ def userId        = column[Long]           ("user_id")
    /* @6 */ def title         = column[String]           ("title")
             def body          = column[String]         ("body")
             def star          = column[Double]         ("star")
             def fun           = column[Double]         ("fun")
             def hospitality   = column[Double]         ("hospitality")
             def createdAt     = column[LocalDateTime]  ("created_at")

    // The * projection of the table
    def * = (
      id.?, castId, userId, title, body, star, fun, hospitality, createdAt
    ) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (Review.apply _).tupled,
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType) => Review.unapply(v)
      )
  }
}