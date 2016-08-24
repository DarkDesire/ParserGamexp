import java.io.{BufferedWriter, Closeable, FileOutputStream, OutputStreamWriter}

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import spray.json._
import spray.json.CollectionFormats
import DefaultJsonProtocol._
import scala.collection.mutable.ArrayBuffer

import scala.io.Source._

object Application extends App with CollectionFormats{
  implicit val parsedItemFormat = jsonFormat3(ParsedItem)
  implicit val consumablesFormat = seqFormat[ParsedItem]

 /* val consumables = ArrayBuffer[ParsedItem]()
  println("Starting parsing from file")
  parsingSiteBasedOnFile(consumables, "sphere.txt")
  println("Printing all grabbed data")
  consumables.foreach(item => println(item))
  println("Starting writing data from site into file")
  writeArrayToFile(consumables,"data.txt")
  */
  // saved data into file, because don't want to parse sites everytime
  println("Reading data from file")
  var data = ArrayBuffer[ParsedItem]()
  readingFromFile(data,"data.txt")
  makingItemsFromCollection(data)

  def makingItemsFromCollection(data: ArrayBuffer[ParsedItem]) = {
    data.foreach(
      element => if (true) {
        val name = element.name
        val description = element.description
        val img_src = element.image_src
        // description parsing
        // now i'm here <<

      }
    )
  }

  def readingFromFile(data: ArrayBuffer[ParsedItem], inputFileName: String) = {
    val source = fromInputStream(getClass.getResourceAsStream(inputFileName)).getLines().mkString("")
    val data = source.parseJson.convertTo[Seq[ParsedItem]].toArray
  }

  def parsingSiteBasedOnFile(array: ArrayBuffer[ParsedItem], inputFileName:String) = {
    val browser = JsoupBrowser()
    val sourceFile = fromInputStream(getClass.getResourceAsStream(inputFileName)).getLines()
    sourceFile.foreach{
      string => if (string.contains("http")) {
        println(s"Parsing .... ${string}")
        val source = browser.get(string)
        val items = source >> elementList(".description_content")
        items.foreach{
          item =>
            val name = item >> text(".description_name")
            val image_src = item >> text(".description_pic_src")
            val description = item >> text(".description_txt")
            array += ParsedItem(name,description,image_src)
        }
        println(s"Parsing ${string} done")
      }
    }
  }
  def writeArrayToFile(array: ArrayBuffer[ParsedItem], outputFileName: String) = {
    println("Saving data to data.txt file")
    using(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName)))) {
      writer =>
        writer.write(array.toSeq.toJson.prettyPrint)
    }

    def using[T <: Closeable, R](resource: T)(block: T => R): R = {
      try {
        block(resource)
      }
      finally {
        resource.close()
      }
    }
  }

}
case class ParsedItem(name:String, description: String, image_src:String){
  override def toString: String =
    s"\tname:${name}\ndescription:${description}\nimage_src:${image_src}"
}

case class Item(name: String, features: Set[Feature],featuresSet: Set[FeatureList], img_src: String)