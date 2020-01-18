import mill._
import mill.modules._
import mill.scalalib._

val scalaVersions = Seq("2.12.10", "2.13.1")
object pickle extends Cross[PickleModule](scalaVersions:_*)
class PickleModule(override val crossScalaVersion: String) extends CrossModuleBase

val playJsonVersions = Seq("2.7.4", "2.8.1")
object playJson extends Cross[PlayJsonModule]((scalaVersions zip playJsonVersions):_*)
class PlayJsonModule(override val crossScalaVersion: String, playJsonVersion: String) extends CrossModuleBase{
  override def moduleDeps = Seq(pickle(crossScalaVersion))
  override def sources = T.sources(super.millSourcePath / os.up / "src")
}
