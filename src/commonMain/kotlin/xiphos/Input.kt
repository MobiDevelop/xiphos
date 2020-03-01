package xiphos

interface Input {

    enum class Button {
        PRIMARY, AUXILIARY, SECONDARY
    }

    fun setInputProcessor(inputProcessor: InputProcessor?)

}

