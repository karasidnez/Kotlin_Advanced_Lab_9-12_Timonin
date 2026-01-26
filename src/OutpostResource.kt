data class OutpostResource(
    val id: Int,
    val name: String,
    var amount: Int
){
    override fun toString(): String{
        return "Ваш ресурс: $id | Имя: $name | Количество: $amount"
    }
}
fun main() {
    val gas = OutpostResource(1,"Gas",100)
    val mineral = OutpostResource(2, "Minerals",250)
    println("Успех!Вы добыли дополнительно: ${mineral.amount + 50}")
    val bonusMineral = mineral.copy(3,"Minerals Bonus",mineral.amount + 50)
    println(gas.toString())
    println(mineral.toString())
    println(bonusMineral.toString())

}
