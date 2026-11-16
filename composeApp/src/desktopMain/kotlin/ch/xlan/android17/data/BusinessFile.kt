package ch.xlan.android17.data

import ch.xlan.android17.model.Business
import ch.xlan.android17.model.toJson
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.moveTo
import kotlin.io.path.writeText

val businessFile: Path = run {
    val dataHome = System.getenv("XDG_DATA_HOME")?.takeIf { it.isNotBlank() }
        ?: "${System.getProperty("user.home")}/.local/share"
    Path(dataHome, "android17", "business.json")
}

fun saveBusiness(business: Business, file: Path = businessFile) {
    file.parent.createDirectories()
    val tmp = file.resolveSibling("${file.fileName}.tmp")
    tmp.writeText(business.toJson())
    tmp.moveTo(file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE)
}
