/*
 * This file is part of Nextbeat services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package controllers.facility

import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, MessagesControllerComponents}
import persistence.facility.dao.FacilityDAO
import persistence.facility.model.Facility
import persistence.geo.model.Location
import persistence.geo.dao.LocationDAO
import model.site.facility.SiteViewValueFacilityDetail
import model.component.util.ViewValuePageLayout
import mvc.action.AuthenticationAction


// 施設
//~~~~~~~~~~~~~~~~~~~~~
class FacilityDetailController @javax.inject.Inject()(
  val facilityDao: FacilityDAO,
  cc: MessagesControllerComponents
) extends AbstractController(cc) with I18nSupport {
  implicit lazy val executionContext = defaultExecutionContext

  /**
    * 施設詳細ページ
    */
   def show(castid: Long)= (Action andThen AuthenticationAction()).async { implicit request =>
    for {
      Some(facilityDetail) <- facilityDao.get(castid)
    } yield {
      val vv = SiteViewValueFacilityDetail(
        layout     = ViewValuePageLayout(id = request.uri),
        facility = facilityDetail
      )
    Ok(views.html.site.facility.detail.Main(vv))
    }
  }
}
