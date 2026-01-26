interface Movable{
    var speed: Int
    val model: String
    val number: String
    fun move()
    fun stop(){
        println("Останавливаемся...")
    }
}
interface VideoPlayable{
    fun play() = println("Play video")
}
interface  AudioPlayable{
    fun play() = println("Play audio")
}
class MediaPlayer : VideoPlayable, AudioPlayable{
    override fun play(){
        println("Start palaying")
        super<VideoPlayable>.play()
        super<AudioPlayable>.play()

    }
}
class Car(
    override val model: String,
    override val number: String
) : Movable{

    override var speed = 60
    override fun move() {
        println("Едем на машине cо скоростью $speed")
    }
}
class Aircraft(
    override val model: String,
    override val number: String
) : Movable{
    override var speed = 600
    override fun move() {
        println("Летим на самолёте cо скоростью $speed")
    }
    override fun stop() = println("Приземляемся...")
}
fun travel(obj: Movable) = obj.move()
fun main() {
    val car = Car("LADA","134LAD")
    val aircraft = Aircraft("Boindg","737")
    val player = MediaPlayer()
    travel(car)
    travel(aircraft)
    aircraft.move()
    aircraft.move()
    player.play()

}