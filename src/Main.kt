import resources.OutpostResource
import resources.ResourseManager
import modules.ModuleResult

fun handleModuleResult(result: ModuleResult) {
    when (result) {
        is ModuleResult.Success ->
            println("Успех: ${result.message}")
        is ModuleResult.ResourceProduced ->
            println("Произведено: ${result.resourceName} +${result.amount}")
        is ModuleResult.NotEnoughResources ->
            println(
                "Недостаточно ресурса ${result.resourceName}. " +
                        "Нужно: ${result.required}, есть: ${result.available}"
            )
        is ModuleResult.Error ->
            println("ОШИБКА: ${result.reason}")
    }
}
object SystemLogger {
    init {
        println("SystemLogger инициализирован")
    }
    fun log(message: String){
        println("[LOG] $message")
    }
}
val logger by lazy{
    SystemLogger
}
fun main() {

    logger.log("Запуск базы")
    val loadedResource = FileStorage.load()
    val manager = ResourseManager()
    loadedResource.forEach { manager.add(it) }
    if (loadedResource.isEmpty()){
        manager.add(OutpostResource(1,"Minerals",300))
        manager.add(OutpostResource(2,"Gas",100))
    }
    FileStorage.save(manager.getAll())

}

