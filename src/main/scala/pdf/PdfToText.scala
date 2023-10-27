package pdf

import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdfparser.PDFParser
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import java.io.{File, FileInputStream}

/** reference : https://alvinalexander.com/scala/convert-pdf-to-plain-text-application-scala-shell-script/
  */
object PdfToText extends App {
  System.setProperty("java.awt.headless", "true")

  if (args.length != 3) printUsageAndExit

  val startPage = args(0).toInt
  val endPage = args(1).toInt
  val filename = args(2)

  // sanity check
  if (startPage > endPage) printUsageAndExit

  println(getTextFromPdf(startPage, endPage, filename))

  def printUsageAndExit: Unit = {
    println("")
    println("Usage: pdftotext startPage endPage filename")
    println("       (endPage must be >= startPage)")
    System.exit(1)
  }

  def getTextFromPdf(startPage: Int, endPage: Int, filename: String): Option[String] = {
    var pdf: PDDocument = null
    try {
      val file = new File(filename)
      pdf = Loader.loadPDF(file)

//      pdf.getDocument

      val stripper = new PDFTextStripper
      stripper.setStartPage(startPage)
      stripper.setEndPage(endPage)
      Some(stripper.getText(pdf))
    } catch {
      case t: Throwable =>
        t.printStackTrace
        None
    } finally {
//      pdf.close()
    }
  }
}
