/*
 * This file is part of the Nextbeat services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package persistence.review.dao

import java.time.LocalDateTime
import scala.concurrent.Future

import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import persistence.review.model.Review

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

  /**
   * 地域から施設を取得
   * 検索業件: ロケーションID
   */

  // --[ テーブル定義 ] --------------------------------------------------------
  class ReviewTable(tag: Tag) extends Table[Review](tag, "review") {


    // Table's columns
    /* @1 */ def id            = column[Review.Id]    ("id", O.PrimaryKey, O.AutoInc)
    /* @2 */ def castId        = column[Long]    ("cast_id")
    /* @3 */ def userId        = column[Long]         ("user_id")
    /* @6 */ def updatedAt     = column[LocalDateTime]  ("updated_at")
    /* @7 */ def createdAt     = column[LocalDateTime]  ("created_at")

    // The * projection of the table
    def * = (
      id.?, castId, userId, updatedAt, createdAt
    ) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (Review.apply _).tupled,
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType) => Review.unapply(v).map(_.copy(
        _4 = LocalDateTime.now
      ))
    )
  }
}
