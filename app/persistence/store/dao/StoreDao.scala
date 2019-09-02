/*
 * This file is part of the Nextbeat services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package persistence.store.dao

import java.time.LocalDateTime
import scala.concurrent.Future

import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import persistence.store.model.Store
import persistence.profile.model.Profile
import persistence.geo.model.Location

// DAO: 施設情報
//~~~~~~~~~~~~~~~~~~
class StoreDAO @javax.inject.Inject()(
  val dbConfigProvider: DatabaseConfigProvider
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  // --[ リソース定義 ] --------------------------------------------------------
  lazy val slick = TableQuery[StoreTable]

  // --[ データ処理定義 ] ------------------------------------------------------
  /**
   * 施設を取得
   */
  def get(id: Store.Id): Future[Option[Store]] =
    db.run {
      slick
        .filter(_.id === id)
        .result.headOption
    }

  // def getaddress(id: Profile.store_id): Future[Option[Store]] =
  //   db.run {
  //     slick
  //       .filter(_.id === id)
  //       .result.headOption
  //   }

  /**
   * 施設を全件取得する
   */
  def findAll: Future[Seq[Store]] =
    db.run {
      slick.result
    }

  def filterByLocationIds(locationIds: Seq[Location.Id]): Future[Seq[Store]] =
    db.run {
      slick
        .filter(_.locationId inSet locationIds)
        .result
    }

  // --[ テーブル定義 ] --------------------------------------------------------
  class StoreTable(tag: Tag) extends Table[Store](tag, "store") {


    // Table's columns
    /* @1 */ def id            = column[Store.Id]     ("id", O.PrimaryKey, O.AutoInc)
    /* @2 */ def name          = column[String]       ("name")
    /* @3 */ def address       = column[String]       ("address")
    /* @4 */ def phoneNumber = column[Int]          ("phione_number")
    /* @5 */ def locationId  = column[String]       ("location_id")


    // The * projection of the table
    def * = (
      id.?, name, address, phoneNumber, locationId
    ) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (Store.apply _).tupled,
      (v: TableElementType) => Store.unapply(v)
    )
  }
}
