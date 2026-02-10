package modules

import resources.OutpostResource
import resources.ResourseManager

class EnergyGenerator : OutpostModule(name = "Генератор энергии") {
    override fun performAction(manager: ResourseManager): ModuleResult {
        println("Генератор работает...производит 20 энергии")
        val energy = manager.get("Energy")
        return if (energy != null) {
            energy.amount += 20
            ModuleResult.ResourceProduced("Energy", 20)
        } else {
            manager.add(OutpostResource(99, "Energy", 20))
            ModuleResult.Success("Энергия создана впервые")
        }
    }
}