package ch.xlan.android17.data

import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor

fun readClipboardText(): String {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    require(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) { "le presse-papiers ne contient pas de texte" }
    return clipboard.getData(DataFlavor.stringFlavor) as String
}
