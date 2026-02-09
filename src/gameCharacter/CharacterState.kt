package gameCharacter

sealed class CharacterState {
    object Idle : CharacterState()
    object Running : CharacterState()
    data class Attack(val damage: Int) : CharacterState()
    data class Death(val reason: String) : CharacterState()
}