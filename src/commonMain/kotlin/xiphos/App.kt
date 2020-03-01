package xiphos

interface App {

    fun listener(): AppListener
    fun graphics(): Graphics
    fun audio(): Audio
    fun input(): Input
    fun files(): Files
    fun net(): Net

}