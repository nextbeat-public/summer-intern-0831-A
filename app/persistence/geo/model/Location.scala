/*
 * This file is part of the Nextbeat services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package persistence.geo.model

import java.time.LocalDateTime
import mvc.util.EnumStatus

// 地域情報
//~~~~~~~~~~
case class Location(
  id:           Location.Id,          // 全国地方公共団体コード
  level:        Short,                // 階層レベル
  typedef:      Location.Typedef,     // 市区町村種別
  parent:       Option[Location.Id],  // 親となる地域情報ID
  urn:          String,               // 地域情報URL
  nameRegion:   String,               // 地方区分
  nameRegionEn: String,               // 地方区分(ラテン表記)
  namePref:     String,               // 都道府県
  namePrefEn:   String,               // 都道府県(ラテン表記)
  nameCity:     Option[String],       // 市
  nameCityEn:   Option[String],       // 市(ラテン表記)
  nameWard:     Option[String],       // 区
  nameWardEn:   Option[String],       // 区(ラテン表記)
  nameCounty:   Option[String],       // 郡
  nameCountyEn: Option[String],       // 郡(ラテン表記)
  updatedAt:    LocalDateTime = LocalDateTime.now, // データ更新日
  createdAt:    LocalDateTime = LocalDateTime.now  // データ作成日
)

// コンパニオンオブジェクト
//~~~~~~~~~~~~~~~~~~~~~~~~~~
object Review {

  // --[ 管理ID ]---------------------------------------------------------------
  type Id = String

  /** 市区町村管理コード => 地域 */
  def fromLocationId(id: Review.Id): Option[Review] = {
    val prefId = "%02d000".format(lid.take(2).toInt)
    map.collectFirst({ case v if v._2.contains(prefId) => v._1 })
  }
}

