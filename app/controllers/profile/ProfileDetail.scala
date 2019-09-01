/*
 * This file is part of Nextbeat services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package controllers.profile

import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, MessagesControllerComponents}
import model.component.util.ViewValuePageLayout
import mvc.action.AuthenticationAction
import persistence.profile.dao.ProfileDAO
import persistence.profile.model.Profile
import model.site.cast.SiteViewValueProfileDetail

// 施設
//~~~~~~~~~~~~~~~~~~~~~
class ProfileDetailController @javax.inject.Inject()(
  val profileDao: ProfileDAO,
  cc: MessagesControllerComponents
) extends AbstractController(cc) with I18nSupport {
  implicit lazy val executionContext = defaultExecutionContext

  /**
    * 施設詳細ページ
    */
   def show(castid: Long)= (Action andThen AuthenticationAction()).async { implicit request =>
    for {
      Some(profileDetail) <- profileDao.get(castid)
    } yield {
      val vv = SiteViewValueProfileDetail(
        layout     = ViewValuePageLayout(id = request.uri),
        profile    = profileDetail
      )
    Ok(views.html.site.cast.detail.Main(vv))
    }
  }
}
