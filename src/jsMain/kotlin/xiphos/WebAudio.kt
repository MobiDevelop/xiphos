package xiphos

import xiphos.audio.Music
import xiphos.audio.Sound
import xiphos.audio.WebMusic
import xiphos.audio.WebSound
import xiphos.files.FileHandle

class WebAudio : Audio {
    override fun newSound(file: FileHandle): Sound {
        return WebSound(file.path)
    }

    override fun newMusic(file: FileHandle): Music {
        return WebMusic(file.path)
    }
}