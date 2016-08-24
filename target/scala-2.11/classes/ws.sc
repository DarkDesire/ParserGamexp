import scala.io.Source
import scala.io.Source._
import Feature._

val force1 = getFeatureByKeyword("сила")
force1.value = "sadasd"
force1
val force2 = getFeatureByKeyword("сила")
force1.value = "3123123"
force1

val set = Set[Feature]()

set + force1
set + force2
set