package gameCharacter

fun main() {
    val character = GameCharacter("Артур")

    character.changeState(CharacterState.Running)
    character.changeState(CharacterState.Attack(15))
    character.changeState(CharacterState.Death("погиб в бою"))

    processState(character.state)

    println("\nИстория состояний:")
    val states = listOf(
        CharacterState.Idle,
        CharacterState.Running,
        CharacterState.Attack(25),
        CharacterState.Death("упал с обрыва")
    )

    states.forEach { processState(it) }
}

fun processState(state: CharacterState) {
    when (state) {
        is CharacterState.Idle -> println("Персонаж бездействует")
        is CharacterState.Running -> println("Персонаж бежит")
        is CharacterState.Attack -> println("Персонаж атакует с уроном ${state.damage}")
        is CharacterState.Death -> println("Персонаж погиб: ${state.reason}")
    }
}