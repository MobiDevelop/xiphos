package xiphos

import xiphos.audio.Music
import xiphos.audio.Sound
import xiphos.files.FileHandle

interface Audio {

    fun newMusic(file: FileHandle): Music
    fun newSound(file: FileHandle): Sound

}