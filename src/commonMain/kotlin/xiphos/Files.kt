package xiphos

import xiphos.files.FileHandle

interface Files {

    fun internal(path: String): FileHandle

}