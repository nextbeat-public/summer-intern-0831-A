/*
 * This file is part of Nextbeat services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package controllers.cast

import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, MessagesControllerComponents}
import persistence.cast.dao.CastDAO
import persistence.store.model.Store.formForStoreSearch
import persistence.geo.model.Location
import persistence.geo.dao.LocationDAO
import model.site.cast.SiteViewValueProfileDetail
import model.site.cast.SiteViewValueProfileList
import model.component.util.ViewValuePageLayout
import mvc.action.AuthenticationAction
import persistence.profile.dao.ProfileDAO
import persistence.profile.model.Profile
import model.site.cast.SiteViewValueProfileDetail
import persistence.store.dao.StoreDAO


// 施設
//~~~~~~~~~~~~~~~~~~~~~
class CastController @javax.inject.Inject()(
  val castDao: CastDAO,
  val daoLocation: LocationDAO,
  val profileDao: ProfileDAO,
  val storeDao: StoreDAO,
  cc: MessagesControllerComponents
) extends AbstractController(cc) with I18nSupport {
  implicit lazy val executionContext = defaultExecutionContext

  /**
    * 施設一覧ページ
    */
  def list = (Action andThen AuthenticationAction()).async { implicit request =>
    for {
      locSeq      <- daoLocation.filterByIds(Location.Region.IS_PREF_ALL)
      storeSeq     <- storeDao.findAll
      castSeq     <- castDao.findAll

    } yield {
      val vv = SiteViewValueProfileList(
        layout     = ViewValuePageLayout(id = request.uri),
        location   = locSeq,
        store       = storeSeq,
        cast        = castSeq
      )
      Ok(views.html.site.cast.list.Main(vv, formForStoreSearch))
    }
  }

  /**
   * 施設検索
   */
  def search = Action.async { implicit request =>
    formForStoreSearch.bindFromRequest.fold(
      errors => {
       for {
          locSeq      <- daoLocation.filterByIds(Location.Region.IS_PREF_ALL)
          storeSeq     <- storeDao.findAll
          castSeq        <- castDao.findAll
        } yield {
          val vv = SiteViewValueProfileList(
            layout     = ViewValuePageLayout(id = request.uri),
            location   = locSeq,
            store       = storeSeq,
            cast       = castSeq
          )
          BadRequest(views.html.site.cast.list.Main(vv, errors))
        }
      },
      form   => {
        for {
          locSeq      <- daoLocation.filterByIds(Location.Region.IS_PREF_ALL)
          storeSeq     <- form.locationIdOpt match {
            case Some(id) =>
              for {
                locations   <- daoLocation.filterByPrefId(id)
                storeSeq     <- storeDao.filterByLocationIds(locations.map(_.id))
              } yield storeSeq
            case None     => storeDao.findAll
          }
          profileSeq     <- profileDao.findByStoreId(storeSeq.flatMap(_.id))
          castSeq      <- castDao.findByUserId(profileSeq.map(_.user_id))
        } yield {
          val vv = SiteViewValueProfileList(
           layout     = ViewValuePageLayout(id = request.uri),
            location   = locSeq,
            store       = storeSeq,
            cast       = castSeq
          )
          Ok(views.html.site.cast.list.Main(vv, formForStoreSearch.fill(form)))
        }
      }
    )
  }
}
