package xiphos

import xiphos.files.FileHandle

class WebFiles : Files {
    override fun internal(path: String): FileHandle {
        return FileHandle(path)
    }
}