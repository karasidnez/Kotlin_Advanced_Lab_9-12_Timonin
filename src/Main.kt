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
fun main() {

    val manager = ResourseManager()
    val generator = ResourseManager()
    val lab = ResourseManager()
    val minerals = OutpostResource(1, "Minerals", 300)
    val gas = OutpostResource(2, "Gas", 100)
    manager.add(minerals)
    manager.add(gas)
    val bonus = minerals.copy(amount = minerals.amount + 50)
    println("Копия минералов с бонусом: $bonus")
//    val generatorResult = generator.performAction(manager)
//    val labResult = lab.performAction(manager)
//    handleModuleResult(generatorResult)
//    handleModuleResult(labResult)
//    manager.printAll()



}

