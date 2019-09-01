/*
 * This file is part of the Nextbeat services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package persistence.profile.dao

import java.time.LocalDateTime
import scala.concurrent.Future

import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import persistence.profile.model.Profile
import persistence.cast.model.Cast
import persistence.geo.model.Location

// DAO: 施設情報
//~~~~~~~~~~~~~~~~~~
class ProfileDAO @javax.inject.Inject()(
  val dbConfigProvider: DatabaseConfigProvider
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  // --[ リソース定義 ] --------------------------------------------------------
  lazy val slick = TableQuery[ProfileTable]

  // --[ データ処理定義 ] ------------------------------------------------------
  /**
   * 施設を取得
   */
  def get(id: Profile.Id): Future[Option[Profile]] =
    db.run {
      slick
        .filter(_.id === id)
        .result.headOption
    }
  
  // def get_store_id(id: String): Future[Option[Profile]] =
  //   db.run {
  //     slick
  //       .filter(_.user_id === id)
  //       .result.headOption
  //   }

  /**
   * 施設を全件取得する
   */
  def findAll: Future[Seq[Profile]] =
    db.run {
      slick.result
    }

  // --[ テーブル定義 ] --------------------------------------------------------
  class ProfileTable(tag: Tag) extends Table[Profile](tag, "profile") {


    // Table's columns
    /* @1 */ def id            = column[Profile.Id]     ("id", O.PrimaryKey, O.AutoInc)
    /* @2 */ def comment       = column[String]         ("comment")
    /* @3 */ def description   = column[String]         ("description")
    /* @4 */ def user_id       = column[String]         ("user_id")
    /* @5 */ def store_id      = column[String]         ("store_id")

    // The * projection of the table
    def * = (
      id.?, comment, description, user_id, store_id
    ) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (Profile.apply _).tupled,
      (v: TableElementType) => Profile.unapply(v)
    )
  }
}
