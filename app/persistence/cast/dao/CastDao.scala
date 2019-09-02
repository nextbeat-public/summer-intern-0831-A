/*
 * This file is part of the Nextbeat services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package persistence.cast.dao

import java.time.LocalDateTime
import scala.concurrent.Future

import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import persistence.cast.model.Cast
import persistence.geo.model.Location
import persistence.profile.model.Profile

// DAO: 施設情報
//~~~~~~~~~~~~~~~~~~
class CastDAO @javax.inject.Inject()(
  val dbConfigProvider: DatabaseConfigProvider
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  // --[ リソース定義 ] --------------------------------------------------------
  lazy val slick = TableQuery[CastTable]

  // --[ データ処理定義 ] ------------------------------------------------------
  /**
   * 施設を取得
   */
  def get(id: Cast.Id): Future[Option[Cast]] =
    db.run {
      slick
        .filter(_.id === id)
        .result.headOption
    }

  def findByUserId(ids: Seq[Profile.UserId]): Future[Seq[Cast]] =
    db.run {
      slick
        .filter(_.id inSet ids)
        .result
    }

  /**
   * 施設を全件取得する
   */
  def findAll: Future[Seq[Cast]] =
    db.run {
      slick.result
    }

  // --[ テーブル定義 ] --------------------------------------------------------
  class CastTable(tag: Tag) extends Table[Cast](tag, "cast") {


    // Table's columns
    /* @1 */ def id            = column[Cast.Id]     ("id", O.PrimaryKey, O.AutoInc)
    /* @2 */ def name          = column[String]         ("name")
    /* @3 */ def picture       = column[String]         ("picture")

    // The * projection of the table
    def * = (
      id.?, name, picture
    ) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (Cast.apply _).tupled,
      (v: TableElementType) => Cast.unapply(v)
    )
  }
}
