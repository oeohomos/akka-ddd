package pl.newicom.dddd.view.sql

import pl.newicom.dddd.view.ViewUpdateService
import slick.driver.JdbcProfile

import scala.concurrent.Future

abstract class SqlViewUpdateService(implicit val profile: JdbcProfile) extends ViewUpdateService with FutureHelpers {
  this: SqlViewStoreConfiguration =>

  type VUConfig = SqlViewUpdateConfig

  override def ensureViewStoreAvailable: Future[Unit] = {
    viewStore.run(profile.defaultTables).mapToUnit
  }

  override def viewHandler(vuConfig: VUConfig) = new SqlViewHandler(config, vuConfig)

}
